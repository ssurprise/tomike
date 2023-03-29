package com.skx.tomike.adapter

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.skx.tomike.R
import com.skx.tomike.bean.CatalogItem
import java.util.*

/**
 * 目录列表Adapter
 *
 * @author shiguotao
 * Created on 2016/4/11.
 */
class DashboardAdapter(list: List<CatalogItem>?) : RecyclerView.Adapter<DashboardAdapter.ItemViewHolder>() {

    private val mList: MutableList<CatalogItem> = ArrayList()

    init {
        setData(list)
    }

    fun setData(list: List<CatalogItem>?) {
        mList.clear()
        if (list != null) {
            mList.addAll(list)
        }
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_catalog_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(mList[position])
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvCellName: TextView = itemView.findViewById(R.id.catalog_item_child_name)

        fun bindData(data: CatalogItem) {
            tvCellName.run {
                text = data.name
                setOnClickListener { v: View ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        v.elevation = 5.0f
                    }
                    try {
                        if (!TextUtils.isEmpty(data.path)) {
                            ARouter.getInstance().build(data.path).navigation()

                        } else {
                            val intent = Intent(itemView.context, Class.forName(data.value))
                            (itemView.context as Activity).startActivity(intent)
                        }
                    } catch (e: ClassNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}