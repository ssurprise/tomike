package com.skx.tomike.cannonlaboratory.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.skx.tomike.cannonlaboratory.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2016/4/20.
 * <p>
 * ViewPager 一屏显示多个pager Adapter
 */
public class MultiplePagerAdapter extends PagerAdapter {
    private List<Integer> mList;
    private LayoutInflater layoutInflater;

    public MultiplePagerAdapter(Context context, List<Integer> list) {
        super();
        if (list == null) {
            this.mList = new ArrayList<>();
        } else {
            this.mList = list;
        }
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

    public Object getPositionItem(int position) {
        return mList.get(position % mList.size());
    }

    /**
     * 页面宽度所占ViewPager测量宽度的权重比例，默认为1
     */
    @Override
    public float getPageWidth(int position) {
        if (mList.size() <= 1) {
            return 1;
        } else {
            return (float) 0.8;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        ((ViewPager) container).removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = layoutInflater.inflate(R.layout.adapter_multiple_page, container, false);
        ImageView imageView = view.findViewById(R.id.multiple_page_img);
        ImageView hoverImageView = view.findViewById(R.id.multiple_page_hoverImg);
        imageView.setImageResource(mList.get(position % mList.size()));

        view.setTag(hoverImageView);
        container.addView(view);
        return view;
    }
}
