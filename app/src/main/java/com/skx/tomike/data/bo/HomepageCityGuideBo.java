package com.skx.tomike.data.bo;

import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 */

public class HomepageCityGuideBo implements IViewType {

    private HomepageBean.CityGuide homepageCityGuide;

    public HomepageCityGuideBo(HomepageBean.CityGuide homepageCityGuide) {
        this.homepageCityGuide = homepageCityGuide;
    }

    public HomepageBean.CityGuide getHomepageCityGuide() {
        if (homepageCityGuide == null) {
            homepageCityGuide = new HomepageBean.CityGuide();
        }
        return homepageCityGuide;
    }

    @Override
    public int getViewType() {
        return 4;
    }
}
