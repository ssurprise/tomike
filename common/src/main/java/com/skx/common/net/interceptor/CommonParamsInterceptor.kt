package com.skx.common.net.interceptor

import android.text.TextUtils
import okhttp3.*
import okio.Buffer
import org.json.JSONObject

/**
 * 描述 : 拼接公参拦截器
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/2 10:52 下午
 */
class CommonParamsInterceptor : Interceptor {

    companion object {
        private const val TAG = "TomikeInterceptor"

        const val REQUEST_METHOD_GET = "GET"
        const val REQUEST_METHOD_POST = "POST"

        private val MEDIA_TYPE: MediaType = MediaType.parse("application/json; charset=UTF-8")!!
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        //获取原先的请求对象
        val old: Request = chain.request()
        val new: Request = when (old.method()) {
            REQUEST_METHOD_GET -> {
                addGetCommonParams(old)
            }
            REQUEST_METHOD_POST -> {
                addPostCommonParams(old)
            }
            else -> {
                old
            }
        }
        return chain.proceed(new)
    }

    /**
     * 添加get请求的公参。
     * 公参的形式为请求url的query参数，通过addQueryParameter 来实现。
     */
    private fun addGetCommonParams(request: Request): Request {
        // todo 添加你所需要的公参，下面是随便搞的示例
        val httpUrl = request.url().newBuilder()
                .addQueryParameter("userId", "10086")
                .addQueryParameter("token", "xzw87xk6gtla12kjx")
                .addQueryParameter("sign", "usn930ksna1ifkl9")
                .build()
        return request.newBuilder().url(httpUrl).build()
    }


    /**
     * 添加post请求的公参
     * request.body() is FormBody 为true的条件为：在XXService 中将POST请求中设置
     * 1，请求参数注解为@FieldMap、@Field
     * 2，方法注解为@FormUrlEncoded
     */
    private fun addPostCommonParams(request: Request): Request {
        if (request.body() == null) {
            return request
        }
        return when (request.body()) {
            is FormBody -> {
                buildPostFormCommonParams(request)
            }
            is MultipartBody -> {
                // todo MultipartBody类型的暂时没有用到，先不处理！
                request
            }
            else -> {
                buildPostJsonCommonParams(request)
            }
        }
    }

    private fun buildPostFormCommonParams(request: Request): Request {
        // 1.创建新的请求体
        val formBodyBuilder = FormBody.Builder()
        // 2.将旧请求对象中的请求体参数添加到新创建的请求体中
        val body = request.body()
        if (body is FormBody) {
            for (i in 0 until body.size()) {
                formBodyBuilder.addEncoded(body.encodedName(i), body.encodedValue(i))
            }
        }
        // 3.todo 添加你所需要的公参
        formBodyBuilder.addEncoded("userId", "10086")
                .addEncoded("token", "xzw87xk6gtla12kjx")
                .addEncoded("sign", "usn930ksna1ifkl9")

        return request.newBuilder().post(formBodyBuilder.build()).build()
    }

    private fun buildPostJsonCommonParams(request: Request): Request {
        val contentType: MediaType? = request.body()?.contentType()
        if ("application".equals(contentType?.type(), ignoreCase = true)
                && "json".equals(contentType?.subtype(), ignoreCase = true)
        ) {
            // 1. 读取请求体的原有内容
            // 注：Retrofit 并没有直接提供获取请求体内容的方法，可通过Buffer获取
            val buffer = Buffer()
            request.body()?.run { this.writeTo(buffer) }
            val bodyString: String = buffer.readUtf8()
            // 2，解析原有请求参数
            val reqParams = try {
                if (!TextUtils.isEmpty(bodyString)) {
                    JSONObject(bodyString)
                } else {
                    JSONObject()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                JSONObject()
            }

            // 3.todo 添加你所需要的公参
            reqParams.put("userId", "10086")
            reqParams.put("token", "xzw87xk6gtla12kjx")
            reqParams.put("sign", "usn930ksna1ifkl9")

            val body: RequestBody = RequestBody.create(MEDIA_TYPE, reqParams.toString())
            return request.newBuilder().post(body).build()

        } else {
            return request
        }
    }
}