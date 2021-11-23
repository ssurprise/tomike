package com.skx.tomike.cannon.ui.activity

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
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
        val player = PlayerManagerImpl()
        val listManager = MusicListManagerImpl(this)
        player.init(this)
        player.register(listManager)
        player.play()


        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            player.pause()
        }, 10000)
        handler.postDelayed({
            player.play()
        }, 15000)
//        handler.postDelayed({
//            player.play()
//        }, 20000)

    }

}


interface IPlayerManager<T> {

    /**
     * 注册播放列表
     */
    fun register(list: IMusicListManager<T>)

    /**
     * 播放
     */
    fun play()

    /**
     * 暂停
     */
    fun pause()

    /**
     * 切换下一首音乐
     */
    fun next()

    /**
     * 切换上一首音乐
     */
    fun prev()
    fun release()
}

class PlayerManagerImpl : IPlayerManager<MusicInfo> {

    private var mIndex = -1
    private var mListManager: IMusicListManager<MusicInfo>? = null

    private var mPlayer: MediaPlayer? = null

    fun init(context: Context) {
        mPlayer = MediaPlayer.create(context, R.raw.xianer)
    }

    override fun register(list: IMusicListManager<MusicInfo>) {
        mListManager = list
    }

    override fun play() {
        mPlayer?.start()
    }

    override fun pause() {
        mPlayer?.pause()
    }

    override fun next() {
    }

    override fun prev() {
    }

    override fun release() {
        mPlayer?.stop()
        mPlayer?.release()
    }
}


interface IMusicListManager<T> {
    fun register()
    fun add(t: T)
    fun remove(t: T)

    /**
     * 移除指定位置的音乐资源
     */
    fun remove(index: Int)

    /**
     * 获取下一首音乐的资源
     */
    fun next(): T?

    /**
     * 获取上一首音乐的资源
     */
    fun prev(): T?
}

class MusicListManagerImpl(context: Context) : IMusicListManager<MusicInfo> {

    private var list: MutableList<MusicInfo> = mutableListOf()
    private val isSupportLoop = false
    private var mIndex = -1

    init {
        list = getAllMusicFile(context.applicationContext)
    }

    override fun register() {
    }

    override fun add(t: MusicInfo) {
    }

    override fun remove(t: MusicInfo) {
    }

    override fun remove(index: Int) {
    }

    override fun next(): MusicInfo? {
        if (mIndex + 1 in 0 until list.size) {
            return list[++mIndex]
        }
        return null
    }

    override fun prev(): MusicInfo? {
        if (mIndex - 1 in 0 until list.size) {
            return list[--mIndex]
        }
        return null
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
//        val year: String? = null,
//        val type: String? = null,
        val size: Long = 0,
        val duration: Int = 0,
)