package com.skx.tomikecommonlibrary.imageloader;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 描述 : ImageLoader 加载源接口
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2018/11/19 2:30 PM
 */
public interface SourceType<T> {

    @NonNull
    @CheckResult
    @SuppressWarnings("unchecked")
    T load(@Nullable Object model);

}
