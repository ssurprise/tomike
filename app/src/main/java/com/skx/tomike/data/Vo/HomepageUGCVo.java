package com.skx.tomike.data.Vo;

import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 * <p>
 * 首页- UGC
 */
public class HomepageUGCVo implements IViewType {

    private HomepageBean.UGC homepageUGC;

    public HomepageUGCVo(HomepageBean.UGC homepageUGC) {
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
