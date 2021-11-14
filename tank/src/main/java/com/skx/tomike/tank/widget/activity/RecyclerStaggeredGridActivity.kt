package com.skx.tomike.tank.widget.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_RECYCLER_STAGGERED_GRID
import java.util.*

/**
 * 描述 : RecyclerView 瀑布流
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
@Route(path = ROUTE_PATH_RECYCLER_STAGGERED_GRID)
class RecyclerStaggeredGridActivity : SkxBaseActivity<BaseViewModel?>() {

    private val mBannerList: MutableList<Int> = mutableListOf()

    override fun initParams() {
        mBannerList.add(R.drawable.image_01)
        mBannerList.add(R.drawable.image_02)
        mBannerList.add(R.drawable.image_03)
        mBannerList.add(R.drawable.image_04)
        mBannerList.add(R.drawable.image_05)
        mBannerList.add(R.drawable.image_06)
        mBannerList.add(R.drawable.image_07)
        mBannerList.add(R.drawable.image_08)
        mBannerList.add(R.drawable.image_01)
        mBannerList.add(R.drawable.image_02)
        mBannerList.add(R.drawable.image_03)
        mBannerList.add(R.drawable.image_04)
        mBannerList.add(R.drawable.image_05)
        mBannerList.add(R.drawable.image_06)
        mBannerList.add(R.drawable.image_07)
        mBannerList.add(R.drawable.image_08)
        mBannerList.add(R.drawable.image_01)
        mBannerList.add(R.drawable.image_02)
        mBannerList.add(R.drawable.image_03)
        mBannerList.add(R.drawable.image_04)
        mBannerList.add(R.drawable.image_05)
        mBannerList.add(R.drawable.image_06)
        mBannerList.add(R.drawable.image_07)
        mBannerList.add(R.drawable.image_08)
        mBannerList.add(R.drawable.image_01)
        mBannerList.add(R.drawable.image_02)
        mBannerList.add(R.drawable.image_03)
        mBannerList.add(R.drawable.image_04)
        mBannerList.add(R.drawable.image_05)
        mBannerList.add(R.drawable.image_06)
        mBannerList.add(R.drawable.image_07)
        mBannerList.add(R.drawable.image_08)
        mBannerList.add(R.drawable.image_01)
        mBannerList.add(R.drawable.image_02)
        mBannerList.add(R.drawable.image_03)
        mBannerList.add(R.drawable.image_04)
        mBannerList.add(R.drawable.image_05)
        mBannerList.add(R.drawable.image_06)
        mBannerList.add(R.drawable.image_07)
        mBannerList.add(R.drawable.image_08)
        mBannerList.add(R.drawable.image_01)
        mBannerList.add(R.drawable.image_02)
        mBannerList.add(R.drawable.image_03)
        mBannerList.add(R.drawable.image_04)
        mBannerList.add(R.drawable.image_05)
        mBannerList.add(R.drawable.image_06)
        mBannerList.add(R.drawable.image_07)
        mBannerList.add(R.drawable.image_08)
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("RecyclerView 瀑布流").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_recyclerview_staggered_grid
    }

    override fun initView() {
        findViewById<RecyclerView>(R.id.rv_recyclerviewStaggeredGrid_banner).run {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = RvStaggeredAdapter(mBannerList)
        }
    }

    private class RvStaggeredAdapter(contentList: List<Int>?) : RecyclerView.Adapter<RvStaggeredAdapter.ItemViewHolder>() {

        private val mBannerList: MutableList<Int> = mutableListOf()

        init {
            contentList?.run {
                mBannerList.addAll(contentList)
            }
        }

        override fun getItemCount(): Int {
            return mBannerList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_recycler_staggered_grid_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val drawable = ContextCompat.getDrawable(holder.itemView.context, mBannerList[position])
            holder.mIvImage.setImageDrawable(drawable)
            holder.mTvText.text = String.format(Locale.getDefault(), "第%d个", position)
        }

        private class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var mIvImage: ImageView = itemView.findViewById(R.id.iv_recyclerViewStaggered_item_image)
            var mTvText: TextView = itemView.findViewById(R.id.iv_recyclerViewStaggered_item_text)
        }
    }
}