package com.skx.tomike.missile.pattern.memento

/**
 * 描述 : 备忘录模式-发起者（Originator）类。负责创建一个备忘录Memento，用以记录当前时刻自身的内部状态，
 * 并可使用备忘录恢复内部状态。Originator可以根据需要决定Memento存储自己的哪些内部状态。
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 2:05 PM
 */
internal class Player {
    var name: String? = null
    var bloodVolume: Int = 100// 血量 = 0
    var equipment: ArrayList<String> = ArrayList() // 装备

    /**
     * 创建备份数据
     *
     * @return 备份数据
     */
    fun createMemento(): PlayerMemento {
        val playerMemento = PlayerMemento()
        playerMemento.bloodVolume = bloodVolume
        playerMemento.equipment.addAll(equipment)
        return playerMemento
    }

    /**
     * 从备份获取数据
     *
     * @param playerMemento 备份数据
     */
    fun setMemento(playerMemento: PlayerMemento?) {
        if (playerMemento == null) return
        bloodVolume = playerMemento.bloodVolume!!
        equipment.clear()
        equipment.addAll(playerMemento.equipment)
    }
}