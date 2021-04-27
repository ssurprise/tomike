package com.skx.tomike.tank.widget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.skx.tomike.tank.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2016/4/20.
 * <p>
 * ViewPager 一屏显示多个pager Adapter
 */
public class MultiplePagerAdapter extends PagerAdapter {

    private final List<Integer> mDataList = new ArrayList<>();

    public MultiplePagerAdapter(List<Integer> list) {
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

    /**
     * 页面宽度所占ViewPager测量宽度的权重比例，默认为1
     */
    @Override
    public float getPageWidth(int position) {
        if (mDataList.size() <= 1) {
            return 1;
        } else {
            return (float) 0.8;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NotNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_multiple_page, container, false);
        ImageView imageView = view.findViewById(R.id.iv_multiplePage_image);
        ImageView hoverImageView = view.findViewById(R.id.iv_multiplePage_hoverImage);
        imageView.setImageResource(mDataList.get(position % mDataList.size()));

        view.setTag(hoverImageView);
        container.addView(view);
        return view;
    }
}
