package com.skx.tomike.data.bo;

import com.skx.tomike.data.bean.HomepageBean;
import com.skx.tomike.interf.IViewType;

/**
 * Created by shiguotao on 2017/3/16.
 */
public class HomepageVideoBo implements IViewType {

    private HomepageBean.HomepageVideo homepageVideo;

    public HomepageVideoBo(HomepageBean.HomepageVideo homepageVideo) {
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
