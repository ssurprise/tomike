package com.skx.common.utils

import android.graphics.*

/**
 * 修改图片色度、亮度、饱和度
 *
 * @param bm
 * @param hue        色度
 * @param saturation 饱和度
 * @param lum        亮度
 * @return
 */
fun handleImageEffect(bm: Bitmap, hue: Float, saturation: Float, lum: Float): Bitmap {
    // 传进来的bitmap 默认是不可修改的,所以复制一个bitmap 对其操作
    val bitmap = Bitmap.createBitmap(bm.width, bm.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // 色度
    val hueMatrix = ColorMatrix()
    hueMatrix.setRotate(0, hue)
    hueMatrix.setRotate(1, hue)
    hueMatrix.setRotate(2, hue)

    // 饱和度
    val saturationMatrix = ColorMatrix()
    saturationMatrix.setSaturation(saturation)

    // 亮度
    val lumMatrix = ColorMatrix()
    lumMatrix.setScale(lum, lum, lum, 1f)

    val imageMatrix = ColorMatrix()
    imageMatrix.postConcat(hueMatrix)
    imageMatrix.postConcat(saturationMatrix)
    imageMatrix.postConcat(lumMatrix)

    paint.colorFilter = ColorMatrixColorFilter(imageMatrix)
    canvas.drawBitmap(bm, 0f, 0f, paint)
    return bitmap
}


/**
 * 图片底片效果（对图片的像素点进行需要修改）
 *
 * @param bm 需要变换的bitmap
 * @return 变换后的Bitmap
 */
fun handlerImageNegative(bm: Bitmap): Bitmap {
    val width = bm.width
    val height = bm.height
    var color: Int
    var r: Int
    var g: Int
    var b: Int
    var a: Int
    val bmp = Bitmap.createBitmap(bm.width, bm.height, Bitmap.Config.ARGB_8888)

    val oldPx = IntArray(width * height)
    bm.getPixels(oldPx, 0, width, 0, 0, width, height)
    val newPx = IntArray(width * height)
    for (i in 0 until width * height) {
        color = oldPx[i]
        r = Color.red(color)
        g = Color.green(color)
        b = Color.blue(color)
        a = Color.alpha(color)

        r = 255 - r
        g = 255 - g
        b = 255 - b

        if (r < 0) {
            r = 0
        } else if (r > 255) {
            r = 255
        }
        if (g < 0) {
            g = 0
        }
        if (g > 255) {
            g = 255
        }
        if (b < 0) {
            b = 0
        }
        if (b > 255) {
            b = 255
        }
        newPx[i] = Color.argb(a, r, g, b)//合成新颜色
    }
    bmp.setPixels(newPx, 0, width, 0, 0, width, height)
    return bmp
}

/**
 * 旧图片效果（对图片的像素点进行需要修改）
 *
 * @param bm 需要变换的bitmap
 * @return 变换后的Bitmap
 */
fun handlerImageOld(bm: Bitmap): Bitmap {
    val width = bm.width
    val height = bm.height
    var color: Int
    var a: Int
    var r: Int
    var g: Int
    var b: Int
    var r1: Int
    var g1: Int
    var b1: Int
    val bmp = Bitmap.createBitmap(bm.width, bm.height, Bitmap.Config.ARGB_8888)

    val oldPx = IntArray(width * height)
    bm.getPixels(oldPx, 0, width, 0, 0, width, height)
    val newPx = IntArray(width * height)
    for (i in 0 until width * height) {
        color = oldPx[i]
        a = Color.alpha(color)
        r = Color.red(color)
        g = Color.green(color)
        b = Color.blue(color)

        r = 255 - r
        g = 255 - g
        b = 255 - b

        r1 = (0.393 * r + 0.769 * g + 0.189 * b).toInt()
        g1 = (0.349 * r + 0.686 * g + 0.168 * b).toInt()
        b1 = (0.272 * r + 0.534 * g + 0.131 * b).toInt()

        if (r1 > 255) {
            r1 = 255
        }
        if (g1 > 255) {
            g1 = 255
        }
        if (b1 > 255) {
            b1 = 255
        }
        newPx[i] = Color.argb(a, r1, g1, b1)//合成新颜色
    }
    bmp.setPixels(newPx, 0, width, 0, 0, width, height)
    return bmp
}
