package com.skx.tomike.tacticallaboratory.pattern.memento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skx.tomike.tacticallaboratory.R
import com.skx.tomike.tacticallaboratory.pattern.memento.MementoHistoryAdapter.MementoViewHolder
import java.util.*

/**
 * 描述 : 备份记录adapter
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 4:15 PM
 */
class MementoHistoryAdapter : RecyclerView.Adapter<MementoViewHolder>() {
    /**
     * 备份key的集合。
     * 注：这里为了演示备份管理者（Caretaker）对备份完全管理，故此只存储了key，没有对备份进行操作，属demo 效果。
     */
    private val mMementoList = ArrayList<String>()
    private var mMementoAccessListener: MementoAccessListener? = null
    fun setMementoList(mementoMap: Map<String, PlayerMemento?>?) {
        mMementoList.clear()
        if (mementoMap != null) {
            for ((key) in mementoMap) {
                mMementoList.add(key)
            }
        }
        notifyDataSetChanged()
    }

    fun setMementoAccessListener(mementoAccessListener: MementoAccessListener?) {
        mMementoAccessListener = mementoAccessListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MementoViewHolder {
        return MementoViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_pattern_memento_history_item, parent, false))
    }

    override fun onBindViewHolder(holder: MementoViewHolder, position: Int) {
        val mementoKey = mMementoList[position]
        holder.mTvMementoKey.text = mementoKey
        holder.mBtnRead.setOnClickListener {
            mMementoAccessListener?.onReadListener(mementoKey)
        }
        holder.mBtnDelete.setOnClickListener {
            mMementoAccessListener?.onDeleteListener(mementoKey)
            mMementoList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return mMementoList.size
    }

    class MementoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvMementoKey: TextView = itemView.findViewById(R.id.tv_mementoPattern_historyItem_key)
        val mBtnDelete: TextView = itemView.findViewById(R.id.tv_mementoPattern_historyItem_del)
        val mBtnRead: TextView = itemView.findViewById(R.id.tv_mementoPattern_historyItem_read)
    }

    interface MementoAccessListener {
        fun onReadListener(mementoKey: String?)
        fun onDeleteListener(mementoKey: String?)
    }
}