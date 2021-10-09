package com.skx.tomike.core;

import android.app.Application;

public class SkxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleObserver());
    }

}
