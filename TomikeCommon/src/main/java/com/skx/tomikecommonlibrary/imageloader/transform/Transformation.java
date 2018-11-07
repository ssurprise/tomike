package com.skx.tomikecommonlibrary.imageloader.transform;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.nio.charset.Charset;

/**
 * 作者：shiguotao
 * 日期：2018/11/5 5:10 PM
 * 描述：ImageLoader 转换接口
 */
public interface Transformation {

    String STRING_CHARSET_NAME = "UTF-8";
    Charset CHARSET = Charset.forName(STRING_CHARSET_NAME);

    /**
     * 转换给定Bitmap并返回转换后的Bitmap
     *
     * @param toTransform The {@link android.graphics.Bitmap} to transform.
     * @param outWidth    The ideal width of the transformed bitmap (the transformed width does not
     *                    need to match exactly).
     * @param outHeight   The ideal height of the transformed bitmap (the transformed height does not
     *                    need to match exactly).
     * @return 转换后的{@link android.graphics.Bitmap}
     */
    Bitmap transform(@NonNull Bitmap toTransform, int outWidth, int outHeight);

    /**
     * Adds all uniquely identifying information to the given digest.
     * <p> Note - Using {@link java.security.MessageDigest#reset()} inside of this method will result
     * in undefined behavior. </p>
     */
    byte[] diskCacheKey();

    /**
     * Returns a unique key for the transformation, used for caching purposes. If the transformation
     * has parameters (e.g. size, scale factor, etc) then these should be part of the key.
     */
    int hashCode();

    /**
     * For caching to work correctly, implementations <em>must</em> implement this method and
     * {@link #hashCode()}.
     */
    @Override
    boolean equals(Object o);
}
