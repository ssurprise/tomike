package com.skx.tomike.data.bo;

import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 */

public class HomepageOperationPointBo implements IViewType {

    private HomepageBean.OperationPoint homepageOperationPoint;

    public HomepageOperationPointBo(HomepageBean.OperationPoint homepageOperationPoint) {
        this.homepageOperationPoint = homepageOperationPoint;
    }

    public HomepageBean.OperationPoint getHomepageOperationPoint() {
        if(homepageOperationPoint==null){
            homepageOperationPoint = new HomepageBean.OperationPoint();
        }
        return homepageOperationPoint;
    }

    @Override
    public int getViewType() {
        return 3;
    }
}
