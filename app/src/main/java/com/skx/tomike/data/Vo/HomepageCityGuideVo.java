package com.skx.tomike.data.Vo;

import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 */

public class HomepageCityGuideVo implements IViewType {

    private HomepageBean.CityGuide homepageCityGuide;

    public HomepageCityGuideVo(HomepageBean.CityGuide homepageCityGuide) {
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
