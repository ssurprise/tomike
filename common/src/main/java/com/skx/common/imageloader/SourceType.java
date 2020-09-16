package com.skx.common.imageloader;

import androidx.annotation.Nullable;

/**
 * 描述 : ImageLoader 加载源接口
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2018/11/19 2:30 PM
 */
public interface SourceType<T> {

    @SuppressWarnings("unchecked")
    T load(@Nullable Object model);

}
