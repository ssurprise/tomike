package com.skx.common.base.d;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class MyGsonConverterFactory extends Converter.Factory {

    public static MyGsonConverterFactory create(ConverterFactoryInterceptor interceptor) {
        return create(new GsonBuilder().registerTypeAdapterFactory(GsonTypeAdaptersKt.getStringFactory()).create(), interceptor);
    }

    public static MyGsonConverterFactory create(Gson gson, ConverterFactoryInterceptor interceptor) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new MyGsonConverterFactory(gson, interceptor);
    }

    private MyGsonConverterFactory(Gson gson, ConverterFactoryInterceptor interceptor) {
        this.gson = gson;
        this.mInterceptor = interceptor;
    }

    private final Gson gson;
    private final ConverterFactoryInterceptor mInterceptor;

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (mInterceptor != null) {
            Converter<ResponseBody, ?> converter = mInterceptor.intercept(type,gson);
            if (converter != null) {
                return converter;
            }
        }
        return new MyGsonResponseBodyConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyGsonRequestBodyConverter<>(gson, adapter);
    }
}