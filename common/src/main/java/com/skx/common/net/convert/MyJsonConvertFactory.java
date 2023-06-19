package com.skx.common.net.convert;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 描述 : Json解析脱壳 Converter
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/28 11:09 下午
 */
public class MyJsonConvertFactory extends Converter.Factory {

    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static MyJsonConvertFactory create() {
        return create(new Gson());
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static MyJsonConvertFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new MyJsonConvertFactory(gson);
    }


    private final Gson gson;

    private MyJsonConvertFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public @Nullable
    Converter<ResponseBody, ?> responseBodyConverter(@NonNull Type type,
                                                     @NonNull Annotation[] annotations,
                                                     @NonNull Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyResponseBodyConverter<>(gson, adapter, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(@NonNull Type type,
                                                          @NonNull Annotation[] parameterAnnotations,
                                                          @NonNull Annotation[] methodAnnotations,
                                                          @NonNull Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyRequestBodyConverter<>(gson, adapter);
    }
}