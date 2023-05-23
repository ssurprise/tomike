package com.skx.common.base.d;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

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
//        try {
//            BaseBean response = gson.fromJson(responseBody, BaseBean.class);
//            if (!response.isOk()) {
//                String msg = (TextUtils.isEmpty(response.getErrorMsg()) ? "not errorMsg" : response.getErrorMsg());
//                if (RetrofitManager.getInstance().getErrorResponse() != null) {
//                    RetrofitManager.getInstance().getErrorResponse().getErrorMsg(response.getStatusCode());
//                }
//                throw new ExceptionHandle.ServerException(response.getStatusCode(), msg, response.content != null ? response.content.toString() : "");
//            } else if (response.isOk()) {
//                return fromJson(responseBody, type);
//            }
//        } finally {
//            value.close();
//        }
        return null;
    }

    public <T> T fromJson(String in, Type type) {
//        Class<?> rawType = $Gson$Types.getRawType(type);
//        BaseBean baseEntity = gson.fromJson(in, BaseBean.class);
//        String dataJson = gson.toJson(baseEntity.getData());
//        if (BaseBean.class.isAssignableFrom(rawType)) {
//            return gson.fromJson(in, type);
//        } else if (String.class.isAssignableFrom(rawType)) {
//            if (gson.fromJson(dataJson, type) == null) {
//                return (T) "";
//            }
//            return (T) gson.fromJson(dataJson, type);
//        } else {
//            if (baseEntity.getData() != null) {
//                return gson.fromJson(dataJson, type);
//            }
//        }
//        return gson.fromJson(dataJson, type);
        return null;
    }
}