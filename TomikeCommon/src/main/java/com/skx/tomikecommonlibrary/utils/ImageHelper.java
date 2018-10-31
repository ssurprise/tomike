package com.skx.tomikecommonlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by shiguotao on 2016/11/18.
 * 图片效果工具类
 */
public class ImageHelper {

    /**
     * 修改图片色度、亮度、包含度
     *
     * @param bm
     * @param hue        色度
     * @param saturation 饱和度
     * @param lum        亮度
     * @return
     */
    public static Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum) {
        // 传进来的bitmap 默认是不可修改的,所以复制一个bitmap 对其操作
        Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 色度
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        // 饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        // 亮度
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm, 0, 0, paint);
        return bitmap;
    }


    /**
     * 图片底片效果（对图片的像素点进行需要修改）
     *
     * @param bm 需要变换的bitmap
     * @return 变换后的Bitmap
     */
    public static Bitmap handlerImageNegative(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color;
        int r, g, b, a;
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);

        int[] oldPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        int[] newPx = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r < 0) {
                r = 0;
            } else if (r > 255) {
                r = 255;
            }
            if (g < 0) {
                g = 0;
            }
            if (g > 255) {
                g = 255;
            }
            if (b < 0) {
                b = 0;
            }
            if (b > 255) {
                b = 255;
            }
            newPx[i] = Color.argb(a, r, g, b);//合成新颜色
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }

    /**
     * 旧图片效果（对图片的像素点进行需要修改）
     *
     * @param bm 需要变换的bitmap
     * @return 变换后的Bitmap
     */
    public static Bitmap handlerImageOld(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color;
        int a, r, g, b, r1, g1, b1;
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);

        int[] oldPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        int[] newPx = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            a = Color.alpha(color);
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

            if (r1 > 255) {
                r1 = 255;
            }
            if (g1 > 255) {
                g1 = 255;
            }
            if (b1 > 255) {
                b1 = 255;
            }
            newPx[i] = Color.argb(a, r1, g1, b1);//合成新颜色
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }
}
