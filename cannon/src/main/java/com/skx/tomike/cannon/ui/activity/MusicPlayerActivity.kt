package com.skx.tomike.cannon.ui.activity

import android.Manifest
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.utils.SpannableStringUtils
import com.skx.common.utils.ToastTool
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_MUSIC_PLAYER
import com.skx.tomike.cannon.bean.MusicInfo
import com.skx.tomike.cannon.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


/**
 * 描述 : 音乐播放器
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/23 9:08 下午
 */
@Route(path = ROUTE_PATH_MUSIC_PLAYER)
class MusicPlayerActivity : SkxBaseActivity<BaseViewModel>(), View.OnClickListener {

    private val mTvPlayingName by lazy {
        findViewById<TextView>(R.id.tv_musicPlayer_name)
    }

    private val mSeekPlayingDuration by lazy {
        findViewById<SeekBar>(R.id.seek_musicPlayer_duration)
    }

    private val mIvPlayerStartBtn by lazy {
        findViewById<ImageView>(R.id.iv_musicPlayer_play).also {
            it.setOnClickListener(this@MusicPlayerActivity)
        }
    }

    private val mIvPlayModeBtn by lazy {
        findViewById<TextView>(R.id.iv_musicPlayer_playMode)
    }

    private var mActivityResultLauncher: ActivityResultLauncher<Array<String>>? = null
    private val mAdapter = PlayerListAdapter()

    private val mPlayerManager = MusicPlayerManager.instance

    override fun initParams() {

    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("音乐播放器").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_music_player
    }

    override fun initView() {
        findViewById<ImageView>(R.id.iv_musicPlayer_prev).setOnClickListener(this)
        findViewById<ImageView>(R.id.iv_musicPlayer_next).setOnClickListener(this)
        mIvPlayModeBtn.setOnClickListener(this)
        findViewById<RecyclerView>(R.id.rv_musicPlayer_list).apply {
            adapter = mAdapter.also {
                it.setOnItemClickListener { _, _, date ->
                    val cur = MusicPlayerManager.instance.getPlayStateLiveData().value
                    if (cur?.value?.musicId == date.musicId) {
                        // 当前正在播放的和点击的是同一个 -> 暂停或者重新播放。
                        MusicPlayerManager.instance.playOrPause()
                    } else {
                        // 切歌
                        MusicPlayerManager.instance.play(date)
                    }
                }
            }
        }

        val drawable: Drawable? = ContextCompat.getDrawable(this, R.drawable.play_mode_order_loop)
        drawable?.run {
            setBounds(0, 0, 42, 42)
            mIvPlayModeBtn.setCompoundDrawables(drawable, null, null, null)
        }
        mIvPlayModeBtn.text = "顺序播放"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPlayerManager.getPlayStateLiveData().observe(this) {
            Log.e(TAG, "当前播放的曲目是：" + it.value?.displayName)
            it.value?.run {
                mTvPlayingName.text = this.title
            }
            mIvPlayerStartBtn.setImageResource(
                    if (1 == it.state) {
                        R.drawable.player_pause_icon
                    } else {
                        R.drawable.player_start_icon
                    }
            )
        }

        // 1.获取权限 - 读权限 - 获取本地音乐列表要用
        mActivityResultLauncher = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
        ) { result: Map<String?, Boolean?> ->
            // 读权限结果
            val readPermission = result[Manifest.permission.READ_EXTERNAL_STORAGE]
            if (readPermission == true) {
                // 成功获取了读权限
                // 2. 获取本地音乐文件
                loadLocalResource()
            } else {
                ToastTool.showToast(this@MusicPlayerActivity, "没有读权限做不了事情呦~")
            }
        }

        mActivityResultLauncher?.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
    }

    /**
     * 加载本地资源
     */
    private fun loadLocalResource() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = async {
                LocalResource.getAllMusicFile(this@MusicPlayerActivity)
            }
            val await = result.await()
            renderMusicList(await)
            mPlayerManager.addMusicList(await)
        }
    }

    private fun renderMusicList(music: List<MusicInfo>) {
        mAdapter.initMusicList(music)
    }

    private fun renderPlayMode() {
        when (MusicPlayerManager.instance.getPlayMode()) {
            is SingleCycleMode -> {
                val drawable: Drawable? = ContextCompat.getDrawable(this, R.drawable.play_mode_order_loop)
                drawable?.run {
                    setBounds(0, 0, 42, 42)
                    mIvPlayModeBtn.setCompoundDrawables(drawable, null, null, null)
                }
                mIvPlayModeBtn.text = "顺序播放"
                MusicPlayerManager.instance.setPlayMode(OrderPlayMode())
            }
            is OrderPlayMode -> {
                val drawable: Drawable? = ContextCompat.getDrawable(this, R.drawable.play_mode_random)
                drawable?.run {
                    setBounds(0, 0, 42, 42)
                    mIvPlayModeBtn.setCompoundDrawables(drawable, null, null, null)
                }
                mIvPlayModeBtn.text = "随机播放"
                MusicPlayerManager.instance.setPlayMode(RandomPlayMode())
            }
            is RandomPlayMode -> {
                val drawable: Drawable? = ContextCompat.getDrawable(this, R.drawable.play_mode_single_cycle)
                drawable?.run {
                    setBounds(0, 0, 42, 42)
                    mIvPlayModeBtn.setCompoundDrawables(drawable, null, null, null)
                }
                mIvPlayModeBtn.text = "单曲循环"
                MusicPlayerManager.instance.setPlayMode(SingleCycleMode())
            }
            else -> {
                mIvPlayModeBtn.setCompoundDrawables(null, null, null, null)
                mIvPlayModeBtn.text = "unknown"
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_musicPlayer_next -> {
                MusicPlayerManager.instance.next()
            }
            R.id.iv_musicPlayer_play -> {
                MusicPlayerManager.instance.playOrPause()
            }
            R.id.iv_musicPlayer_prev -> {
                MusicPlayerManager.instance.prev()
            }
            R.id.iv_musicPlayer_playMode -> {
                renderPlayMode()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MusicPlayerManager.instance.release()
    }
}

/**
 * 描述 : 音乐播放器 - 播放列表adapter
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/28 12:01 下午
 */
class PlayerListAdapter : RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder>() {

    private val mMusicList: MutableList<MusicInfo> = mutableListOf()
    private var onItemClickListener: ((v: View, pos: Int, date: MusicInfo) -> Unit)? = null

    private var mIndex = -1

    init {
        MusicPlayerManager.instance.addPlayStateObserver {
            // 1. 获取更新的数据所在索引
            var new = -1
            mMusicList.forEachIndexed { index: Int, musicInfo: MusicInfo ->
                if (musicInfo.musicId == it.value?.musicId) {
                    new = index
                }
            }
            // 2. 更新播放条目
            notifyItemChanged(mIndex)
            mIndex = new
            notifyItemChanged(mIndex)
        }
    }


    fun initMusicList(musicList: List<MusicInfo>?) {
        mMusicList.clear()
        addMusicList(musicList)
    }

    private fun addMusicList(musicList: List<MusicInfo>?) {
        musicList?.run {
            mMusicList.addAll(this)
            notifyDataSetChanged()
        }
    }

    fun setOnItemClickListener(listen: ((v: View, pos: Int, date: MusicInfo) -> Unit)?) {
        this.onItemClickListener = listen
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): PlayerListAdapter.PlayerListViewHolder {
        return PlayerListViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.adapter_music_player_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        val musicInfo = mMusicList[position]
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(holder.itemView, position, musicInfo)
        }
        holder.bindMusicDate(musicInfo)
    }

    override fun getItemCount() = mMusicList.size


    inner class PlayerListViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPlayStatus = itemView.findViewById<View>(R.id.iv_musicPlayer_music_playStatus)
        private val tvMusicName = itemView.findViewById<TextView>(R.id.iv_musicPlayer_music_name)

        @ColorRes
        private val selColor: Int = R.color.skx_f05b72

        fun bindMusicDate(musicInfo: MusicInfo) {
            val value = MusicPlayerManager.instance.getPlayStateLiveData().value
            val sel = value?.value?.musicId == musicInfo.musicId
            tvMusicName.text = SpannableStringUtils.getBuilder(musicInfo.title ?: "")
                    .setTextSize(42)
                    .setForegroundColor(ContextCompat.getColor(itemView.context,
                            if (sel) selColor else R.color.skx_212121))
                    .append(" - ${musicInfo.artist}")
                    .setTextSize(36)
                    .setForegroundColor(ContextCompat.getColor(itemView.context,
                            if (sel) selColor else R.color.skx_757575))
                    .create()

            ivPlayStatus.visibility = if (sel) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

}