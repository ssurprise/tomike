package com.skx.tomike.data.bo;

import com.skx.tomike.cannonlaboratory.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 */
public class HomepageCityAreaBo implements IViewType {

    private HomepageBean.CityArea homepageCityArea;

    public HomepageCityAreaBo(HomepageBean.CityArea homepageCityArea) {
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
