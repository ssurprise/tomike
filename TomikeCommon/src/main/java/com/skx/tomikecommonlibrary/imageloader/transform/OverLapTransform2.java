package com.skx.tomikecommonlibrary.imageloader.transform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;

/**
 * 作者：shiguotao
 * 日期：2018/11/8 2:37 PM
 * 描述：水印转换
 */
public class OverLapTransform2 extends BitmapTransformation {

    private static final String ID = "com.skx.tomikecommonlibrary.imageloader.transform.OverLapTransform2";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private WeakReference<Bitmap> mOverLapBitmap;
    private Context mContext;

    public OverLapTransform2(Context context, Bitmap overlapBitmap) {
        this.mContext = context;
        this.mOverLapBitmap = new WeakReference<>(overlapBitmap);
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if (mOverLapBitmap.get() != null) {
            return overlapBitmap(toTransform, mOverLapBitmap.get());

        } else {
            return toTransform;
        }
    }

    @Override
    public int hashCode() {
        return Util.hashCode(ID.hashCode(), mOverLapBitmap.hashCode());
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
//        messageDigest.update(toByteArray(mOverLapBitmap));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OverLapTransform2) {
            OverLapTransform2 other = (OverLapTransform2) obj;
            return this.mOverLapBitmap.equals(other.mOverLapBitmap);
        }
        return false;
    }

    /**
     * 对象转数组
     *
     * @param obj
     * @return
     */
    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
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

}
