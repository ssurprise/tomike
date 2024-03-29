package com.skx.tomike.tank.widget.activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.imageloader.ImageLoader;
import com.skx.tomike.tank.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER2;

/**
 * 描述 : Androidx ViewPage2
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
@Route(path = ROUTE_PATH_VIEWPAGER2)
public class ViewPager2Activity extends SkxBaseActivity<BaseViewModel<?>> {

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
        return new TitleConfig.Builder().setTitleText("androidx ViewPager").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_androidx_view_pager2;
    }

    @Override
    protected void initView() {
        ViewPager2 vpHorizontal = findViewById(R.id.vp_androidxViewPager_horizontal);
        vpHorizontal.setAdapter(new RecyclerViewBannerAdapter(mBannerList));
        vpHorizontal.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Log.e("onPageScrolled", position + " positionOffset:" + positionOffset + " positionOffsetPixels:" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.e("onPageSelected", position + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        ViewPager2 mVpVertical = findViewById(R.id.vp_androidxViewPager_vertical);
        mVpVertical.setAdapter(new RecyclerViewBannerAdapter(mBannerList));
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
        public ItemViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            return new RecyclerViewBannerAdapter.ItemViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_recycler_view_banner, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            ImageLoader.with(holder.itemView.getContext()).load(mBannerList.get(position)).into(holder.mIvImage);
//            ViewGroup.LayoutParams layoutParams = holder.mIvImage.getLayoutParams();
//            layoutParams.width = ScreenUtilKt.getScreenWidth(holder.itemView.getContext()) - 200;
//            holder.mIvImage.setLayoutParams(layoutParams);
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
