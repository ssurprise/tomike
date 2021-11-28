package com.skx.tomike.cannon.utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.skx.tomike.cannon.bean.MusicInfo

/**
 * 描述 : 本地资源
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 12:58 下午
 */
object LocalResource {

    suspend fun getAllMusicFile(context: Context): MutableList<MusicInfo> {
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

                val music = MusicInfo(musicId.toString(),
                        path, displayName, tilte, album, artist, size, duration)
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