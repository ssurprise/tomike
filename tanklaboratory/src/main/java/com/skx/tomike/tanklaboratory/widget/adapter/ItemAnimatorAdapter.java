package com.skx.tomike.tanklaboratory.widget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.tanklaboratory.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2017/10/13.
 */
public class ItemAnimatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mContentList;

    public ItemAnimatorAdapter(List<String> contentList) {
        if (mContentList == null) {
            mContentList = new ArrayList<>();
        }
        this.mContentList.addAll(contentList);
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ItemAnimatorViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_sticky_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        ItemAnimatorViewHolder tHolder = (ItemAnimatorViewHolder) holder;
        tHolder.tv_name.setText(mContentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }

    private static class ItemAnimatorViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;

        ItemAnimatorViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.nationList_item_name);
        }
    }

    public void insertFirstPos(String newStr) {
        mContentList.add(newStr);
        notifyItemInserted(mContentList.size() - 1);
    }

    public void append(String newStr) {
        mContentList.add(newStr);
        notifyItemInserted(mContentList.size() - 1);
    }

    public void insertRange(List<String> newStr) {
        int originalSize = mContentList.size();
        mContentList.addAll(newStr);
        notifyItemRangeInserted(originalSize, mContentList.size());
    }

    /**
     * 删除第一项
     */
    public void removeFirst() {
        if (mContentList.size() - 1 >= 0) {
            mContentList.remove(0);
            notifyItemRemoved(0);
        }
    }

    /**
     * 删除最后一项
     */
    public void removeLast() {
        if (mContentList.size() - 1 >= 0) {
            mContentList.remove(mContentList.size() - 1);
            notifyItemRemoved(mContentList.size());
        }
    }

    /**
     * 删除区间item
     *
     * @param newStr
     */
    public void removeRange(List<String> newStr) {
        boolean b = mContentList.removeAll(newStr);
        if (b) {
            notifyItemRangeRemoved(0, newStr.size());
            notifyItemRangeChanged(0, mContentList.size());
        }
    }
}
