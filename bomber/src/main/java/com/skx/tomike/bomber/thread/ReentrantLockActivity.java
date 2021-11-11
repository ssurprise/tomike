package com.skx.tomike.bomber.thread;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

import java.util.concurrent.locks.ReentrantLock;

import static com.skx.tomike.bomber.RouteConstantsKt.ROUTER_GROUP;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_DAEMON;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_REENTRANT_LOCK;

@Route(path = ROUTE_PATH_THREAD_REENTRANT_LOCK, group = ROUTER_GROUP)
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
