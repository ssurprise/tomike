package com.skx.tomike.tacticallaboratory.pattern.observer;

import com.skx.tomike.tacticallaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

/**
 * 观察者模式
 * <p>
 * <p>
 *
 * @author shiguotao
 */
public class ObserverPatternActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("观察者模式").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pattern_observer;
    }

    @Override
    protected void initView() {

    }


    /*
    观察者模式又被称作发布/订阅模式，观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。
    这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己。
     */
    private void refreshView() {

    }
}
