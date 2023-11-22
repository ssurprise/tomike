package com.skx.common.loudspeaker

import java.util.*

/**
 * @Author： shiguotao
 * @E-mail: shiguotao-os@360os.com
 * @DATE： 2023/11/22 18:07
 * @Description： 大喇叭-自定义的一个全局被观察者类
 */
object LoudSpeaker : Observable() {

    fun sendMsg(txt: String) {
        this.setChanged()
        this.notifyObservers(txt)
    }
}