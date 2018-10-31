package com.skx.tomike.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.skx.tomike.model.User;

public class UserProfileViewModel extends ViewModel {

    private MutableLiveData<User> user;

    public void initData() {

    }

    public LiveData<User> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
        }
        return user;
    }
}
