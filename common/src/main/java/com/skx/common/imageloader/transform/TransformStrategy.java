package com.skx.common.imageloader.transform;

/**
 * 作者：shiguotao
 * 日期：2018/11/7 10:51 AM
 * 描述：变换策略
 */
public enum TransformStrategy {
    /**
     * 无变换
     */
    NONE,

    /**
     * 不知道该怎么描述了，反正就是你想的那样
     */
    FIT_CENTER,

    /**
     * 均匀缩放图像（保持图像的纵横比），使图像的尺寸（宽度和高度）等于或大于视图的相应尺寸（减去填充）。 然后图像在视图中居中。
     */
    CENTER_CROP,

    /**
     * 均匀缩放图像（保持图像的纵横比），使图像的尺寸（宽度和高度）等于或小于视图的相应尺寸（减去填充）。 然后图像在视图中居中。
     */
    CENTER_INSIDE,

    /**
     * 圆形、裁剪
     */
    CIRCLE_CROP,

    /**
     * 自定义变换
     */
    CUSTOMIZATION,
}
