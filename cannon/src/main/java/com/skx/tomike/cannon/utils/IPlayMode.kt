package com.skx.tomike.cannon.utils

import com.skx.tomike.cannon.bean.MusicInfo


/**
 * 描述 : 音乐播放器 - 播放模式接口(用于设计单曲循环、列表循环、随机播放)
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 12:01 下午
 */
interface IPlayMode<T> {

    /**
     * 获取当前索引的上（前）一个索引
     */
    fun prev(cur: Int): Int = -1

    /**
     * 获取当前索引的下（后）一个索引
     */
    fun next(cur: Int): Int = -1

}

abstract class AbsPlayMode<T> : IPlayMode<T> {

    protected var mMusicList: List<T>? = null

    fun registerMusicList(musicList: List<T>) {
        this.mMusicList = musicList
    }
}


/**
 * 描述 : 音乐播放器 - 顺序播放(循环)
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 12:01 下午
 */
class OrderPlayMode : AbsPlayMode<MusicInfo>() {

    /**
     * 如果当前索引（cur）在数据列表范围内，取后一个，如果当前是最一个，则返回第一个
     * 如果当前索引（cur）不在数据列表范围内，直接返回-1。
     *
     * @param cur 当前索引
     * @return 下一个索引
     */
    override fun next(cur: Int): Int {
        mMusicList?.takeIf {
            cur >= 0 && cur < it.size
        }?.run {
            // 如果当前是最一个，则再向后的话返回第一个，如此循环
            if (cur == this.size - 1) {
                return 0
            }
            return cur + 1
        }
        return -1
    }

    /**
     * 如果当前索引（cur）在数据列表范围内，取前一个，如果当前已经是第一个，则最后返回一个
     * 如果当前索引（cur）不在数据列表范围内，直接返回-1。
     *
     * @param cur 当前索引
     * @return 前（上）一个索引
     */
    override fun prev(cur: Int): Int {
        mMusicList?.takeIf {
            cur >= 0 && cur < it.size
        }?.run {
            // 如果当前是第一个，则再向前的话返回最后一个，如此循环
            if (cur == 0) {
                return this.size - 1
            }
            return cur - 1
        }
        return -1
    }
}

/**
 * 描述 : 音乐播放器 - 单曲循环播放，上（前）一个、下（后）一个均返回当前未知
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 12:01 下午
 */
class SingleCycleMode : AbsPlayMode<MusicInfo>() {

    /**
     * 角标越界的情况下返回-1
     */
    override fun next(cur: Int): Int {
        mMusicList?.takeIf {
            cur >= 0 && cur < it.size
        }?.run {
            return cur
        }
        return -1
    }

    /**
     * 角标越界的情况下返回-1
     */
    override fun prev(cur: Int): Int {
        mMusicList?.takeIf {
            cur >= 0 && cur < it.size
        }?.run {
            return cur
        }
        return -1
    }
}

/**
 * 描述 : 音乐播放器 - 单曲循环播放
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 12:01 下午
 */
class RandomPlayMode : AbsPlayMode<MusicInfo>() {

    override fun next(cur: Int): Int {
        mMusicList?.takeIf {
            cur >= 0 && cur < it.size
        }?.run {
            val randomIndex = getRandomIndex()

            if (cur == randomIndex || randomIndex == -1) {
                return when {
                    (cur + 1 > this.size - 1) && (cur + 1 < 0) -> {
                        cur
                    }
                    (cur + 1 > this.size) -> {
                        cur - 1
                    }
                    else -> {
                        cur + 1
                    }
                }
            }
            return randomIndex
        }
        return -1
    }

    override fun prev(cur: Int): Int {
        mMusicList?.takeIf {
            cur >= 0 && cur < it.size
        }?.run {
            val randomIndex = getRandomIndex()
            if (cur == randomIndex || randomIndex == -1) {
                // 越界或者值相等时的边界处理
                return when {
                    (cur + 1 > this.size - 1) && (cur + 1 < 0) -> {// 越界
                        cur
                    }
                    (cur + 1 > this.size) -> {// 向上越界
                        cur - 1
                    }
                    else -> {// 向下越界
                        cur + 1
                    }
                }
            }
            return randomIndex
        }
        return -1
    }

    private fun getRandomIndex(): Int {
        mMusicList?.run {
            return this.indices.random()
        }
        return -1
    }
}