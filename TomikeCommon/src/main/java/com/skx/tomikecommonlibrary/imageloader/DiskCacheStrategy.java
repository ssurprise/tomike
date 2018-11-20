package com.skx.tomikecommonlibrary.imageloader;

/**
 * 硬盘缓存策略枚举
 */
public enum DiskCacheStrategy {
    /**
     * 缓存所有版本图（默认行为）
     */
    ALL,

    /**
     * 不进行缓存
     */
    NONE,

    /**
     * 在解码之前将检索到的数据直接写入磁盘缓存。可以理解为只缓存原始图片。
     */
    DATA,

    /**
     * 缓存解码后的数据，可以理解为只缓存转换后的图片
     * Writes resources to disk after they've been decoded.
     */
    RESOURCE,

    /**
     * 根据数据源智能匹配
     */
    AUTOMATIC,
}
