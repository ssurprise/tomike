package com.skx.tomike.repository;

import android.arch.lifecycle.MutableLiveData;
import android.os.SystemClock;

import com.skx.tomike.model.User;


public class UserProfileRepository {


    public static MutableLiveData<User> getUserProfile() {

        SystemClock.sleep(2000);

        MutableLiveData<User> liveData = new MutableLiveData<>();

        User user = new User();
        user.setName("测试");
        user.setNickName("加多宝");
        user.setUserId("0123456789");
        liveData.postValue(user);

        return liveData;
    }
}
