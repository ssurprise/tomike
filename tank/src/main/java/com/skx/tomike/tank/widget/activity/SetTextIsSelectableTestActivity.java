package com.skx.tomike.tank.widget.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.skx.tomike.tank.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;


/**
 * setTextIsSelectable()方法测试类
 * <p>
 * vivo x7 ：长按无法复制，双击可实现可选复制
 * 三星note 3：xml中 android:textIsSelectable="true"   java中setTextIsSelectable(false); 这个时候如果跳出当前页面会报错：StackOverflowError，其他情况的设置一切都是ok的
 */
public class SetTextIsSelectableTestActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("TextView 复制").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_text_is_selectable_test;
    }

    @Override
    protected void initView() {
        TextView tv_textCopyTest_content = findViewById(R.id.textCopyTest_content);
        tv_textCopyTest_content.setText("啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊");

        tv_textCopyTest_content.setTextIsSelectable(false);
    }

    public void gotoOther(View view) {
        Toast.makeText(this, "点击有效哦~", Toast.LENGTH_SHORT).show();
    }
}
