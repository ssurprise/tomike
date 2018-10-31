package com.skx.tomike.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by shiguotao on 2016/4/20.
 * <p>
 * ViewPager 无限循环、自动轮播 Adapter
 */
public class InfiniteLoopAdapter extends PagerAdapter{
    private List<Integer> mList;
    LayoutInflater layoutInflater;

    public InfiniteLoopAdapter(Context context, List<Integer> list) {
        super();
        this.mList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        ((ViewPager) container).removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(layoutInflater.getContext());
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.height = 168;
        layoutParams.width = 168;

        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mList.get(position % mList.size()));
        ((ViewPager) container).addView(imageView);
        return imageView;
    }
}
