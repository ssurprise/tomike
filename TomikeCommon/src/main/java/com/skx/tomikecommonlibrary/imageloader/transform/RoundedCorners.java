package com.skx.tomikecommonlibrary.imageloader.transform;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.util.Util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * 作者：shiguotao
 * 日期：2018/11/7 4:06 PM
 * 描述：圆角转换Bitmap。
 * 注：最终转换成Glide 提供的 RoundedCorners，这里不用纠结实现内容，可以忽略。
 */
public final class RoundedCorners implements TransformAdapter {
    private static final String ID = "com.skx.tomikecommonlibrary.imageloader.transform.RoundedCorners";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private final int roundingRadius;

    /**
     * @param roundingRadius 圆角半径，单位：像素
     * @throws IllegalArgumentException if rounding radius is 0 or less.
     */
    public RoundedCorners(int roundingRadius) {
        if (roundingRadius < 0) {
            throw new IllegalArgumentException("roundingRadius must be greater than 0.");
        }
        this.roundingRadius = roundingRadius;
    }

    @Override
    public Bitmap transform(@NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return null;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
        byte[] radiusData = ByteBuffer.allocate(4).putInt(roundingRadius).array();
        messageDigest.update(radiusData);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RoundedCorners) {
            RoundedCorners other = (RoundedCorners) o;
            return roundingRadius == other.roundingRadius;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Util.hashCode(ID.hashCode(),
                Util.hashCode(roundingRadius));
    }

    public int getRoundingRadius() {
        return roundingRadius;
    }
}
