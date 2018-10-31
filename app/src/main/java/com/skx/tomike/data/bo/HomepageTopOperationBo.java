package com.skx.tomike.data.bo;

import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 * <p>
 * 首页- 运营位模块业务类
 */
public class HomepageTopOperationBo implements IViewType {

    private HomepageBean.Operation homepageOperation;

    public HomepageTopOperationBo(HomepageBean.Operation homepageOperation) {
        this.homepageOperation = homepageOperation;
    }

    public HomepageBean.Operation getHomepageOperation() {
        if(homepageOperation==null){
            homepageOperation = new HomepageBean.Operation();
        }
        return homepageOperation;
    }

    @Override
    public int getViewType() {
        return 7;
    }
}
