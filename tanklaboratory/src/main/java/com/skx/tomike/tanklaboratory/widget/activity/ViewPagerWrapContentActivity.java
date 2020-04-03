package com.skx.tomike.tanklaboratory.widget.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : 适应item高度的ViewPager
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/4/20.
 */
public class ViewPagerWrapContentActivity extends SkxBaseActivity<BaseViewModel> {

    private final ArrayList<Integer> mImageList = new ArrayList<>();

    @Override
    protected void initParams() {
        mImageList.add(R.drawable.image_01);
        mImageList.add(R.drawable.image_02);
        mImageList.add(R.drawable.image_03);
        mImageList.add(R.drawable.image_04);
        mImageList.add(R.drawable.image_05);
        mImageList.add(R.drawable.image_06);
        mImageList.add(R.drawable.image_07);
        mImageList.add(R.drawable.image_08);
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("ViewPager 自适应高度").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_pager_wrap_content;
    }

    @Override
    protected void initView() {
        ViewPager viewPager = findViewById(R.id.vp_wrapContentVp_content);
        WrapVPagerAdapter adapter = new WrapVPagerAdapter(mImageList);
        viewPager.setAdapter(adapter);
    }

    public static class WrapVPagerAdapter extends PagerAdapter {

        private final List<Integer> mDataList = new ArrayList<>();

        public WrapVPagerAdapter(List<Integer> list) {
            super();
            if (list != null) {
                this.mDataList.addAll(list);
            }
        }

        @Override
        public int getCount() {
            return mDataList.size();
        }

        @Override
        public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, @NotNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @NotNull
        @Override
        public Object instantiateItem(@NotNull ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_view_pager_wrap_content, container, false);
//            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_view_pager_wrap_content, container);
            ImageView imageView = view.findViewById(R.id.iv_wrapContentVp_image);
            imageView.setImageResource(mDataList.get(position));
            container.addView(view);
            return view;
        }
    }
}
