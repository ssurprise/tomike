package com.skx.common.net.convert;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 描述 : 解密转换器，用于对请求的数据先进行解密后再返回
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/28 11:21 下午
 */
class DecryptResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    DecryptResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        Log.e("ResponseBodyConverter", "init");
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        // todo 解密操作。
        Log.e("ResponseBodyConverter", "value=" + value.string());
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            T result = adapter.read(jsonReader);
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
            return result;
        } finally {
            value.close();
        }
    }
}
