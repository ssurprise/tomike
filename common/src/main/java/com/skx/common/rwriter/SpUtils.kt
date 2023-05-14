package com.skx.common.rwriter

import android.content.Context
import android.content.SharedPreferences
import kotlin.Int as Int1


/**
 * 描述 : SharePreferences 工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/10 11:29 下午
 */
object SpUtils {

    private const val KEY_DEFAULT = "default_config"

    fun getClient(ctx: Context, name: String = KEY_DEFAULT): Client {
        return Client(ctx, name)
    }

    class Client(ctx: Context, name: String) {

        private var sp: SharedPreferences? = null
        private var mMap: MutableMap<String, Any?> = mutableMapOf()

        init {
            sp = ctx.getSharedPreferences(name, Context.MODE_PRIVATE)
            mMap = mutableMapOf()
        }

        fun putString(key: String, value: String?): Client {
            mMap[key] = value
            return this
        }

        fun putStringSet(key: String, values: MutableSet<String>?): Client {
            mMap[key] = values
            return this
        }

        fun putInt(key: String, value: Int1): Client {
            mMap[key] = value
            return this
        }

        fun putLong(key: String, value: Long): Client {
            mMap[key] = value
            return this
        }

        fun putFloat(key: String, value: Float): Client {
            mMap[key] = value
            return this
        }

        fun putBoolean(key: String, value: Boolean): Client {
            mMap[key] = value
            return this
        }

        fun remove(key: String): Client {
            mMap.remove(key)
            return this
        }

        fun clear(): Client {
            mMap.clear()
            return this
        }

        fun commit(): Boolean {
            return true
        }

        fun apply() {
            sp?.edit()?.run {
                mMap.forEach {
                    when (it.value) {
                        is kotlin.Int -> {
                            this.putInt(it.key, it.value as kotlin.Int)
                        }
                        is Long -> {
                            this.putLong(it.key, it.value as Long)
                        }
                        is String -> {
                            this.putString(it.key, it.value as String)
                        }
                        is Float -> {
                            this.putFloat(it.key, it.value as Float)
                        }
                        is Boolean -> {
                            this.putBoolean(it.key, it.value as Boolean)
                        }
                        is Set<*> -> {
                            this.putStringSet(it.key, it.value as Set<String>)
                        }
                    }
                }
                this.apply()
            }
        }

//        private fun getEditor():SharedPreferences.Editor{
//            val edit = sp.edit()
//            mMap.forEach {
//                when (it.value) {
//                    is kotlin.Int -> {
//                        edit.putInt(it.key, it.value as kotlin.Int)
//                    }
//                    is Long -> {
//                        edit.putLong(it.key, it.value as Long)
//                    }
//                    is String -> {
//                        edit.putString(it.key, it.value as String)
//                    }
//                    is Float -> {
//                        edit.putFloat(it.key, it.value as Float)
//                    }
//                    is Boolean -> {
//                        edit.putBoolean(it.key, it.value as Boolean)
//                    }
//                    is Set<*> -> {
//                        edit.putStringSet(it.key, it.value as Set<String>)
//                    }
//                }
//            }
//            return edit
//        }

        fun getString(key: String, defValue: String?): String? {
            return sp?.getString(key, defValue) ?: defValue
        }

        fun getStringSet(key: String, defValues: MutableSet<String>?): MutableSet<String>? {
            return sp?.getStringSet(key, defValues) ?: defValues
        }

        fun getInt(key: String, defValue: Int1): Int1 {
            return sp?.getInt(key, defValue) ?: defValue
        }

        fun getLong(key: String?, defValue: Long): Long {
            return sp?.getLong(key, defValue) ?: defValue
        }

        fun getFloat(key: String?, defValue: Float): Float {
            return sp?.getFloat(key, defValue) ?: defValue
        }

        fun getBoolean(key: String?, defValue: Boolean): Boolean {
            return sp?.getBoolean(key, defValue) ?: defValue
        }

        fun contains(key: String): Boolean {
            return sp?.contains(key) ?: false
        }
    }
}