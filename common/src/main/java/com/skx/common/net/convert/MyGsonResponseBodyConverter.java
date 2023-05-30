package com.skx.common.net.convert;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.skx.common.net.BaseResponse;
import com.skx.common.net.exception.ExceptionHandle;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private Gson gson;
    private Type type;

    public MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String responseBody = value.string();
        if (TextUtils.isEmpty(responseBody)) {
            value.close();
            return null;
        }
        try {
            BaseResponse response = gson.fromJson(responseBody, BaseResponse.class);
            if (!response.isOk()) {
                String msg = (TextUtils.isEmpty(response.getReason()) ? "not errorMsg" : response.getReason());
//                if (RetrofitManager.getInstance().getErrorResponse() != null) {
//                    RetrofitManager.getInstance().getErrorResponse().getErrorMsg(response.getStatusCode());
//                }
                throw new ExceptionHandle.ServerException(response.getStatus(), msg, response.getResult() != null ? response.getResult().toString() : "");
            } else if (response.isOk()) {
                return fromJson(responseBody, type);
            }
        } finally {
            value.close();
        }
        return null;
    }

    public <T> T fromJson(String in, Type type) {
        Class<?> rawType = $Gson$Types.getRawType(type);
        BaseResponse baseEntity = gson.fromJson(in, BaseResponse.class);
        String dataJson = gson.toJson(baseEntity.getResult());
        if (BaseResponse.class.isAssignableFrom(rawType)) {
            return gson.fromJson(in, type);
        } else if (String.class.isAssignableFrom(rawType)) {
            if (gson.fromJson(dataJson, type) == null) {
                return (T) "";
            }
            return (T) gson.fromJson(dataJson, type);
        } else {
            if (baseEntity.getResult() != null) {
                return gson.fromJson(dataJson, type);
            }
        }
        return gson.fromJson(dataJson, type);
    }
}