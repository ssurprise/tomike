package com.skx.common.net.interceptor

import android.text.TextUtils
import android.util.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 描述 : OkHttp base_url 切换拦截器,用于动态域名下发
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/23 10:53 下午
 */
class BaseUrlInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request: Request = chain.request()
        Log.e("BaseUrlInterceptor", "原始url= ${request.url()}")
        Log.e("BaseUrlInterceptor", "原始headers= ${request.headers()}")

        val newBaseUrl = request.header("base_url")
        if (!TextUtils.isEmpty(newBaseUrl)) {
            // 2.构建新的请求url
            val domainUrl = HttpUrl.parse(newBaseUrl!!)
            domainUrl?.run {
                // 2.1 以旧的 httpUrl对象为基础，通过newBuilder()方法构建新的 HttpUrl 对象。目的：只改变需要修改的部分，保持未修改的数据不变。
                val httpUrl = request.url()
                val newUrl: HttpUrl = httpUrl.newBuilder()
                        .scheme(domainUrl.scheme())
                        .host(domainUrl.host())
                        .port(domainUrl.port())
                        .build()
                // 3. 通过Request.Builder对象修改 request信息的url
                val builder = request.newBuilder()
                builder.url(newUrl)
                // 4. 将配置的header(:base_url)删除
                builder.removeHeader("base_url")
                request = builder.build()
            }
            Log.e("BaseUrlInterceptor", "更新后的url= ${request.url()}")
            Log.e("BaseUrlInterceptor", "更新后的headers= ${request.headers()}")
        }
        return chain.proceed(request)
    }
}