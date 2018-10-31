package com.skx.tomike.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.skx.tomike.R;
import com.skx.tomike.util.KeyboardTool;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shiguotao on 2016/7/19.
 * <p/>
 * <p/>
 * 默认情况下，当页面中只有一个EditText，且不做任何设置时，EditText有光标，键盘不弹出，而且不会失去焦点，就算点击其他地方手动 clearFocus 焦点，照样还会重新自动获得焦点。
 * <p/>
 * 1.  如果不想让 EditText 初始获得焦点可以在父控件上设置    android:focusable="true" android:focusableInTouchMode="true"
 * <p/>
 * 2.  mEditText.clearFocus(); 清除焦点
 * <p/>
 * <p/>
 * 3.  android:windowSoftInputMode="adjustResize" 重新计算高度。测试用例，activity 的root 控件是RelativeLayout .
 * EditText 居中显示。 button 按钮 below EditText. 间距比较大的时候button 被键盘覆盖掉。调整间距，会发现会存在Button 文字展示不全的情况，也就是说完全被挤压了
 * EditText 居中显示。 button 按钮 alignParentBottom   . marginBottom  间距比较大的时候，Button 会在EditText 后面，虽然按照xml绘制顺序，应该是Button 把EditText 覆盖掉，但是并没有
 * <p/>
 * 4. android:windowSoftInputMode="adjustPan" 会直接覆盖掉挡住的部分 这个就比较好理解了
 * <p/>
 * 5. isFocusable()  返回此控件是否可以获得焦点
 */
public class KeyboardActivity extends SkxBaseActivity {

    private RelativeLayout keyboard_container;
    private EditText mEditText;
    private Button btn;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        keyboard_container = (RelativeLayout) findViewById(R.id.keyboard_container);
        mEditText = (EditText) findViewById(R.id.keyboard_et);
        btn = (Button) findViewById(R.id.keyboard_btn);
        btn2 = (Button) findViewById(R.id.keyboard_btn2);

        /**
         * mEditText.setFocusable(true);
         * mEditText.setFocusableInTouchMode(true);
         * mEditText.requestFocus();//获取焦点 光标出现
         */

        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        mEditText.requestFocus();//获取焦点 光标出现

//        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if (hasFocus) {
//                    Log.e("edit_hasFocus", hasFocus + "");
//                    ToastTool.showToast(KeyboardActivity.this, "有焦点");
//                } else {
//                    Log.e("edit_hasFocus", hasFocus + "");
//                    ToastTool.showToast(KeyboardActivity.this, "无焦点");
//                }
//            }
//        });
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                KeyboardTool.getInstances(KeyboardActivity.this).toggleKeyboard();
            }
        }, 256);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardTool.getInstances(KeyboardActivity.this).toggleKeyboard();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardTool.getInstances(KeyboardActivity.this).hideKeyboard(btn);
            }
        });
    }

    // 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                KeyboardTool.getInstances(KeyboardActivity.this).toggleKeyboard();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }
}
