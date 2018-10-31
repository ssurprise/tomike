package com.skx.tomike.data.Vo;

import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 */
public class HomepageVideoVo implements IViewType {

    private HomepageBean.HomepageVideo homepageVideo;

    public HomepageVideoVo(HomepageBean.HomepageVideo homepageVideo) {
        this.homepageVideo = homepageVideo;
    }

    public HomepageBean.HomepageVideo getHomepageVideo() {
        if(homepageVideo==null){
            homepageVideo = new HomepageBean.HomepageVideo();
        }
        return homepageVideo;
    }

    @Override
    public int getViewType() {
        return 0;
    }
}
