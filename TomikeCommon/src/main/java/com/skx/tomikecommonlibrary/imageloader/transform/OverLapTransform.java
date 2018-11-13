package com.skx.tomikecommonlibrary.imageloader.transform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.bumptech.glide.util.Util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * 作者：shiguotao
 * 日期：2018/11/8 2:37 PM
 * 描述：水印转换
 */
public final class OverLapTransform implements TransformAdapter {

    private static final String ID = "com.skx.tomikecommonlibrary.imageloader.transform.OverLapTransform";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private Bitmap mOverLapBitmap;
    private Context mContext;

    public OverLapTransform(Context context, Bitmap overlapBitmap) {
        this.mContext = context;
        this.mOverLapBitmap = overlapBitmap;
    }

    public OverLapTransform(Context context, int drawableResId) {
        this.mContext = context;
        this.mOverLapBitmap = getBitmap(drawableResId);
    }

    @Override
    public Bitmap transform(@NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if (mOverLapBitmap != null) {
            return overlapBitmap(toTransform, mOverLapBitmap);

        } else {
            return toTransform;
        }
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);

        if (mOverLapBitmap != null) {
            ByteBuffer buffer = ByteBuffer.allocate(mOverLapBitmap.getByteCount());
            mOverLapBitmap.copyPixelsToBuffer(buffer);
            messageDigest.update(buffer.array());
        }
    }

    @Override
    public int hashCode() {
        if (mOverLapBitmap != null) {
            return Util.hashCode(ID.hashCode(), mOverLapBitmap.hashCode());
        } else {
            return ID.hashCode();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OverLapTransform) {
            OverLapTransform other = (OverLapTransform) obj;
            return (this.mOverLapBitmap == null && other.mOverLapBitmap == null)
                    || (this.mOverLapBitmap != null && other.mOverLapBitmap != null && this.mOverLapBitmap.equals(other.mOverLapBitmap));
        }
        return false;
    }

    /**
     * Bitmap 重叠，添加水印
     *
     * @param targetBitmap  目标Bitmap
     * @param overlapBitmap 添加的Bitmap
     * @return 重叠添加后的Bitmap
     */
    private Bitmap overlapBitmap(Bitmap targetBitmap, Bitmap overlapBitmap) {
        Bitmap targetBitmapCopy = targetBitmap.copy(Bitmap.Config.ARGB_8888, true);

        int targetWidth = targetBitmapCopy.getWidth();
        int targetHeight = targetBitmapCopy.getHeight();

        int overlapWidth = overlapBitmap.getWidth();
        int overlapHeight = overlapBitmap.getHeight();

        int screenWidth = getScreenWidth();


        float desW = targetWidth * ((float) overlapWidth / (float) screenWidth);
        float desH = overlapHeight * (desW / overlapWidth);

        Canvas canvas = new Canvas(targetBitmapCopy);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        int aimX = (int) (Math.abs(targetWidth - desW) / 2);
        int aimY = (int) (Math.abs(targetHeight - desH) / 2);

        Rect desRect = new Rect(aimX, aimY, (int) (aimX + desW), (int) (aimY + desH));
        canvas.drawBitmap(overlapBitmap, null, desRect, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return targetBitmapCopy;
    }

    private int getScreenWidth() {
        WindowManager _manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics _displayMetrics = new DisplayMetrics();
        _manager.getDefaultDisplay().getMetrics(_displayMetrics);

        int[] screenWHPixels = new int[2];
        screenWHPixels[0] = _displayMetrics.widthPixels;
        screenWHPixels[1] = _displayMetrics.heightPixels;

        return screenWHPixels[0];
    }

    private Bitmap getBitmap(int id) {
        Drawable drawable = ContextCompat.getDrawable(mContext, id);
        if (drawable == null) return null;
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }
}
