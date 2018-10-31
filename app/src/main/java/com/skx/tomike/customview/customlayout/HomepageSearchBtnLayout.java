package com.skx.tomike.customview.customlayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.skx.tomike.R;
import com.skx.tomike.util.ViewPropertyHelper;
import com.skx.tomike.util.WidthHeightTool;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

/**
 * Created by shiguotao on 2017/3/21.
 * <p>
 * 首页 - 搜索按钮自定义控件
 */
public class HomepageSearchBtnLayout extends RelativeLayout {

    private final int STATE_SMALL = 1;
    private final int STATE_BIG = 2;
    private int state = STATE_BIG;
    private int smallStateViewWidth;
    private int bigStateViewWidth;

    public HomepageSearchBtnLayout(Context context) {
        this(context, null);
    }

    public HomepageSearchBtnLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomepageSearchBtnLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        smallStateViewWidth = DpPxSpTool.dip2px(context, 120);
        bigStateViewWidth = WidthHeightTool.getScreenWidth(context);

        LayoutInflater.from(context).inflate(R.layout.layout_xzhomepage_search_btn, this, true);
    }

    /**
     * 展开搜索按钮动画
     */
    public void extendSearchBtnAnimator() {
        if (state != STATE_BIG) {
            state = STATE_BIG;
            ViewPropertyHelper helper = new ViewPropertyHelper(this);
            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(helper, "width", this.getWidth(), bigStateViewWidth).setDuration(300);
            objectAnimator.start();
        }
    }

    /**
     * 展开搜索按钮动画
     */
    public void closeSearchBtnAnimator() {
        if (state != STATE_SMALL) {
            state = STATE_SMALL;
            ViewPropertyHelper helper = new ViewPropertyHelper(this);
            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(helper, "width", this.getWidth(), smallStateViewWidth).setDuration(300);
            objectAnimator.start();
        }
    }

    /**
     * 展开搜索按钮动画
     */
    public void gotoSearchPageAnimator() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "translationY", 0, -this.getHeight()).setDuration(300);
        objectAnimator.start();
    }
}
