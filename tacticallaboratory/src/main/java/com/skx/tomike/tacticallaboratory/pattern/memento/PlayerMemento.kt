package com.skx.tomike.tacticallaboratory.pattern.memento

/**
 * 描述 : 备忘录类 Memento，负责存储Originator对象的内部状态，并可以防止Originator以外的其他对象访问备忘录。
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 2:04 PM
 */
class PlayerMemento {
    var bloodVolume: Int? = null // 生命值、血量
    var equipment: ArrayList<String> = ArrayList() // 装备
}