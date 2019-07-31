package com.skx.tomike.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.cannonlaboratory.bean.HomepageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2017/3/16.
 * <p>
 * 首页 -  视频item
 */
public class HomepageUGCItemAdapter extends PagerAdapter {

    private Context mContext;
    private List<HomepageBean.UGCItemInfo> mUGCList;

    public HomepageUGCItemAdapter(Context mContext, List<HomepageBean.UGCItemInfo> videoLists) {
        this.mContext = mContext;
        if (videoLists == null) {
            this.mUGCList = new ArrayList<>();
        } else {
            this.mUGCList = videoLists;
        }
    }

    @Override
    public int getCount() {
        return mUGCList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_ugc_item, container, false);
        TextView tv_content = (TextView) view.findViewById(R.id.homepage_ugc_item_content);
        TextView tv_contentCreateTime = (TextView) view.findViewById(R.id.homepage_ugc_item_contentCreateTime);

        HomepageBean.UGCItemInfo ugcItem = mUGCList.get(position);
        tv_content.setText(ugcItem.content);
        tv_contentCreateTime.setText(ugcItem.time);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
