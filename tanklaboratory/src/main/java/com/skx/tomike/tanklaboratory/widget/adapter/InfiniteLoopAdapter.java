package com.skx.tomike.tanklaboratory.widget.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2016/4/20.
 * <p>
 * ViewPager 无限循环、自动轮播 Adapter
 */
public class InfiniteLoopAdapter extends PagerAdapter {

    private final List<Integer> mDataList = new ArrayList<>();

    public InfiniteLoopAdapter(List<Integer> list) {
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
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.height = 168;
        layoutParams.width = 168;

        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mDataList.get(position % mDataList.size()));
        container.addView(imageView);
        return imageView;
    }

}
