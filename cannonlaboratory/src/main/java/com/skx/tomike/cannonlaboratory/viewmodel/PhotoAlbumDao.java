package com.skx.tomike.cannonlaboratory.viewmodel;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.Log;

import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageBucket;
import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * 描述 : 相册数据读取工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/19 5:38 PM
 */
public class PhotoAlbumDao {

    private final String TAG = getClass().getSimpleName();

    private ContentResolver cr;
    // 缩略图列表
    private HashMap<String, String> thumbnailList = new HashMap<>();
    private HashMap<String, PhotoUpImageBucket> bucketList = new HashMap<>();

    public PhotoAlbumDao(Context context) {
        if (context != null) {
            cr = context.getContentResolver();
        }
    }

    /**
     * 得到缩略图
     */
    private void getThumbnail() {
        String[] projection = {Thumbnails._ID, Thumbnails.IMAGE_ID, Thumbnails.DATA};
        Cursor cursor = Thumbnails.queryMiniThumbnails(cr, Thumbnails.EXTERNAL_CONTENT_URI, Thumbnails.MINI_KIND, projection);
        if (cursor.moveToFirst()) {
            int image_id;
            String image_path;
            int image_idColumn = cursor.getColumnIndex(Thumbnails.IMAGE_ID);
            int dataColumn = cursor.getColumnIndex(Thumbnails.DATA);
            do {
                image_id = cursor.getInt(image_idColumn);
                image_path = cursor.getString(dataColumn);
                thumbnailList.put("" + image_id, image_path);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    /**
     * 得到图片集
     */
    private void buildImagesBucketList() {
        String[] columns = new String[]{MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        // 得到一个游标
        Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
                MediaStore.Images.Media.MIME_TYPE + "=? " +
                        "or " + MediaStore.Images.Media.MIME_TYPE + "=? " +
                        "or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg", "image/png", "image/jpg"},
                MediaStore.Images.Media.DATE_MODIFIED + " desc");
        if (cur == null || cur.isClosed()) {
            return;
        }

        // 获取指定列的索引
        int photoIDIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
        int photoPathIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);
        int bucketDisplayNameIndex = cur.getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);

        // 迭代取出数据
        while (cur.moveToNext()) {
            String _id = cur.getString(photoIDIndex);
            String path = cur.getString(photoPathIndex);
            String bucketName = cur.getString(bucketDisplayNameIndex);
            String bucketId = cur.getString(bucketIdIndex);

            PhotoUpImageBucket bucket = bucketList.get(bucketId);
            if (bucket == null) {
                bucket = new PhotoUpImageBucket();
                bucket.bucketName = bucketName;
                bucketList.put(bucketId, bucket);
            }
            bucket.count++;
            bucket.imageList.add(new PhotoUpImageItem(_id, path));
        }

        cur.close();
    }

    /**
     * 得到图片集
     *
     * @return
     */
    public List<PhotoUpImageBucket> getImagesBucketList() {
        buildImagesBucketList();

        List<PhotoUpImageBucket> tmpList = new ArrayList<>();
        for (Entry<String, PhotoUpImageBucket> entry : bucketList.entrySet()) {
            tmpList.add(entry.getValue());
        }
        return tmpList;
    }

    /**
     * 得到原始图像路径
     *
     * @param image_id
     * @return
     */
    public String getOriginalImagePath(String image_id) {
        String path = null;
        Log.i(TAG, "---(^o^)----" + image_id);
        String[] projection = {Media._ID, Media.DATA};
        Cursor cursor = cr.query(Media.EXTERNAL_CONTENT_URI, projection,
                Media._ID + "=" + image_id, null,
                Media.DATE_MODIFIED + " desc");
        if (cursor != null) {
            cursor.moveToFirst();
            path = cursor.getString(cursor.getColumnIndex(Media.DATA));
        }
        return path;
    }

    public void destoryList() {
        thumbnailList.clear();
        thumbnailList = null;
        bucketList.clear();
        bucketList = null;
    }
}
