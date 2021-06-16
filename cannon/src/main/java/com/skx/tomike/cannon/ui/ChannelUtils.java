package com.skx.tomike.cannon.ui;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 有关渠道的工具
 * 参考相应工具
 * 美团 walle
 * https://github.com/Meituan-Dianping/walle
 * <p>
 * 兼容打包V2
 * apk signer 打包V2 payload_reader https://github.com/Meituan-Dianping/walle/blob/master/payload_reader/README.md
 * <p>
 * 当前项目，只有相应的读取相应的渠道，并不参与写入
 * 写入由自动化构建完成
 */
public class ChannelUtils {

    private ChannelUtils() {

    }
    /**
     * @return 返回当前channel
     */
    @NonNull
    static String getChannel(Context context) {
        return getChannel(context, "xiaozhu");
    }

    /**
     * @param defaultChannel 当包内channel为空的时 做为默认返回
     * @return 返回当前channel 如果有，返回相应的包里面的channel 如果没有返回默认的相应信息
     */
    public static String getChannel(Context context, String defaultChannel) {
        ChannelInfo info = getChannelInfo(context);
        if (info == null) {
            return defaultChannel;
        }
        return info.getChannel();
    }


    /**
     * @return 渠道信息
     */
    static ChannelInfo getChannelInfo(Context context) {
        final String apkPath = getAppPath(context);
        if (TextUtils.isEmpty(apkPath)) {
            return null;
        }
        return ChannelReader.get(new File(apkPath));
    }

    /**
     * @param context 上下文
     * @return apk文件所有位置
     */
    static String getAppPath(Context context) {
        String apkPath = null;
        try {
            final ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            apkPath = applicationInfo.sourceDir;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return apkPath;
    }
}


final class ChannelReader {
    static final String CHANNEL_KEY = "channel";

    private ChannelReader() {
        super();
    }

    /**
     * easy api for get channel & extra info.<br/>
     *
     * @param apkFile apk file
     * @return null if not found
     */
    public static ChannelInfo get(final File apkFile) {
        final Map<String, String> result = getMap(apkFile);
        if (result == null) {
            return null;
        }
        final String channel = result.get(CHANNEL_KEY);
        result.remove(CHANNEL_KEY);
        return new ChannelInfo(channel, result);
    }

    /**
     * get channel & extra info by map, use {@link ChannelReader#CHANNEL_KEY PayloadReader.CHANNEL_KEY} get channel
     *
     * @param apkFile apk file
     * @return null if not found
     */
    static Map<String, String> getMap(final File apkFile) {
        try {
            final String rawString = getRaw(apkFile);
            if (rawString == null) {
                return null;
            }
            final JSONObject jsonObject = new JSONObject(rawString);
            final Iterator keys = jsonObject.keys();
            final Map<String, String> result = new HashMap<String, String>();
            while (keys.hasNext()) {
                final String key = keys.next().toString();
                result.put(key, jsonObject.getString(key));
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get raw string from channel id
     *
     * @param apkFile apk file
     * @return null if not found
     */
    static String getRaw(final File apkFile) {
        return PayloadReader.getString(apkFile, ApkUtil.APK_CHANNEL_BLOCK_ID);
    }
}

final class PayloadReader {
    private PayloadReader() {
        super();
    }

    /**
     * get string (UTF-8) by id
     *
     * @param apkFile apk file
     * @return null if not found
     */
    public static String getString(final File apkFile, final int id) {
        final byte[] bytes = PayloadReader.get(apkFile, id);
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, ApkUtil.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get bytes by id <br/>
     *
     * @param apkFile apk file
     * @param id      id
     * @return bytes
     */
    public static byte[] get(final File apkFile, final int id) {
        final Map<Integer, ByteBuffer> idValues = getAll(apkFile);
        if (idValues == null) {
            return null;
        }
        final ByteBuffer byteBuffer = idValues.get(id);
        if (byteBuffer == null) {
            return null;
        }
        return getBytes(byteBuffer);
    }

    /**
     * get data from byteBuffer
     *
     * @param byteBuffer buffer
     * @return useful data
     */
    private static byte[] getBytes(final ByteBuffer byteBuffer) {
        final byte[] array = byteBuffer.array();
        final int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, arrayOffset + byteBuffer.position(),
                arrayOffset + byteBuffer.limit());
    }

    /**
     * get all custom (id, buffer) <br/>
     * Note: get final from byteBuffer, please use {@link PayloadReader#getBytes getBytes}
     *
     * @param apkFile apk file
     * @return all custom (id, buffer)
     */
    private static Map<Integer, ByteBuffer> getAll(final File apkFile) {
        Map<Integer, ByteBuffer> idValues = null;
        try {
            RandomAccessFile randomAccessFile = null;
            FileChannel fileChannel = null;
            try {
                randomAccessFile = new RandomAccessFile(apkFile, "r");
                fileChannel = randomAccessFile.getChannel();
                final ByteBuffer apkSigningBlock2 = ApkUtil.findApkSigningBlock(fileChannel).getFirst();
                idValues = ApkUtil.findIdValues(apkSigningBlock2);
            } catch (IOException ignore) {
            } finally {
                try {
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                } catch (IOException ignore) {
                }
                try {
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (IOException ignore) {
                }
            }
        } catch (SignatureNotFoundException ignore) {
        }

        return idValues;
    }


}


/**
 * 相应的写的入的渠道信息
 */
class ChannelInfo {
    private final String channel;
    /**
     * 当前结构下无用，仅保留，为后续做处理
     */
    private final Map<String, String> extraInfo;

    ChannelInfo(final String channel, final Map<String, String> extraInfo) {
        this.channel = channel;
        this.extraInfo = extraInfo;
    }

    public String getChannel() {
        return channel;
    }

    public Map<String, String> getExtraInfo() {
        return extraInfo;
    }
}

final class ApkUtil {
    private ApkUtil() {
        super();
    }

    /**
     * APK Signing Block Magic Code: magic “APK Sig Block 42” (16 bytes)
     * "APK Sig Block 42" : 41 50 4B 20 53 69 67 20 42 6C 6F 63 6B 20 34 32
     */
    public static final long APK_SIG_BLOCK_MAGIC_HI = 0x3234206b636f6c42L; // LITTLE_ENDIAN, High
    public static final long APK_SIG_BLOCK_MAGIC_LO = 0x20676953204b5041L; // LITTLE_ENDIAN, Low
    private static final int APK_SIG_BLOCK_MIN_SIZE = 32;

    /*
     The v2 signature of the APK is stored as an ID-value pair with ID 0x7109871a
     (https://source.android.com/security/apksigning/v2.html#apk-signing-block)
      */
    public static final int APK_SIGNATURE_SCHEME_V2_BLOCK_ID = 0x7109871a;

    // Our Channel Block ID
    public static final int APK_CHANNEL_BLOCK_ID = 0x71777777;

    public static final String DEFAULT_CHARSET = "UTF-8";

    private static final int ZIP_EOCD_REC_MIN_SIZE = 22;
    private static final int ZIP_EOCD_REC_SIG = 0x06054b50;
    private static final int UINT16_MAX_VALUE = 0xffff;
    private static final int ZIP_EOCD_COMMENT_LENGTH_FIELD_OFFSET = 20;

    private static long getCommentLength(final FileChannel fileChannel) throws IOException {
        // End of central directory record (EOCD)
        // Offset    Bytes     Description[23]
        // 0           4       End of central directory signature = 0x06054b50
        // 4           2       Number of this disk
        // 6           2       Disk where central directory starts
        // 8           2       Number of central directory records on this disk
        // 10          2       Total number of central directory records
        // 12          4       Size of central directory (bytes)
        // 16          4       Offset of start of central directory, relative to start of archive
        // 20          2       Comment length (n)
        // 22          n       Comment
        // For a zip with no archive comment, the
        // end-of-central-directory record will be 22 bytes long, so
        // we expect to find the EOCD marker 22 bytes from the end.


        final long archiveSize = fileChannel.size();
        if (archiveSize < ZIP_EOCD_REC_MIN_SIZE) {
            throw new IOException("APK too small for ZIP End of Central Directory (EOCD) record");
        }
        // ZIP End of Central Directory (EOCD) record is located at the very end of the ZIP archive.
        // The record can be identified by its 4-byte signature/magic which is located at the very
        // beginning of the record. A complication is that the record is variable-length because of
        // the comment field.
        // The algorithm for locating the ZIP EOCD record is as follows. We search backwards from
        // end of the buffer for the EOCD record signature. Whenever we find a signature, we check
        // the candidate record's comment length is such that the remainder of the record takes up
        // exactly the remaining bytes in the buffer. The search is bounded because the maximum
        // size of the comment field is 65535 bytes because the field is an unsigned 16-bit number.
        final long maxCommentLength = Math.min(archiveSize - ZIP_EOCD_REC_MIN_SIZE, UINT16_MAX_VALUE);
        final long eocdWithEmptyCommentStartPosition = archiveSize - ZIP_EOCD_REC_MIN_SIZE;
        for (int expectedCommentLength = 0; expectedCommentLength <= maxCommentLength;
             expectedCommentLength++) {
            final long eocdStartPos = eocdWithEmptyCommentStartPosition - expectedCommentLength;

            final ByteBuffer byteBuffer = ByteBuffer.allocate(4);
            fileChannel.position(eocdStartPos);
            fileChannel.read(byteBuffer);
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

            if (byteBuffer.getInt(0) == ZIP_EOCD_REC_SIG) {
                final ByteBuffer commentLengthByteBuffer = ByteBuffer.allocate(2);
                fileChannel.position(eocdStartPos + ZIP_EOCD_COMMENT_LENGTH_FIELD_OFFSET);
                fileChannel.read(commentLengthByteBuffer);
                commentLengthByteBuffer.order(ByteOrder.LITTLE_ENDIAN);

                final int actualCommentLength = commentLengthByteBuffer.getShort(0);
                if (actualCommentLength == expectedCommentLength) {
                    return actualCommentLength;
                }
            }
        }
        throw new IOException("ZIP End of Central Directory (EOCD) record not found");
    }

    private static long findCentralDirStartOffset(final FileChannel fileChannel) throws IOException {
        return findCentralDirStartOffset(fileChannel, getCommentLength(fileChannel));
    }

    private static long findCentralDirStartOffset(final FileChannel fileChannel, final long commentLength) throws IOException {
        // End of central directory record (EOCD)
        // Offset    Bytes     Description[23]
        // 0           4       End of central directory signature = 0x06054b50
        // 4           2       Number of this disk
        // 6           2       Disk where central directory starts
        // 8           2       Number of central directory records on this disk
        // 10          2       Total number of central directory records
        // 12          4       Size of central directory (bytes)
        // 16          4       Offset of start of central directory, relative to start of archive
        // 20          2       Comment length (n)
        // 22          n       Comment
        // For a zip with no archive comment, the
        // end-of-central-directory record will be 22 bytes long, so
        // we expect to find the EOCD marker 22 bytes from the end.

        final ByteBuffer zipCentralDirectoryStart = ByteBuffer.allocate(4);
        zipCentralDirectoryStart.order(ByteOrder.LITTLE_ENDIAN);
        fileChannel.position(fileChannel.size() - commentLength - 6); // 6 = 2 (Comment length) + 4 (Offset of start of central directory, relative to start of archive)
        fileChannel.read(zipCentralDirectoryStart);
        return (long) zipCentralDirectoryStart.getInt(0);
    }

    public static Pair<ByteBuffer, Long> findApkSigningBlock(
            final FileChannel fileChannel) throws IOException, SignatureNotFoundException {
        final long centralDirOffset = findCentralDirStartOffset(fileChannel);
        return findApkSigningBlock(fileChannel, centralDirOffset);
    }

    public static Pair<ByteBuffer, Long> findApkSigningBlock(
            final FileChannel fileChannel, final long centralDirOffset) throws IOException, SignatureNotFoundException {

        // Find the APK Signing Block. The block immediately precedes the Central Directory.

        // FORMAT:
        // OFFSET       DATA TYPE  DESCRIPTION
        // * @+0  bytes uint64:    size in bytes (excluding this field)
        // * @+8  bytes payload
        // * @-24 bytes uint64:    size in bytes (same as the one above)
        // * @-16 bytes uint128:   magic

        if (centralDirOffset < APK_SIG_BLOCK_MIN_SIZE) {
            throw new SignatureNotFoundException(
                    "APK too small for APK Signing Block. ZIP Central Directory offset: "
                            + centralDirOffset);
        }
        // Read the magic and offset in file from the footer section of the block:
        // * uint64:   size of block
        // * 16 bytes: magic
        fileChannel.position(centralDirOffset - 24);
        final ByteBuffer footer = ByteBuffer.allocate(24);
        fileChannel.read(footer);
        footer.order(ByteOrder.LITTLE_ENDIAN);
        if ((footer.getLong(8) != APK_SIG_BLOCK_MAGIC_LO)
                || (footer.getLong(16) != APK_SIG_BLOCK_MAGIC_HI)) {
            throw new SignatureNotFoundException(
                    "No APK Signing Block before ZIP Central Directory");
        }
        // Read and compare size fields
        final long apkSigBlockSizeInFooter = footer.getLong(0);
        if ((apkSigBlockSizeInFooter < footer.capacity())
                || (apkSigBlockSizeInFooter > Integer.MAX_VALUE - 8)) {
            throw new SignatureNotFoundException(
                    "APK Signing Block size out of range: " + apkSigBlockSizeInFooter);
        }
        final int totalSize = (int) (apkSigBlockSizeInFooter + 8);
        final long apkSigBlockOffset = centralDirOffset - totalSize;
        if (apkSigBlockOffset < 0) {
            throw new SignatureNotFoundException(
                    "APK Signing Block offset out of range: " + apkSigBlockOffset);
        }
        fileChannel.position(apkSigBlockOffset);
        final ByteBuffer apkSigBlock = ByteBuffer.allocate(totalSize);
        fileChannel.read(apkSigBlock);
        apkSigBlock.order(ByteOrder.LITTLE_ENDIAN);
        final long apkSigBlockSizeInHeader = apkSigBlock.getLong(0);
        if (apkSigBlockSizeInHeader != apkSigBlockSizeInFooter) {
            throw new SignatureNotFoundException(
                    "APK Signing Block sizes in header and footer do not match: "
                            + apkSigBlockSizeInHeader + " vs " + apkSigBlockSizeInFooter);
        }
        return Pair.of(apkSigBlock, apkSigBlockOffset);
    }

    public static Map<Integer, ByteBuffer> findIdValues(final ByteBuffer apkSigningBlock) throws SignatureNotFoundException {
        checkByteOrderLittleEndian(apkSigningBlock);
        // FORMAT:
        // OFFSET       DATA TYPE  DESCRIPTION
        // * @+0  bytes uint64:    size in bytes (excluding this field)
        // * @+8  bytes pairs
        // * @-24 bytes uint64:    size in bytes (same as the one above)
        // * @-16 bytes uint128:   magic
        final ByteBuffer pairs = sliceFromTo(apkSigningBlock, 8, apkSigningBlock.capacity() - 24);

        final Map<Integer, ByteBuffer> idValues = new LinkedHashMap<>(); // keep order

        int entryCount = 0;
        while (pairs.hasRemaining()) {
            entryCount++;
            if (pairs.remaining() < 8) {
                throw new SignatureNotFoundException(
                        "Insufficient data to read size of APK Signing Block entry #" + entryCount);
            }
            final long lenLong = pairs.getLong();
            if ((lenLong < 4) || (lenLong > Integer.MAX_VALUE)) {
                throw new SignatureNotFoundException(
                        "APK Signing Block entry #" + entryCount
                                + " size out of range: " + lenLong);
            }
            final int len = (int) lenLong;
            final int nextEntryPos = pairs.position() + len;
            if (len > pairs.remaining()) {
                throw new SignatureNotFoundException(
                        "APK Signing Block entry #" + entryCount + " size out of range: " + len
                                + ", available: " + pairs.remaining());
            }
            final int id = pairs.getInt();
            idValues.put(id, getByteBuffer(pairs, len - 4));

            pairs.position(nextEntryPos);
        }

        return idValues;
    }

    /**
     * Returns new byte buffer whose content is a shared subsequence of this buffer's content
     * between the specified start (inclusive) and end (exclusive) positions. As opposed to
     * {@link ByteBuffer#slice()}, the returned buffer's byte order is the same as the source
     * buffer's byte order.
     */
    private static ByteBuffer sliceFromTo(final ByteBuffer source, final int start, final int end) {
        if (start < 0) {
            throw new IllegalArgumentException("start: " + start);
        }
        if (end < start) {
            throw new IllegalArgumentException("end < start: " + end + " < " + start);
        }
        final int capacity = source.capacity();
        if (end > source.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + end + " > " + capacity);
        }
        final int originalLimit = source.limit();
        final int originalPosition = source.position();
        try {
            source.position(0);
            source.limit(end);
            source.position(start);
            final ByteBuffer result = source.slice();
            result.order(source.order());
            return result;
        } finally {
            source.position(0);
            source.limit(originalLimit);
            source.position(originalPosition);
        }
    }

    /**
     * Relative <em>get</em> method for reading {@code size} number of bytes from the current
     * position of this buffer.
     * <p>
     * <p>This method reads the next {@code size} bytes at this buffer's current position,
     * returning them as a {@code ByteBuffer} with start set to 0, limit and capacity set to
     * {@code size}, byte order set to this buffer's byte order; and then increments the position by
     * {@code size}.
     */
    private static ByteBuffer getByteBuffer(final ByteBuffer source, final int size)
            throws BufferUnderflowException {
        if (size < 0) {
            throw new IllegalArgumentException("size: " + size);
        }
        final int originalLimit = source.limit();
        final int position = source.position();
        final int limit = position + size;
        if ((limit < position) || (limit > originalLimit)) {
            throw new BufferUnderflowException();
        }
        source.limit(limit);
        try {
            final ByteBuffer result = source.slice();
            result.order(source.order());
            source.position(limit);
            return result;
        } finally {
            source.limit(originalLimit);
        }
    }

    private static void checkByteOrderLittleEndian(final ByteBuffer buffer) {
        if (buffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }


}

class SignatureNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public SignatureNotFoundException(final String message) {
        super(message);
    }

    public SignatureNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

/**
 * Pair
 */
final class Pair<A, B> {
    private final A mFirst;
    private final B mSecond;

    private Pair(final A first, final B second) {
        mFirst = first;
        mSecond = second;
    }

    public static <A, B> Pair<A, B> of(final A first, final B second) {
        return new Pair<A, B>(first, second);
    }

    public A getFirst() {
        return mFirst;
    }

    public B getSecond() {
        return mSecond;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mFirst == null) ? 0 : mFirst.hashCode());
        result = prime * result + ((mSecond == null) ? 0 : mSecond.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("rawtypes") final Pair other = (Pair) obj;
        if (mFirst == null) {
            if (other.mFirst != null) {
                return false;
            }
        } else if (!mFirst.equals(other.mFirst)) {
            return false;
        }
        if (mSecond == null) {
            if (other.mSecond != null) {
                return false;
            }
        } else if (!mSecond.equals(other.mSecond)) {
            return false;
        }
        return true;
    }
}


