package com.skx.tomike.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomike.data.bean.HomepageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2017/3/16.
 * <p>
 * 首页 -  视频item
 */
public class HomepageOperationItemAdapter extends PagerAdapter {

    private Context mContext;
    private List<HomepageBean.OperationItem> mVideoList;

    public HomepageOperationItemAdapter(Context mContext, List<HomepageBean.OperationItem> videoLists) {
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

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_operation_item, container, false);
        ImageView imgv_mainImage = (ImageView) view.findViewById(R.id.homepage_operation_item_mainImage);

        HomepageBean.OperationItem homepageLodgeUnitInfo = mVideoList.get(position);
//        homepageLodgeUnitInfo.imgUrl
        //  图片加载    imgv_mainImage  homepageLodgeUnitInfo.imageUrl

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
