package com.skx.tomike.cannon.ui.activity;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.KeyboardTool;
import com.skx.common.utils.ScreenUtilKt;
import com.skx.common.utils.ToastTool;
import com.skx.tomike.cannon.R;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_KEYBOARD;

/**
 * Created by shiguotao on 2016/7/19.
 * <p/>
 * <p/>
 * 默认情况下，当页面中只有一个EditText，且不做任何设置时，EditText有光标，键盘不弹出，而且不会失去焦点，就算点击其他地方手动 clearFocus 焦点，照样还会重新自动获得焦点。
 * <p/>
 * 1. 如果不想让 EditText 初始获得焦点可以在父控件上设置
 * android:focusable="true"
 * android:focusableInTouchMode="true"
 * <p/>
 * <p>
 * 2. 清除焦点 view.clearFocus();
 * <p/>
 * <p/>
 * <p/>
 * 5. isFocusable()  返回此控件是否可以获得焦点
 * <p>
 * <p>
 * <p>
 * 3.windowSoftInputMode属性介绍：
 * https://www.cnblogs.com/exmyth/p/4696344.html
 * https://blog.csdn.net/qiutiandepaomo/article/details/84028558
 * <p>
 * <p>
 * <p>
 * 4.仿微信键盘切换:
 * https://www.imooc.com/article/40369
 */
@Route(path = ROUTE_PATH_KEYBOARD)
public class KeyboardActivity extends SkxBaseActivity<BaseViewModel> {

    private RelativeLayout mRlRoot;
    private ImageView mIvEmojiBtn;
    private ImageView mIvSendBtn;
    private EditText mEditText;
    private FrameLayout fl_keyboard_content;

    private final int STATE_NONE = 0;
    private final int STATE_KEYBOARD = 1;
    private final int STATE_EMOJI = 2;
    private int mState = STATE_NONE;

    private int keyboardHeight = 0;

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("键盘应用管理").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_keyboard;
    }

    @Override
    protected void initView() {
        mRlRoot = findViewById(R.id.rl_keyboard_root);
        mEditText = findViewById(R.id.et_keyboard_inputBox);
        mIvEmojiBtn = findViewById(R.id.iv_keyboard_emoji);
        mIvSendBtn = findViewById(R.id.iv_keyboard_send);
        fl_keyboard_content = findViewById(R.id.fl_keyboard_content);

        mEditText.setOnFocusChangeListener((v, hasFocus) -> {
            Log.e(TAG, "EditText hasFocus ->" + hasFocus);
            if (hasFocus) {
                updateState(STATE_KEYBOARD);
            }
        });

        mIvEmojiBtn.setOnClickListener(v -> {
            updateState(STATE_EMOJI);
        });
        mIvSendBtn.setOnClickListener(v -> {

        });
        mRlRoot.getViewTreeObserver().addOnGlobalLayoutListener(mGlobalLayoutListener);
    }

    private final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            // 判断窗口可见区域大小
            Rect r = new Rect();
            mRlRoot.getWindowVisibleDisplayFrame(r);

            int screenHeight = ScreenUtilKt.getScreenHeight(mActivity);
            int heightDifference = screenHeight - (r.bottom - r.top) - ScreenUtilKt.getStatusBarHeight(mActivity);
            boolean isKeyboardShowing = heightDifference > screenHeight / 3; //如果之前软键盘状态为显示，现在为关闭，或者之前为关闭，现在为显示，则表示软键盘的状态发生了改变

            if (isKeyboardShowing) {
                keyboardHeight = heightDifference;
                // 显示键盘 -> 隐藏底部导航栏
                Log.e(TAG, "onGlobalLayout: 键盘弹出");
                ToastTool.showToast(mActivity, "键盘弹出");
            } else {
                Log.e(TAG, "onGlobalLayout: 键盘关闭");
                // 输入法键盘 -> 显示导航栏
                ToastTool.showToast(mActivity, "键盘关闭");
            }
        }
    };

    private void updateState(int newState) {
        if (mState == newState) {
            return;
        }
        mState = newState;
        switch (mState) {
            case STATE_KEYBOARD:
                showInputKeyboard();
                break;
            case STATE_EMOJI:
                showEmojiView();
            default:
                break;
        }
    }

    /*
     * 显示输入法
     */
    private void showInputKeyboard() {
        Log.e(TAG, "showInputKeyboard");
        mEditText.requestFocus();
        //打开输入法
        KeyboardTool.getInstances(KeyboardActivity.this).showKeyboard(mEditText);
        new Handler(Looper.myLooper()).postDelayed(() -> {
            fl_keyboard_content.setVisibility(View.GONE);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }, 300);
    }

    private void showEmojiView() {
        Log.e(TAG, "showEmojiView");
        if (fl_keyboard_content.getVisibility() != View.VISIBLE) {
            mEditText.clearFocus();
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
            ViewGroup.LayoutParams lp = fl_keyboard_content.getLayoutParams();
            lp.height = keyboardHeight;
            fl_keyboard_content.setLayoutParams(lp);
            fl_keyboard_content.setVisibility(View.VISIBLE);
            //关闭键盘
            KeyboardTool.getInstances(KeyboardActivity.this).hideKeyboard(mEditText);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KeyboardTool.getInstances(KeyboardActivity.this).hideKeyboard(mEditText);
        mRlRoot.getViewTreeObserver().removeOnGlobalLayoutListener(mGlobalLayoutListener);
    }
}
