package com.skx.common.base

import com.skx.common.net.HttpManager
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType


/**
 * 描述 : 数据仓库-默认自带Retrofit 网络请求
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019/1/25 12:54 上午
 */
open class BaseRepository<S> : IRepository {

    private val mRetrofit: Retrofit? = HttpManager.getInstance()?.getClient()
    protected var service: S? = null

    init {
        try {
            // getGenericSuperclass() : 去获得父类的Type 对象
            this.javaClass.genericSuperclass?.run {
                if (this is ParameterizedType) {
                    val params = this.actualTypeArguments
                    if (params.isNotEmpty()) {
                        val clazz = params[0] as? Class<S>
                        service = mRetrofit?.create(clazz)
                    }
                }
            }

        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }
}