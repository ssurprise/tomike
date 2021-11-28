package com.skx.tomike.cannon.utils

import android.content.Context
import android.media.MediaPlayer
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.skx.tomike.cannon.bean.MusicInfo


/**
 * 描述 : 音乐播放器manager
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 12:01 下午
 */
class MusicPlayerManager private constructor() {

    companion object {
        const val TAG = "MusicPlayerManager"

        val DEFAULT_LIST_MANAGER: IMusicListManager<MusicInfo> = MusicListManagerImpl()
        val instance: MusicPlayerManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            MusicPlayerManager()
        }
    }

    /**
     * 播放列表管理器
     */
    private var musicListManager: IMusicListManager<MusicInfo> = DEFAULT_LIST_MANAGER

    /**
     * 真正实现播放音乐功能的管理者
     */
    private var playManager: IPlayManager<MusicInfo>? = PlayManagerImpl()

    /**
     * 播放状态LiveData
     */
    private val mPlayStateLiveData: MutableLiveData<PlayState<MusicInfo>> = MutableLiveData()

    // 表示当前播放的音乐在播放列表中的索引位置
    private var mIndex = -1

    // 自动播放
    private var autoplay = true

    init {
        mPlayStateLiveData.value = PlayState<MusicInfo>().also {
            // 初始化播放器状态为未播放状态
            it.state = 0
        }
    }

    fun registerMusicList(musicListManager: IMusicListManager<MusicInfo>) {
        this.musicListManager = musicListManager
    }

    fun registerPlayManager(playManager: IPlayManager<MusicInfo>) {
        this.playManager = playManager
    }

    fun addMusicList(musicList: List<MusicInfo>) {
        musicListManager.addAll(musicList)
    }

    fun play(t: MusicInfo?, autoAddIfNotExist: Boolean = false, playNow: Boolean = false) {
        if (t == null) {
            return
        }

        //1.检查该音频资源是否在播放列表里
        val indexOfKey = musicListManager.indexOfKey(t)

        //2.当前播放列表里有该音频资源,直接切换至该资源index 进行播放
        if (indexOfKey != -1) {
            mIndex = indexOfKey
            justPlay(mIndex)
            return
        }
        //3.当前播放列表中无此播放资源，判断是否需要追加。
        if (autoAddIfNotExist) {
            musicListManager.add(t)
        }
        //4.是否需要立即播放新添加的音乐
        if (autoAddIfNotExist && playNow) {
            mIndex = musicListManager.getSize() - 1
            justPlay(mIndex)
        }
    }

    /**
     * 播放音乐。
     * 该方法只负责播放目标音频资源及播放状态更新，不做其他逻辑处理
     */
    private fun justPlay(index: Int) {
        if (mIndex < 0 || mIndex > musicListManager.getSize() - 1) {
            Log.e(TAG, "index 越界，无法执行播放任务")
            return
        }

        val cur = musicListManager.get(index)
        cur?.run {
            // 更新播放状态 -> 播放中
            notifyPlayStatus(this)
        }
        Log.e(TAG, "播放音乐.name:${cur?.title}")
        playManager?.play(cur)
    }

    /**
     * 暂停播放。
     * 该方法只负责暂停播放及播放状态更新，不做其他逻辑处理
     */
    private fun justPause() {
        Log.e(TAG, "暂停播放.")
        playManager?.pause()
        // 更新播放状态 -> 暂停
        notifyPauseStatus()
    }

    /**
     * 播放或者暂停，大概其类似播放中的状态点击就暂停，暂停的状态下再点击就继续播放
     * 注：犹豫要不要写这个逻辑，总能感觉这是偏向于业务处理的。暂且留ta一命吧！
     */
    fun playOrPause() {
        if (playManager?.isPlaying() == true
                && mPlayStateLiveData.value?.state == 1) {
            // 播放中 -> 暂停
            justPause()
            return
        }

        // 如下需要播放音乐
        // 1. 安全校验，播放列表有数据，但是index 处于初始话状态，自动初始化。
        if (mIndex == -1 && musicListManager.getSize() > 0) {
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
        state.state = 1
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
        state.state = 2
        mPlayStateLiveData.postValue(state)
    }

    /**
     * 播放下一首
     */
    fun next() {
        if (mIndex + 1 > musicListManager.getSize()) {
            // todo 缺失播放列表循环功能，后续补齐
            return
        }
        Log.e(TAG, "播放下一首.")
        justPlay(++mIndex)
    }

    /**
     * 播放前一首
     */
    fun prev() {
        if (mIndex - 1 < 0) {
            // todo 缺失播放列表循环功能，后续补齐
            return
        }
        Log.e(TAG, "播放上一首.")
        justPlay(--mIndex)
    }

    fun getPlayStateLiveData(): MutableLiveData<PlayState<MusicInfo>> {
        return mPlayStateLiveData
    }

    /**
     * 释放播放器资源
     */
    fun release() {
        playManager?.release()
    }
}

class PlayState<T> {
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
     * 注册播放列表
     */
    fun init()

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

    fun init(context: Context) {
        mPlayer.setOnCompletionListener {
            Log.e("music-player", "播放结束")
        }
        mPlayer.setOnErrorListener { mp, what, extra ->
            false
        }
        mPlayer.setOnPreparedListener {

        }
        mPlayer.setOnBufferingUpdateListener { mp, percent -> }
    }

    override fun init() {}

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
            if (this@PlayManagerImpl.isPlaying()) {
                // 当前正在播放中需要重置状态
                reset()
            }
            if (!isPause) {
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
        mPlayer.release()
    }

    override fun isPlaying(): Boolean {
        return mPlayer.isPlaying
    }
}

/**
 * 描述 : 播放器-播放列表管理类接口
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 11:17 下午
 */
interface IMusicListManager<T> {

    fun add(t: T)

    fun addAll(t: Collection<T>)

    fun remove(t: T)

    /**
     * 移除指定位置的音乐资源
     */
    fun remove(index: Int)

    fun getSize(): Int

    fun get(index: Int): T?

    /**
     * 获取该资源对应的位置
     */
    fun indexOfKey(t: T): Int
}

/**
 * 描述 : 默认播放器-播放列表实现类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 11:17 下午
 */
class MusicListManagerImpl : IMusicListManager<MusicInfo> {

    private val mList: MutableList<MusicInfo> = mutableListOf()

    override fun add(t: MusicInfo) {
        mList.add(t)
    }

    override fun addAll(t: Collection<MusicInfo>) {
        mList.addAll(t)
    }

    override fun remove(t: MusicInfo) {
        mList.remove(t)
    }

    override fun remove(index: Int) {
        mList.removeAt(index)
    }

    override fun get(index: Int): MusicInfo? {
        if (index < 0 || index > mList.size) {
            return null
        }
        return mList[index]
    }

    override fun getSize(): Int {
        return mList.size
    }

    override fun indexOfKey(t: MusicInfo): Int {
        mList.forEachIndexed { index, musicInfo ->
            if (musicInfo.fileUrl == t.fileUrl) {
                return index
            }
        }
        return -1
    }
}



