package com.skx.tomike.cannon.ui;

import android.content.Context;

public class AppContext {

    private static AppContext mAppContext;
    private Context mContext;

    private AppContext() {
    }

    public static AppContext getInstance() {
        if (mAppContext == null) {
            mAppContext = new AppContext();
        }
        return mAppContext;
    }

    public void setAppContext(Context context) {
        if (mContext == null) {
            mContext = context;
        }
    }

    public Context getAppContext() {
        return mContext;
    }
}