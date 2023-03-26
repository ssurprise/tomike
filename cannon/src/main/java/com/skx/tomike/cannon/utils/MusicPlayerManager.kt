package com.skx.tomike.cannon.utils

import android.content.Context
import android.media.MediaPlayer
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.skx.tomike.cannon.bean.MusicInfo


/**
 * 描述 : 音乐播放器manager，内部核心实现分为4部分：音乐列表数据、播放器、播放模式、播放状态通知
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 12:01 下午
 */
class MusicPlayerManager private constructor() {

    companion object {
        const val TAG = "MusicPlayerManager"

        val instance: MusicPlayerManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            MusicPlayerManager()
        }
    }

    /**
     * 播放列表管理器
     */
    private var mMusicList: MutableList<MusicInfo> = mutableListOf()

    /**
     * 播放模式，默认位顺序播放（循环）
     */
    private var mPlayMode: AbsPlayMode<MusicInfo> = OrderPlayMode()

    /**
     * 真正实现播放音乐功能的管理者
     */
    private var mPlayManager: IPlayManager<MusicInfo>? = PlayManagerImpl()

    /**
     * 播放状态LiveData
     */
    private val mPlayStateLiveData: MutableLiveData<PlayState<MusicInfo>> = MutableLiveData()
    private val mPlayStateObserverList: MutableList<Observer<PlayState<MusicInfo>>> = mutableListOf()

    // 表示当前播放的音乐在播放列表中的索引位置
    private var mIndex = -1


    init {
        mPlayStateLiveData.value = PlayState<MusicInfo>().also {
            // 初始化播放器状态为未播放状态
            it.state = 0
        }
        // 初始化数据源
        mPlayMode.registerMusicList(mMusicList)
    }

    fun registerMusicList(musicList: MutableList<MusicInfo>) {
        this.mMusicList = musicList
        // 数据源发生变化的情况下，需要更新播放模式中的数据源。
        this.mPlayMode.registerMusicList(mMusicList)
    }

    fun registerPlayManager(playManager: IPlayManager<MusicInfo>) {
        this.mPlayManager = playManager
    }

    fun setPlayMode(playMode: AbsPlayMode<MusicInfo>) {
        this.mPlayMode = playMode
        // 重置播放模式时，需要重新绑定播放模式中的播放数据源。
        this.mPlayMode.registerMusicList(mMusicList)
    }

    fun getPlayMode(): AbsPlayMode<MusicInfo> {
        return mPlayMode
    }

    fun addMusicList(musicList: List<MusicInfo>) {
        mMusicList.addAll(musicList)
    }

    fun play(t: MusicInfo?, autoAddIfNotExist: Boolean = false, playNow: Boolean = false) {
        if (t == null) {
            return
        }

        //1.检查该音频资源是否在播放列表里
        val indexOfKey = mMusicList.indexOf(t)

        //2.当前播放列表里有该音频资源,直接切换至该资源index 进行播放
        if (indexOfKey != -1) {
            mIndex = indexOfKey
            justPlay(mIndex)
            return
        }
        //3.当前播放列表中无此播放资源，判断是否需要追加。
        if (autoAddIfNotExist) {
            mMusicList.add(t)
        }
        //4.是否需要立即播放新添加的音乐
        if (autoAddIfNotExist && playNow) {
            mIndex = mMusicList.size - 1
            justPlay(mIndex)
        }
    }

    /**
     * 播放音乐。
     * 该方法只负责播放目标音频资源及播放状态更新，不做其他逻辑处理
     */
    private fun justPlay(index: Int) {
        if (mIndex < 0 || mIndex > mMusicList.size - 1) {
            Log.e(TAG, "index:${index} 越界，无法执行播放任务")
            return
        }

        val cur = mMusicList[index]
        // 更新播放状态 -> 播放中
        notifyPlayStatus(cur)

        Log.e(TAG, "播放音乐.name:${cur.title}")
        mPlayManager?.play(cur)
    }

    /**
     * 暂停播放。
     * 该方法只负责暂停播放及播放状态更新，不做其他逻辑处理
     */
    private fun justPause() {
        Log.e(TAG, "暂停播放.")
        mPlayManager?.pause()
        // 更新播放状态 -> 暂停
        notifyPauseStatus()
    }

    /**
     * 播放或者暂停，大概其类似播放中的状态点击就暂停，暂停的状态下再点击就继续播放
     * 注：犹豫要不要写这个逻辑，总能感觉这是偏向于业务处理的。暂且留ta一命吧！
     */
    fun playOrPause() {
        if (mIndex != -1
                && mPlayManager?.isPlaying() == true
                && mPlayStateLiveData.value?.state == 1
        ) {
            // 播放中 -> 暂停
            justPause()
            return
        }

        // 如下需要播放音乐
        // 1. 安全校验，播放列表有数据，但是index 处于初始话状态，自动初始化。
        if (mIndex == -1 && mMusicList.size > 0) {
            mIndex = 0
        }

        // 2. 播放音乐
        justPlay(mIndex)
    }

    /**
     * 通知播放状态更新至播放中状态
     */
    private fun notifyPlayStatus(cur: MusicInfo) {
        var state: PlayState<MusicInfo>? = mPlayStateLiveData.value
        if (state == null) {
            state = PlayState()
        }
        state.state = PlayState.PLAYING
        state.value = cur
        mPlayStateLiveData.postValue(state)
    }

    /**
     * 通知播放状态更新至暂停状态
     */
    private fun notifyPauseStatus() {
        var state: PlayState<MusicInfo>? = mPlayStateLiveData.value
        if (state == null) {
            state = PlayState()
        }
        state.state = PlayState.PAUSE
        mPlayStateLiveData.postValue(state)
    }

    /**
     * 播放下一首
     */
    fun next() {
        Log.e(TAG, "播放下一首...")
        val next = mPlayMode.next(mIndex)
        if (next == -1) {
            Log.e(TAG, "获取下一首音乐失败：next=${next}")
            return
        }
        mIndex = next
        justPlay(mIndex)
    }

    /**
     * 播放前一首
     */
    fun prev() {
        Log.e(TAG, "播放上一首...")
        val prev = mPlayMode.prev(mIndex)
        if (prev == -1) {
            Log.e(TAG, "获取上一首音乐失败：prev=${prev}")
            return
        }
        mIndex = prev
        justPlay(mIndex)
    }

    fun getPlayStateLiveData(): MutableLiveData<PlayState<MusicInfo>> {
        return mPlayStateLiveData
    }

    /**
     * 添加播放状态观察者
     * 注意：该方法添加的是未绑定lifecycle的观察者，此种方式的观察者在不用时一定要注意移除，防止内存泄漏。
     * 如需使用绑定lifecycle，可通过 {@link #getPlayStateLiveData()} 方法自行处理。
     */
    fun addPlayStateObserver(observer: Observer<PlayState<MusicInfo>>) {
        mPlayStateLiveData.observeForever(observer)
        mPlayStateObserverList.add(observer)
    }

    fun removePlayStateObserver(observer: Observer<PlayState<MusicInfo>>) {
        mPlayStateLiveData.removeObserver(observer)
        mPlayStateObserverList.remove(observer)
    }

    /**
     * 释放播放器资源，重置播放状态，清空播放数据
     */
    fun release() {
        mPlayManager?.release()
        mIndex = -1
        val state: PlayState<MusicInfo>? = mPlayStateLiveData.value
        state?.run {
            this.state = PlayState.NON_PLAY
            this.value = null
        }
        // 先发送未播放的状态通知
        mPlayStateLiveData.postValue(state)
        // 移除观察者
        for (item in (mPlayStateObserverList.size - 1) downTo 0) {
            val observer = mPlayStateObserverList[item]
            mPlayStateLiveData.removeObserver(observer)
            mPlayStateObserverList.removeAt(item)
        }
    }
}

class PlayState<T> {

    companion object {
        /**
         * 未播放状态：注意区分该状态不同于暂停状态，乃是没有任何播放资源的初始状态
         */
        const val NON_PLAY = 0

        /**
         * 播放中状态
         */
        const val PLAYING = 1

        /**
         * 暂停状态
         */
        const val PAUSE = 2
    }

    // 播放状态：0-未播放；1-播放中；2-暂停
    var state = 0
    var value: T? = null
}


/**
 * 描述 : 播放器-播放功能管理类接口
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 11:18 下午
 */
interface IPlayManager<T> {

    /**
     * 播放
     */
    fun play(t: T?)

    /**
     * 暂停
     */
    fun pause()

    /**
     * 当前是否正在播放中
     */
    fun isPlaying(): Boolean

    /**
     * 释放播放器资源
     */
    fun release()
}


/**
 * 描述 : 默认播放器-播放功能实现类（MediaPlayer实现）
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 11:18 下午
 */
class PlayManagerImpl : IPlayManager<MusicInfo> {

    private val TAG = "PlayManager"

    private var mPlayer: MediaPlayer = MediaPlayer()
    private var isPause = false

    init {
        mPlayer.setOnCompletionListener {
            isPause = false
            Log.e("music-player", "播放结束")
        }
        mPlayer.setOnErrorListener { mp, what, extra ->
            Log.e("music-player", "播放错误")
            false
        }
        mPlayer.setOnPreparedListener {
            Log.e("music-player", "播放之前...")
        }
        mPlayer.setOnBufferingUpdateListener { mp, percent -> }
    }


    /**
     * 播放音乐
     *
     * 注意：
     * 如果在暂停的时候使用多是stop，则在start之前必须重新prepare，否则报错:Media Player called in state 8
     * 如果暂停使用pause，那么直接start就可以，不用prepare
     *
     * @param t 目标资源
     */
    override fun play(t: MusicInfo?) {
        if (t == null || TextUtils.isEmpty(t.fileUrl) || TextUtils.isEmpty(t.musicId)) {
            return
        }
        mPlayer.run {
            Log.e(TAG, "file-url:" + t.fileUrl)
            if (isPlaying) {
                // 当前正在播放中需要重置状态
                reset()
            }
            if (!isPause) {
                reset()
                setDataSource(t.fileUrl)
                prepare()
            }
            isPause = false
            start()
        }
    }

    override fun pause() {
        if (isPlaying()) {
            isPause = true
            mPlayer.pause()
        }
    }

    override fun release() {
        mPlayer.stop()
        mPlayer.reset()
        mPlayer.release()
    }

    override fun isPlaying(): Boolean {
        return mPlayer.isPlaying
    }
}



