package com.skx.common.net.interceptor

import android.util.Log
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 描述 : 拼接公参拦截器
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/29 10:52 下午
 */
class CommonParamsInterceptor : Interceptor {

    companion object {
        const val REQUEST_METHOD_GET = "GET"
        const val REQUEST_METHOD_POST = "POST"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val method = request.method()
        if (REQUEST_METHOD_GET == method) {
            request = addGetCommonParams(request)
            Log.e("CommonParamsInterceptor", "更新后的url= ${request.url()}")
        } else if (REQUEST_METHOD_POST == method) {
            request = addPostCommonParams(request)
        }
        return chain.proceed(request)
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
        val body = request.body()
        if (body is FormBody) {
            // 1.创建新的请求体
            val formBodyBuilder = FormBody.Builder()
            // 2.将旧请求对象中的请求体参数添加到新创建的请求体中
            for (i in 0 until body.size()) {
                formBodyBuilder.addEncoded(body.encodedName(i), body.encodedValue(i))
            }
            // 3.todo 添加你所需要的公参
            formBodyBuilder.addEncoded("userId", "10086")
                    .addEncoded("token", "xzw87xk6gtla12kjx")
                    .addEncoded("sign", "usn930ksna1ifkl9")

            return request.newBuilder().post(formBodyBuilder.build()).build()
        }
        return request
    }
}