package com.skx.tomike.catalog

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.skx.tomike.R
import com.skx.tomike.catalog.Catalog2Adapter.CatalogCellViewHolder
import java.util.*

/**
 * 目录列表Adapter
 *
 * @author shiguotao
 * Created on 2016/4/11.
 */
class Catalog2Adapter(list: List<CatalogCellModel>?) : RecyclerView.Adapter<CatalogCellViewHolder>() {

    private val mList: MutableList<CatalogCellModel> = ArrayList()

    init {
        setData(list)
    }

    fun setData(list: List<CatalogCellModel>?) {
        mList.clear()
        if (list != null) {
            mList.addAll(list)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            val oldSpanCnt = layoutManager.spanCount
            layoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (getItemViewType(position) == VIEW_TYPE_PARENT) {
                        oldSpanCnt
                    } else {
                        1
                    }
                }
            }
            layoutManager.spanCount = layoutManager.spanCount
        }
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        val catalogCellModel = mList[position]
        return if (catalogCellModel.isParent) {
            VIEW_TYPE_PARENT
        } else {
            VIEW_TYPE_CHILD
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogCellViewHolder {
        return if (viewType == VIEW_TYPE_PARENT) {
            CatalogGroupViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.adapter_catalog_item_parent, parent, false))
        } else CatalogChildViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_catalog_item, parent, false))
    }

    override fun onBindViewHolder(holder: CatalogCellViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    abstract class CatalogCellViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        abstract fun bindData(cellModel: CatalogCellModel)
    }

    internal class CatalogChildViewHolder(itemView: View) : CatalogCellViewHolder(itemView) {

        private var tvCellName: TextView = itemView.findViewById(R.id.catalog_item_child_name)

        override fun bindData(cellModel: CatalogCellModel) {
            tvCellName.run {
                text = cellModel.title
                setOnClickListener { v: View ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        v.elevation = 5.0f
                    }
                    try {
                        if (!TextUtils.isEmpty(cellModel.path)) {
                            ARouter.getInstance().build(cellModel.path).navigation()

                        } else {
                            val intent = Intent(itemView.context, Class.forName(cellModel.target))
                            (itemView.context as Activity).startActivity(intent)
                        }
                    } catch (e: ClassNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    internal class CatalogGroupViewHolder(itemView: View) : CatalogCellViewHolder(itemView) {

        private var tvGroupTittle: TextView = itemView.findViewById(R.id.catalog_item_group_name)
        private var ivGroupArrow: ImageView = itemView.findViewById(R.id.catalog_item_group_arrow)

        override fun bindData(cellModel: CatalogCellModel) {
            tvGroupTittle.text = cellModel.title
        }
    }

    companion object {
        private const val VIEW_TYPE_PARENT = 1
        private const val VIEW_TYPE_CHILD = 2
    }
}