package com.skx.tomikecommonlibrary.imageloader.transform;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * 作者：shiguotao
 * 日期：2018/11/5 5:10 PM
 * 描述：ImageLoader 转换接口
 * <p>
 * 1.必须实现{@link #equals（Object）}和{@link #hashCode（）}才能使内存缓存正常工作。
 * <p>
 * 2.一个类，用于对实现{@link #equals（Object）}和{@link #hashCode（）}}的资源执行任意转换，以识别内存缓存中的转换
 * 和{@link #updateDiskCacheKey（java.security。 MessageDigest）}}来识别磁盘缓存中的转换。
 * <p>
 * 3.如果更新转换，{@link #equals（Object）}，{@link #hashCode（）}和{@link #updateDiskCacheKey（java.security.MessageDigest）}都应该更改。
 * 如果您使用的是简单的String键，则执行此操作的简单方法是在键上附加版本号。如果不这样做，将意味着用户可能会看到从缓存加载的图像已应用旧版本的转换。
 * 更改这些方法的返回值将确保缓存键已更改，因此将使用更新的Transformation重新生成任何缓存的资源。
 */
public interface Transformation {

    String STRING_CHARSET_NAME = "UTF-8";
    Charset CHARSET = Charset.forName(STRING_CHARSET_NAME);

    /**
     * 转换给定Bitmap并返回转换后的Bitmap
     *
     * @param toTransform 要转换的{@link android.graphics.Bitmap}。
     * @param outWidth    变换后的位图的理想宽度（变换后的宽度不需要精确匹配）
     * @param outHeight   变换后的位图的理想高度（变换后的高度不需要精确匹配）。
     * @return 转换后的{@link android.graphics.Bitmap}
     */
    Bitmap transform(@NonNull Bitmap toTransform, int outWidth, int outHeight);

    /**
     * 将所有唯一标识信息添加到给定摘要中。用于识别磁盘缓存中的转换。
     * <p>
     * 注 ： 在此方法中使用{@link java.security.MessageDigest＃reset（）}将导致未定义的行为。
     */
    void updateDiskCacheKey(@NonNull MessageDigest messageDigest);

    /**
     * 返回转换的唯一键，用于缓存目的。 如果变换具有参数（例如大小，比例因子等），则这些参数应该是密钥的一部分。
     */
    @Override
    int hashCode();

    /**
     * For caching to work correctly, implementations <em>must</em> implement this method and
     * {@link #hashCode()}.
     */
    @Override
    boolean equals(Object o);
}
