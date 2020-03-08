package com.skx.tomike.cannonlaboratory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.HomepageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2017/3/16.
 * <p>
 * 首页 -  视频item
 */
public class HomepageLodgeUnitItemAdapter extends PagerAdapter {

    private Context mContext;
    private List<HomepageBean.LodgeUnitItem> mLodgeUnitList;
    /**
     * 循环次数，默认是1
     */
    private int loopTimes = 1;

    public HomepageLodgeUnitItemAdapter(Context mContext, List<HomepageBean.LodgeUnitItem> videoLists) {
        this.mContext = mContext;
        if (videoLists == null) {
            this.mLodgeUnitList = new ArrayList<>();
        } else {
            this.mLodgeUnitList = videoLists;
        }
    }

    public List<HomepageBean.LodgeUnitItem> getLodgeUnitList() {
        if (mLodgeUnitList == null) {
            this.mLodgeUnitList = new ArrayList<>();
        }
        return mLodgeUnitList;
    }

    /**
     * 设置循环次数
     *
     * @param loopTimes 循环次数
     */
    public void setLoopTimes(int loopTimes) {
        this.loopTimes = loopTimes;
    }

    @Override
    public int getCount() {
        return mLodgeUnitList.size() <= 1 ? 1 : mLodgeUnitList.size() * loopTimes;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_homepage_lodgeunt_item, container, false);
        ImageView imgv_mainImage = (ImageView) view.findViewById(R.id.homepage_lodgeUnit_item_mainImage);

        HomepageBean.LodgeUnitItem homepageLodgeUnitInfo = mLodgeUnitList.get(position % mLodgeUnitList.size());
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
