package com.skx.common.base.d;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 转换拦截器
 * 对于一些特殊,特别的接口请求,自己解析
 * 根据其中的type来判断
 */
public interface ConverterFactoryInterceptor {
    /**
     * @param type 用来判断,是否
     * @param gson 解析数据所用
     * @return 决定解析当前 type 返回 不为空的值, 不处理则返回 null
     */
    Converter<ResponseBody, ?> intercept(Type type, Gson gson);
}
