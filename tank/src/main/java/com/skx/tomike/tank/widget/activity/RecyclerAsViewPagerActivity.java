package com.skx.tomike.tank.widget.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.tank.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.imageloader.ImageLoader;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : RecyclerView 仿ViewPager 实现滑页功能
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
public class RecyclerAsViewPagerActivity extends SkxBaseActivity<BaseViewModel> {

    private final List<String> mBannerList = new ArrayList<>();

    @Override
    protected void initParams() {
        mBannerList.add("http://pic1.win4000.com/wallpaper/6/58194994a591e.jpg");
        mBannerList.add("http://pic1.win4000.com/wallpaper/6/5819499e74cf8.jpg");
        mBannerList.add("http://pic1.win4000.com/wallpaper/3/584f9928e1771.jpg");
        mBannerList.add("http://pic1.win4000.com/wallpaper/3/584f992d6bd62.jpg");
        mBannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590993126378&di=b2f667623306f875b48d50f120267a88&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F201611_18_11%2Fa46lpo74655993745596.gif");
        mBannerList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1761055549,119613524&fm=26&gp=0.jpg");
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("RecyclerView 仿 ViewPager效果").create();
    }

    @Override
    protected int getLayoutId() {
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
        public RecyclerViewBannerAdapter.ItemViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            return new RecyclerViewBannerAdapter.ItemViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_recycler_view_banner, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewBannerAdapter.ItemViewHolder holder, int position) {
            ImageLoader.with(holder.itemView.getContext()).load(mBannerList.get(position)).into(holder.mIvImage);
//            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
//            layoutParams.width = ScreenUtilKt.getScreenWidth(holder.itemView.getContext()) - 300;
//            holder.itemView.setLayoutParams(layoutParams);
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
