package com.skx.tomike.tanklaboratory.animation.activity;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : Layout 添加/删除子view的过渡效果
 *
 * <p>
 * 知识点：利用LayoutTransition api提供的动效，有两种实现方式。
 * xml 设置方式->
 * android:animateLayoutChanges="" 开始子view的动画
 * android:layoutAnimation=""   设置动画样式
 * <p>
 * 代码设置方式->
 * android.view.ViewGroup#setLayoutAnimation(android.view.animation.LayoutAnimationController)
 * 可添加动画监听
 *
 * <p>
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2018/12/16 5:43 PM
 */
public class LayoutTransitionActivity extends SkxBaseActivity<BaseViewModel> {

    private LinearLayout mContainer;
    private List<View> mViewList = new ArrayList<>();

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("LayoutTransition 实现子view动画").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout_transition;
    }

    @Override
    protected void initView() {
        mContainer = findViewById(R.id.ll_layoutTransition_parent);
    }

    public void remove(View view) {
        if (mViewList.size() > 0) {
            mContainer.removeViewAt(0);
            mViewList.remove(0);
        }
    }

    public void add(View view) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(String.valueOf(mViewList.size()));
        textView.setLayoutParams(layoutParams);
        mContainer.addView(textView, 0);
        mViewList.add(0, textView);
    }


    /*
    This class enables automatic animations on layout changes in ViewGroup objects.
    To enable transitions for a layout container, create a LayoutTransition object and
    set it on any ViewGroup by calling setLayoutTransition(LayoutTransition).
    This will cause default animations to run whenever items are added to or removed from that container.
    To specify custom animations, use the setAnimator() method.

    目标：当ViewGroup添加或者删除View 时，可以自动执行过渡动画。

    使用：
    1.创建LayoutTransition对象
    2.调用setLayoutTransition(LayoutTransition)方法设置到ViewGroup上
    注意：这时的所有效果都是默认的。

    与setLayoutTransition对应的xml属性是 animateLayoutChanges =“true”，提供了默认的效果。

    */
}
