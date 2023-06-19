package com.skx.common.net.convert;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.$Gson$Types;
import com.skx.common.net.BaseResponse;
import com.skx.common.net.exception.ExceptionHandle;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 描述 : 自定义json解析转换器，用于对请求的数据解析为需要的类型
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/28 11:09 下午
 */
class MyResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;
    private final TypeAdapter<T> adapter;

    MyResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
        this.gson = gson;
        this.type = type;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String responseStr = value.string();
        if (TextUtils.isEmpty(responseStr)) {
            value.close();
            return null;
        }
        try {
            Log.d("http-converter", "response=" + responseStr);
            // 解析数据，返回类型脱壳
            BaseResponse response = gson.fromJson(responseStr, BaseResponse.class);
            if (response.isOk()) {
                Class<?> rawType = $Gson$Types.getRawType(type);// 接收方申明的实际类型
                if (BaseResponse.class.isAssignableFrom(rawType)) {
                    return gson.fromJson(responseStr, type);
                } else if (String.class.isAssignableFrom(rawType)) {
                    String dataJson = gson.toJson(response.getResult());
                    if (gson.fromJson(dataJson, type) == null) {
                        // 适用于接口返回成功，但是不需要返回实际数据情况。如{"code":0,"msg":"",data:null}
                        return (T) "";
                    }
                    return gson.fromJson(dataJson, type);
                } else {
                    if (response.getResult() != null) {
                        String dataJson = gson.toJson(response.getResult());
                        return gson.fromJson(dataJson, type);
                    }
                }
                return gson.fromJson(responseStr, type);
            } else {
                // 异常处理
                String msg = (TextUtils.isEmpty(response.getMsg()) ? "not errorMsg" : response.getMsg());
                throw new ExceptionHandle.ServerException(response.getCode(), msg);
            }
        } finally {
            value.close();
        }
    }
}