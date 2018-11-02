package com.skx.tomike.activity.designpattern;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;
import com.skx.tomike.javabean.User;
import com.skx.tomike.javabean.UserSetting;

/**
 * 原型模式
 * <p>
 * 1.super.clone 拷贝如果有包含对象，属于浅拷贝。包含的对象属性也需要实现Cloneable接口，重写clone方法。否则修改复制对象的对象值，则会影响到源对象的属性
 * 原型模式可以解决构建复杂对象的资源消耗问题，能狗在某些场景下提升创建对象的效率。另外一个重要的用户就是保护性考拷贝
 * 通过实现Cloneable接口的原型模式在调用clone函数改造实例时并不一定比通过new 操作速度快，只有当通过new构造对象较为耗时或者成本较高时，通过clone方法才能获得效率上的提升。
 * 优点：原型模式是在内存中二进制流的拷贝，要比直接new一个对象性能好的多，特别是要在一个循环体内产生大量的对象时。
 * 缺点：直接在内存中拷贝构造函数是不会执行的，在实际开发中应当要特别注意这点。
 *
 * @author shiguotao
 */
public class ClonePatternActivity extends SkxBaseActivity {

    String noteStr = "super.clone 拷贝如果有包含对象，属于浅拷贝。包含的对象属性也需要实现Cloneable接口，重写clone方法。否则修改复制对象的对象值，则会影响到源对象的属性\n"
            + "原型模式可以解决构建复杂对象的资源消耗问题，能狗在某些场景下提升创建对象的效率。另外一个重要的用户就是保护性考拷贝\n " +
            "通过实现Cloneable接口的原型模式在调用clone函数改造实例时并不一定比通过new 操作速度快，只有当通过new构造对象较为耗时或者成本较高时，通过clone方法才能获得效率上的提升。\n" +
            "优点：原型模式是在内存中二进制流的拷贝，要比直接new一个对象性能好的多，特别是要在一个循环体内产生大量的对象时。\n" +
            "缺点：直接在内存中拷贝构造函数是不会执行的，在实际开发中应当要特别注意这点。";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        refreshView();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        setContentView(R.layout.activity_pattern_clone);
        TextView note = (TextView) findViewById(R.id.clonePattern_note);
        note.setText(noteStr);
    }

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