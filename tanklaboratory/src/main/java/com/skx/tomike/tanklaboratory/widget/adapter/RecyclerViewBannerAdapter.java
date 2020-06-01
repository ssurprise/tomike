package com.skx.tomike.tanklaboratory.widget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.imageloader.ImageLoader;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2017/10/13.
 */
public class RecyclerViewBannerAdapter extends RecyclerView.Adapter<RecyclerViewBannerAdapter.ItemViewHolder> {

    private final List<String> mBannerList = new ArrayList<>();

    public RecyclerViewBannerAdapter(List<String> contentList) {
        if (contentList != null) {
            mBannerList.addAll(contentList);
        }
    }

    @Override
    public int getItemCount() {
        return mBannerList.size();
    }

    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recycler_view_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull ItemViewHolder holder, int position) {
        ImageLoader.with(holder.itemView.getContext()).load(mBannerList.get(position)).into(holder.mIvImage);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvImage;

        ItemViewHolder(View itemView) {
            super(itemView);
            mIvImage = itemView.findViewById(R.id.iv_recyclerViewAsViewPager_bannerImage);
        }
    }
}
