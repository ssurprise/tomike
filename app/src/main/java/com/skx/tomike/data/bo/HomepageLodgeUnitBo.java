package com.skx.tomike.data.bo;

import com.skx.tomike.cannonlaboratory.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 */
public class HomepageLodgeUnitBo implements IViewType {

    private HomepageBean.LodgeUnit homepageLodgeUnit;

    public HomepageLodgeUnitBo(HomepageBean.LodgeUnit homepageLodgeUnit) {
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
