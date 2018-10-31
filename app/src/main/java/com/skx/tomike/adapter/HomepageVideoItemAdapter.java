package com.skx.tomike.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.data.bean.HomepageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2017/3/16.
 * <p>
 * 首页 -  视频item
 */
public class HomepageVideoItemAdapter extends PagerAdapter {

    private Context mContext;
    private List<HomepageBean.HomepageVideoItemInfo> mVideoList;

    public HomepageVideoItemAdapter(Context mContext, List<HomepageBean.HomepageVideoItemInfo> videoLists) {
        this.mContext = mContext;
        if (videoLists == null) {
            this.mVideoList = new ArrayList<>();
        } else {
            this.mVideoList = videoLists;
        }
    }

    @Override
    public int getCount() {
        return mVideoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_video_item, container, false);
        ImageView imgv_mainImage = (ImageView) view.findViewById(R.id.homepage_video_item_mainImage);
        TextView tv_duration = (TextView) view.findViewById(R.id.homepage_video_item_duration);
        TextView tv_title = (TextView) view.findViewById(R.id.homepage_video_item_title);
        ImageView imgv_play = (ImageView) view.findViewById(R.id.homepage_video_item_playIcon);

        HomepageBean.HomepageVideoItemInfo homepageVideoInfo = mVideoList.get(position);

        //  图片加载    imgv_mainImage  homepageVideoInfo.imageUrl
        tv_duration.setText(homepageVideoInfo.time);
        tv_title.setText(homepageVideoInfo.title);
        if ("light".equalsIgnoreCase(homepageVideoInfo.color)) {
            tv_duration.setTextColor(ContextCompat.getColor(mContext, R.color.skx_ffffff));
            tv_title.setTextColor(ContextCompat.getColor(mContext, R.color.skx_ffffff));
            imgv_play.setImageResource(R.drawable.icon_play_white);
        } else {
            tv_duration.setTextColor(ContextCompat.getColor(mContext, R.color.skx_323232));
            tv_title.setTextColor(ContextCompat.getColor(mContext, R.color.skx_323232));
            imgv_play.setImageResource(R.drawable.icon_play_black);
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
