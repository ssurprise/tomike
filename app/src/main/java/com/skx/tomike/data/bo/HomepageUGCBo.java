package com.skx.tomike.data.bo;

import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 * <p>
 * 首页- UGC
 */
public class HomepageUGCBo implements IViewType {

    private HomepageBean.UGC homepageUGC;

    public HomepageUGCBo(HomepageBean.UGC homepageUGC) {
        this.homepageUGC = homepageUGC;
    }

    public HomepageBean.UGC getHomepageUGC() {
        if (homepageUGC == null) {
            homepageUGC = new HomepageBean.UGC();
        }
        return homepageUGC;
    }

    @Override
    public int getViewType() {
        return 6;
    }
}
