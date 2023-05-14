package com.skx.tomike.tank.widget.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.imageloader.ImageLoader;
import com.skx.tomike.tank.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_RECYCLER_AS_VP;

/**
 * 描述 : RecyclerView 仿ViewPager 实现滑页功能
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
@Route(path = ROUTE_PATH_RECYCLER_AS_VP)
public class RecyclerAsViewPagerActivity extends SkxBaseActivity<BaseViewModel<?>> {

    private final List<Integer> mBannerList = new ArrayList<>();

    @Override
    protected void initParams() {
        mBannerList.add(R.drawable.image_01);
        mBannerList.add(R.drawable.image_02);
        mBannerList.add(R.drawable.image_03);
        mBannerList.add(R.drawable.image_04);
        mBannerList.add(R.drawable.image_05);
        mBannerList.add(R.drawable.image_06);
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("RecyclerView 仿 ViewPager效果").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_recyclerview_as_view_pager;
    }

    @Override
    protected void initView() {
        RecyclerView mRvBanner = findViewById(R.id.rv_recyclerviewAsViewPager_banner);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mRvBanner.setLayoutManager(layoutManager);
        mRvBanner.setAdapter(new RecyclerViewBannerAdapter(mBannerList));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRvBanner);
    }

    private static class RecyclerViewBannerAdapter extends RecyclerView.Adapter<RecyclerViewBannerAdapter.ItemViewHolder> {

        private final List<Integer> mBannerList = new ArrayList<>();

        public RecyclerViewBannerAdapter(List<Integer> contentList) {
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
        public RecyclerViewBannerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerViewBannerAdapter.ItemViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_recycler_view_banner, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewBannerAdapter.ItemViewHolder holder, int position) {
            ImageLoader.with(holder.itemView.getContext()).load(mBannerList.get(position)).into(holder.mIvImage);
        }

        private static class ItemViewHolder extends RecyclerView.ViewHolder {

            ImageView mIvImage;

            ItemViewHolder(View itemView) {
                super(itemView);
                mIvImage = itemView.findViewById(R.id.iv_recyclerViewAsViewPager_bannerImage);
            }
        }
    }

}
