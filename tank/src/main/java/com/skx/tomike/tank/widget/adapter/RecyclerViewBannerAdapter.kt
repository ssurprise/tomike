package com.skx.tomike.tank.widget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.skx.common.imageloader.ImageLoader
import com.skx.tomike.tank.R
import java.util.ArrayList

class RecyclerViewBannerAdapter(contentList: List<String>?) : RecyclerView.Adapter<RecyclerViewBannerAdapter.ItemViewHolder>() {

    private val mBannerList: MutableList<String> = ArrayList()

    override fun getItemCount(): Int {
        return mBannerList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_recycler_view_banner, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        ImageLoader.with(holder.itemView.context).load(mBannerList[position]).into(holder.mIvImage)
    }

    class ItemViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mIvImage: ImageView = itemView.findViewById(R.id.iv_recyclerViewAsViewPager_bannerImage)

        init {
            val layoutParams = mIvImage.layoutParams as RelativeLayout.LayoutParams
            layoutParams.topMargin = 15
            layoutParams.bottomMargin = 15
            layoutParams.leftMargin = 15
            layoutParams.rightMargin = 15
            mIvImage.layoutParams = layoutParams
        }
    }

    init {
        if (contentList != null) {
            mBannerList.addAll(contentList)
        }
    }
}