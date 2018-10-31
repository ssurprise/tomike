package com.skx.tomike.activity.designpattern;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;
import com.skx.tomike.javabean.User;
import com.skx.tomike.javabean.UserSetting;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 * <p>
 * <p>
 *
 * @author shiguotao
 */
public class ObserverPatternActivity extends SkxBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        refreshView();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        setContentView(R.layout.activity_pattern_observer);
        TextView note = (TextView) findViewById(R.id.clonePattern_note);
    }

    /*
    观察者模式又被称作发布/订阅模式，观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。
    这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己。
     */

    @Override
    public void refreshView() {
        super.refreshView();

        User user = new User();
        user.userName = "墨尔本";
        user.password = "5684553";

        UserSetting userSetting = new UserSetting();
        userSetting.settingName = "睡眠设置";
        userSetting.settingValue = "YES";
        user.userSetting = userSetting;

        Log.e("复制前", user.toString());
        User userClone = user.clone();
        Log.e("复制后", userClone.toString());

        userClone.userName = "墨尔本二代";
        userClone.userSetting.settingName = "自启动设置";
        Log.e("修改后，原对象", user.toString());
        Log.e("修改后，复制对象", userClone.toString());
    }
}
