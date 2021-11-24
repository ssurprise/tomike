package com.skx.tomike.cannon.ui.activity

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.media.MediaPlayer
import android.provider.MediaStore
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_MUSIC_PLAYER


/**
 * 描述 : 音乐播放器
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/23 9:08 下午
 */
@Route(path = ROUTE_PATH_MUSIC_PLAYER)
class MusicPlayerActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {

    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("音乐播放器").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_music_player
    }

    override fun initView() {
        val player = PlayManagerImpl()
//        val listManager = MusicListManagerImpl(this)
//        player.init(this)
//        player.play()
//
//
//        val handler = Handler(Looper.myLooper()!!)
//        handler.postDelayed({
//            player.pause()
//        }, 10000)
//        handler.postDelayed({
//            player.play()
//        }, 15000)
//        handler.postDelayed({
//            player.play()
//        }, 20000)

    }
}

class MusicPlayerManager {

    companion object {
        val DEFAULT_LIST_MANAGER: IMusicListManager<MusicInfo> = MusicListManagerImpl()
    }

    private var musicListManager: IMusicListManager<MusicInfo> = DEFAULT_LIST_MANAGER
    private var playManager: IPlayManager<MusicInfo>? = null

    // 表示当前播放的音乐
    private var mIndex = -1

    // 自动播放
    private var autoplay = true


    fun play(t: MusicInfo?, autoAddIfNotExist: Boolean = false, playNow: Boolean = false) {
        if (t == null) {
            return
        }
        // todo other check

        //1.检查该音频资源是否在播放列表里
        val indexOfKey = musicListManager.indexOfKey(t)

        //2.当前播放列表里有该音频资源,直接切换至该资源index 进行播放
        if (indexOfKey != -1) {
            mIndex = indexOfKey
            playManager?.play(musicListManager.get(mIndex))
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
        playManager?.play(musicListManager.get(index))
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

    private var mPlayer: MediaPlayer? = null

    fun init(context: Context) {
        mPlayer = MediaPlayer.create(context, R.raw.xianer)
    }

    override fun init() {

    }

    override fun play(t: MusicInfo?) {
        //todo check->t
    }

    override fun pause() {

    }

    override fun release() {
        mPlayer?.stop()
        mPlayer?.release()
    }

    override fun isPlaying(): Boolean {
        return mPlayer?.isPlaying ?: false
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

    init {
//        list = getAllMusicFile(context.applicationContext)
    }

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

    private fun getAllMusicFile(context: Context): MutableList<MusicInfo> {
        val list: MutableList<MusicInfo> = mutableListOf()

        val mContentResolver: ContentResolver = context.contentResolver
        var c: Cursor? = null
        try {
            c = mContentResolver.query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null,
                    null,
                    null,
                    MediaStore.Audio.Media.DEFAULT_SORT_ORDER)

            while (c?.moveToNext() == true) {
                // 歌曲ID
                val musicId: Int = c.getInt(c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                // 歌曲文件的路径
                val path: String = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                // 歌曲名
                val displayName: String = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
                // 歌曲的名称
                val tilte: String = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                // 歌曲的专辑名
                val album: String = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM))
                // 歌曲的歌手名
                val artist: String = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                // 文件大小
                val size: Long = c.getLong(c.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE))
                // 歌曲的总播放时长
                val duration: Int = c.getInt(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))

                val music = MusicInfo(path, displayName, tilte, album, artist, size, duration)
                list.add(music)

            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            c?.close()
        }
        return list
    }
}

data class MusicInfo(
        val fileUrl: String? = null,
        val displayName: String? = null,
        val title: String? = null,
        val album: String? = null,
        val artist: String? = null,
        val size: Long = 0,
        val duration: Int = 0,
)