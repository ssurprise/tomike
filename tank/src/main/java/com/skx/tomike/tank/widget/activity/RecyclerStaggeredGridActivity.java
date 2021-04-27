package com.skx.tomike.tank.widget.activity;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 描述 : RecyclerView 瀑布流
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
public class RecyclerStaggeredGridActivity extends SkxBaseActivity<BaseViewModel> {

    private final List<Integer> mBannerList = new ArrayList<>();

    @Override
    protected void initParams() {
        mBannerList.add(R.drawable.image_01);
        mBannerList.add(R.drawable.image_02);
        mBannerList.add(R.drawable.image_03);
        mBannerList.add(R.drawable.image_04);
        mBannerList.add(R.drawable.image_05);
        mBannerList.add(R.drawable.image_06);
        mBannerList.add(R.drawable.image_07);
        mBannerList.add(R.drawable.image_08);
        mBannerList.add(R.drawable.image_01);
        mBannerList.add(R.drawable.image_02);
        mBannerList.add(R.drawable.image_03);
        mBannerList.add(R.drawable.image_04);
        mBannerList.add(R.drawable.image_05);
        mBannerList.add(R.drawable.image_06);
        mBannerList.add(R.drawable.image_07);
        mBannerList.add(R.drawable.image_08);
        mBannerList.add(R.drawable.image_01);
        mBannerList.add(R.drawable.image_02);
        mBannerList.add(R.drawable.image_03);
        mBannerList.add(R.drawable.image_04);
        mBannerList.add(R.drawable.image_05);
        mBannerList.add(R.drawable.image_06);
        mBannerList.add(R.drawable.image_07);
        mBannerList.add(R.drawable.image_08);
        mBannerList.add(R.drawable.image_01);
        mBannerList.add(R.drawable.image_02);
        mBannerList.add(R.drawable.image_03);
        mBannerList.add(R.drawable.image_04);
        mBannerList.add(R.drawable.image_05);
        mBannerList.add(R.drawable.image_06);
        mBannerList.add(R.drawable.image_07);
        mBannerList.add(R.drawable.image_08);
        mBannerList.add(R.drawable.image_01);
        mBannerList.add(R.drawable.image_02);
        mBannerList.add(R.drawable.image_03);
        mBannerList.add(R.drawable.image_04);
        mBannerList.add(R.drawable.image_05);
        mBannerList.add(R.drawable.image_06);
        mBannerList.add(R.drawable.image_07);
        mBannerList.add(R.drawable.image_08);
        mBannerList.add(R.drawable.image_01);
        mBannerList.add(R.drawable.image_02);
        mBannerList.add(R.drawable.image_03);
        mBannerList.add(R.drawable.image_04);
        mBannerList.add(R.drawable.image_05);
        mBannerList.add(R.drawable.image_06);
        mBannerList.add(R.drawable.image_07);
        mBannerList.add(R.drawable.image_08);
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("RecyclerView 瀑布流").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview_staggered_grid;
    }

    @Override
    protected void initView() {
        RecyclerView mRvBanner = findViewById(R.id.rv_recyclerviewStaggeredGrid_banner);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRvBanner.setLayoutManager(layoutManager);
        mRvBanner.setAdapter(new RecyclerViewStaggeredAdapter(mBannerList));
    }

    private static class RecyclerViewStaggeredAdapter extends RecyclerView.Adapter<RecyclerViewStaggeredAdapter.ItemViewHolder> {

        private final List<Integer> mBannerList = new ArrayList<>();

        public RecyclerViewStaggeredAdapter(List<Integer> contentList) {
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
                    .inflate(R.layout.adapter_recycler_staggered_grid_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), mBannerList.get(position));
            holder.mIvImage.setImageDrawable(drawable);
            holder.mTvText.setText(String.format(Locale.getDefault(), "第%d个", position));
        }

        private static class ItemViewHolder extends RecyclerView.ViewHolder {

            ImageView mIvImage;
            TextView mTvText;

            ItemViewHolder(View itemView) {
                super(itemView);
                mIvImage = itemView.findViewById(R.id.iv_recyclerViewStaggered_item_image);
                mTvText = itemView.findViewById(R.id.iv_recyclerViewStaggered_item_text);
            }
        }
    }

}
