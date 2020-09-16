package com.skx.common.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class BaseViewModel extends AndroidViewModel {

    protected Application mApplication;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mApplication = application;
    }
}
