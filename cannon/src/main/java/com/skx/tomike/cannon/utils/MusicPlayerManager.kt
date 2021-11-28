package com.skx.tomike.cannon.utils

import android.content.Context
import android.media.MediaPlayer
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
     * 真正的播放器管理者
     */
    private var playManager: IPlayManager<MusicInfo>? = PlayManagerImpl()

    /**
     * 播放状态LiveData
     */
    private val mPlayStateLiveData: MutableLiveData<PlayState<MusicInfo>> = MutableLiveData()

    // 表示当前播放的音乐
    private var mIndex = -1

    // 自动播放
    private var autoplay = true

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
     * 只负责播放目标音频资源，不做任何判断处理
     */
    private fun justPlay(index: Int) {
        val cur = musicListManager.get(index)
        cur?.run {
            var state: PlayState<MusicInfo>? = mPlayStateLiveData.value
            if (state == null) {
                state = PlayState()
            }
            state.state = 1
            state.value = cur
            mPlayStateLiveData.postValue(state)
        }
        playManager?.play(cur)
    }

    /**
     * 播放下一首
     */
    fun next() {
        if (mIndex + 1 > musicListManager.getSize()) {
            return
        }
        justPlay(++mIndex)
    }

    /**
     * 播放前一首
     */
    fun prev() {
        if (mIndex - 1 < 0) {
            return
        }
        justPlay(--mIndex)
    }

    fun getPlayStateLiveData(): MutableLiveData<PlayState<MusicInfo>> {
        return mPlayStateLiveData
    }
}

class PlayState<T> {
    // 播放状态：0-未播放；1-播放中；2-暂停
    var state = 0
    var value: T? = null
}

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
     *
     */
    fun release()
}

class PlayManagerImpl : IPlayManager<MusicInfo> {

    private val TAG = "PlayManager"

    private var mPlayer: MediaPlayer = MediaPlayer()

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

    override fun init() {

    }

    override fun play(t: MusicInfo?) {
        //todo check->t
        if (isPlaying()) {
            // 当前正在播放中
            mPlayer.reset()
        }
        mPlayer.run {
            Log.e(TAG, "file-url:" + t?.fileUrl)
            setDataSource(t?.fileUrl)
            prepare()
            start()
        }
    }

    override fun pause() {
        if (isPlaying()) {
            mPlayer.pause()
        }
    }

    override fun release() {
        mPlayer.reset()
        mPlayer.release()
    }

    override fun isPlaying(): Boolean {
        return mPlayer.isPlaying ?: false
    }
}

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



