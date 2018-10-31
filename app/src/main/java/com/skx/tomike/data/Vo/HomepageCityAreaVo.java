package com.skx.tomike.data.Vo;

import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 */
public class HomepageCityAreaVo implements IViewType {

    private HomepageBean.CityArea homepageCityArea;

    public HomepageCityAreaVo(HomepageBean.CityArea homepageCityArea) {
        this.homepageCityArea = homepageCityArea;
    }

    public HomepageBean.CityArea getHomepageCityArea() {
        if (homepageCityArea == null) {
            homepageCityArea = new HomepageBean.CityArea();
        }
        return homepageCityArea;
    }

    @Override
    public int getViewType() {
        return 5;
    }
}
