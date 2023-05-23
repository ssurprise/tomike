package com.skx.common.base.d

import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.internal.bind.TypeAdapters.newFactory
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException


private val STRING: TypeAdapter<String> = object : TypeAdapter<String>() {
    @Throws(IOException::class)
    override fun read(input: JsonReader): String {
        val peek = input.peek()
        if (peek == JsonToken.NULL) {
            input.nextNull()
            return ""
        }
        /* coerce booleans to strings for backwards compatibility */
        return if (peek == JsonToken.BOOLEAN) {
            java.lang.Boolean.toString(input.nextBoolean())
        } else input.nextString()
    }

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: String?) {
        out.value(value)
    }
}

private val STRING_FACTORY: TypeAdapterFactory = newFactory(String::class.java, STRING)

fun getStringFactory(): TypeAdapterFactory {
    return STRING_FACTORY
}





