package com.skx.common.rwriter

import android.content.Context
import com.tencent.mmkv.MMKV

/**
 * 描述 : MMKV 工具类，官网介绍：https://github.com/Tencent/MMKV/wiki/android_tutorial_cn
 * 数据结构ProtoBuf：https://www.jianshu.com/p/a24c88c0526a
 *
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/10/14 5:31 PM
 */

fun initMmkv(context: Context) {
    MMKV.initialize(context)
}

fun save(key: String, value: String) {
    // MMKV 提供一个全局的实例，可以直接使用
//    val defaultMMKV = MMKV.defaultMMKV()

    // 如果不同业务需要区别存储，也可以单独创建自己的实例
    val defaultMMKV = MMKV.mmkvWithID("MyID")
}


/**
 * 获取 MMKV 实例对象
 *
 * 1.MMKV 提供了 importFromSharedPreferences() 函数，可以比较方便地迁移数据过来。
 *
 * 2.MMKV 还额外实现了一遍 SharedPreferences、SharedPreferences.Editor 这两个 interface，在迁移的时候只需两三行代码即可，其他 CRUD 操作代码都不用改。
 *
 */
fun getInstance(name: String): MMKV {
    /*
    //SharedPreferences preferences = getSharedPreferences("myData", MODE_PRIVATE);
    MMKV preferences = MMKV.mmkvWithID("myData");
    // 迁移旧数据
    {
        SharedPreferences old_man = getSharedPreferences("myData", MODE_PRIVATE);
        preferences.importFromSharedPreferences(old_man);
        old_man.edit().clear().commit();
    }
    // 跟以前用法一样
    SharedPreferences.Editor editor = preferences.edit();
    editor.putBoolean("bool", true);
    editor.putInt("int", Integer.MIN_VALUE);
    editor.putLong("long", Long.MAX_VALUE);
    editor.putFloat("float", -3.14f);
    editor.putString("string", "hello, imported");
    HashSet<String> set = new HashSet<String>();
    set.add("W"); set.add("e"); set.add("C"); set.add("h"); set.add("a"); set.add("t");
    editor.putStringSet("string-set", set);
    // 无需调用 commit()
    //editor.commit();
     */
    return MMKV.mmkvWithID(name)
}


