package com.skx.tomike.cannon.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skx.tomike.cannon.bean.User;


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
