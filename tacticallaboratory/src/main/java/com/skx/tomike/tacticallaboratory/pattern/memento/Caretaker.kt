package com.skx.tomike.tacticallaboratory.pattern.memento

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * 描述 : 备忘录管理者。负责备忘录Memento 添加和读取，但是不能对Memento的内容进行访问或者操作。
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 2:04 PM
 */
internal class Caretaker {

    /**
     * 备份容器
     */
    private val mPlayerMementoMap: LinkedHashMap<String, PlayerMemento> = LinkedHashMap()

    /**
     * 添加备份
     *
     * @param playerMemento 备份
     */
    @SuppressLint("SimpleDateFormat")
    fun addPlayerMemento(playerMemento: PlayerMemento) {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        mPlayerMementoMap[dateFormat.format(calendar.time)] = playerMemento
    }

    /**
     * 获取指定备份
     *
     * @param key 在map 中检索的key
     * @return 指定时间的备份
     */
    fun getPlayerMementoByKey(key: String?): PlayerMemento? {
        return if (key == null) null else mPlayerMementoMap[key]
    }

    /**
     * 获取全部备份
     *
     * @return 所有备份
     */
    fun getPlayerMemento(): LinkedHashMap<String, PlayerMemento>? {
        return mPlayerMementoMap
    }

    /**
     * 删除指定备份
     *
     * @param key 要从map中删除其映射的key
     */
    fun removeMemento(key: String?) {
        mPlayerMementoMap.remove(key)
    }

    /**
     * 清除备份
     */
    fun clearMemento() {
        mPlayerMementoMap.clear()
    }
}