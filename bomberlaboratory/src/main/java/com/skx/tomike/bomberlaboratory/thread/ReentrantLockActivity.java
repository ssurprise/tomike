package com.skx.tomike.bomberlaboratory.thread;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("ReentrantLock demo").create();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.tryLock();
    }
}
