package com.skx.tomike.tanklaboratory.widget.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.utils.SkxDrawableUtil;

public class PopupWindowActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn1;
    private Button mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow);

        mBtn1 = findViewById(R.id.popupWindow_btn1);
        mBtn2 = findViewById(R.id.popupWindow_btn2);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.popupWindow_btn1) {
            showPopupWindow(mBtn1);
        } else if (i == R.id.popupWindow_btn2) {
            showPopupWindow(mBtn2);
        }
    }


    public void showPopupWindow(View view) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_test, null);

        ViewCompat.setBackground(contentView,
                (new SkxDrawableUtil()).getBuilder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(Color.parseColor("#2cb298"))
                        .setStroke(Color.parseColor("#30c3a6"), 3)
                        .setCornerRadius(3)
                        .create());
        // 这里最后一个参数设置成false,点击其他区域，popupWindow 不会消失，返回键也无效。只要给popupWindow 设置了背景，可以返回，点击其他区域无效
        // 设置成true, 没有设置背景，按返回键 和 点击其他区域无效
        PopupWindow popupWindow = new PopupWindow(contentView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);

        // popupWindow 需要设置背景  返回按钮才生效，否则返回按键不生效
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(dw);
//         PopupWindow 进入进出动画
//        popupWindow.setAnimationStyle(R.style.popup_window_anim_style);
        popupWindow.showAsDropDown(view);
    }
}
