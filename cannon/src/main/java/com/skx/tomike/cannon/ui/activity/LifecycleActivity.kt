package com.skx.tomike.cannon.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.skx.tomike.cannon.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

/**
 * 描述 : Lifecycle demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/10 9:15 AM
 */
public class LifecycleActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("Lifecycle demo").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lifecycle_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new Re());
    }

    static class Re implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate() {
            Log.e("LifecycleActivity", "OnLifecycleEvent:create");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart() {
            Log.e("LifecycleActivity", "OnLifecycleEvent:start");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume() {
            Log.e("LifecycleActivity", "OnLifecycleEvent:resume");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause() {
            Log.e("LifecycleActivity", "OnLifecycleEvent:pause");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop() {
            Log.e("LifecycleActivity", "OnLifecycleEvent:stop");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onDestroy() {
            Log.e("LifecycleActivity", "OnLifecycleEvent:destroy");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        void onAny() {
            Log.e("LifecycleActivity", "OnLifecycleEvent:any");
        }
    }
}
