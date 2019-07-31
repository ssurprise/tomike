package com.skx.tomike.cannonlaboratory.widget;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by shiguotao on 2016/4/21.
 * <p/>
 * viewPager页面变形器-> 透明度
 */
public class AlphaPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        View tempPage = (View) page.getTag();
        if (position < -1) { // [-Infinity,-1)
            tempPage.setAlpha(1 - Math.abs(position));
        } else if (position <= 0) { // [-1,0]
            tempPage.setAlpha(Math.abs(position));
        }  else if (position <= 1) { // (0,1]
            tempPage.setAlpha(position);
        } else { // (1,+Infinity]
            tempPage.setAlpha(0);
        }
    }
}
