package com.skx.tomike.data.Vo;

import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 */
public class HomepageLodgeUnitVo implements IViewType {

    private HomepageBean.LodgeUnit homepageLodgeUnit;

    public HomepageLodgeUnitVo(HomepageBean.LodgeUnit homepageLodgeUnit) {
        this.homepageLodgeUnit = homepageLodgeUnit;
    }

    public HomepageBean.LodgeUnit getHomepageLodgeUnit() {
        if (homepageLodgeUnit == null) {
            homepageLodgeUnit = new HomepageBean.LodgeUnit();
        }
        return homepageLodgeUnit;
    }

    @Override
    public int getViewType() {
        return 1;
    }
}
