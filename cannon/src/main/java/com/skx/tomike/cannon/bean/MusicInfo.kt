package com.skx.tomike.cannon.bean


/**
 * 描述 : 本地音乐信息类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/27 9:06 下午
 */
data class MusicInfo(
        val musicId: String? = null,
        val fileUrl: String? = null,
        val displayName: String? = null,
        val title: String? = null,
        val album: String? = null,
        val artist: String? = null,
        val size: Long = 0,
        val duration: Int = 0
)