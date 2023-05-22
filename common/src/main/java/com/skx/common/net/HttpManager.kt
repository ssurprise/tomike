package com.skx.common.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * 描述 : 网络请求管理类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/22 11:19 下午
 */
class HttpManager private constructor(config: NetConfig) {

    private var mRetrofitClient: Retrofit
    private var okHttpClient: OkHttpClient
    private var errorResponse: IResponseErrorMsg? = null


    companion object {

        private var instance: HttpManager? = null

        @JvmStatic
        fun init(config: NetConfig) {
            if (instance == null) {
                synchronized(HttpManager::class.java) {
                    if (instance == null) {
                        instance = HttpManager(config)
                    }
                }
            }
        }

        @JvmStatic
        fun getInstance(): HttpManager? {
            if (instance == null) {
                throw NullPointerException("you need initialize HttpManager")
            }
            return instance
        }
    }


    init {
        errorResponse = config.getiResponseErrorMsg()
        val builder: OkHttpClient.Builder = if (config.builder == null) {
            OkHttpClient.Builder()
        } else {
            config.builder
        }
        okHttpClient = builder.build()
        mRetrofitClient = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(config.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(MyGsonConverterFactory.create(config.getInterceptorConverter()))
                .build()
    }

    fun getClient(): Retrofit {
        return mRetrofitClient
    }

    fun <T> createApi(clazz: Class<out T>?): T {
        return mRetrofitClient.create(clazz)
    }

}