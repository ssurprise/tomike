package com.skx.common.permission;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.skx.common.R;
import com.skx.common.utils.ScreenUtilKt;

import static com.skx.common.utils.DpPxSpToolKt.dip2px;


/**
 * 自定义Dialog
 */
public class PermissionRationaleDialog extends Dialog {

    private String mTitle;
    private String mMsg;
    private String positiveBtnText;
    private OnClickListener positiveButtonClickListener;
    private String negativeBtnText;
    private OnClickListener negativeButtonClickListener;

    private boolean mIsFailedPermission;
    private String[] mPermissions;
    private int posBtnTextColor;
    private int negBtnTextColor;

    public PermissionRationaleDialog(@NonNull Context context) {
        super(context, R.style.dialog_custom);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_dialog_layout);
        // title
        boolean showTitle = !TextUtils.isEmpty(mTitle);
        TextView tv_title = findViewById(R.id.dialog_title);
        if (showTitle) {
            tv_title.setText(mTitle);
        }
        tv_title.setVisibility(showTitle ? View.VISIBLE : View.GONE);
        // msg
        TextView tv_msg = findViewById(R.id.dialog_text);
        tv_msg.setText(mMsg);

        // 权限信息
        boolean showPermissionDesc = mPermissions != null && mPermissions.length != 0;
        ViewGroup perLayout = findViewById(R.id.ll_permission_desc_layout);
        if (showPermissionDesc) {
            TextView desc = findViewById(R.id.tv_permission_desc);
            desc.setText(mIsFailedPermission ? "请求失败的权限：" : "需要请求的权限：");
            Drawable point = ContextCompat.getDrawable(getContext(), R.drawable.tip_point);
            for (String permission : mPermissions) {
                LinearLayout wrapView = new LinearLayout(getContext());
                wrapView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                wrapView.setOrientation(LinearLayout.HORIZONTAL);
                ImageView imageView = new ImageView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(getContext(), 5), dip2px(getContext(), 5));
                layoutParams.topMargin = dip2px(getContext(), 7);
                layoutParams.leftMargin = dip2px(getContext(), 2);
                layoutParams.rightMargin = dip2px(getContext(), 5);
                imageView.setLayoutParams(layoutParams);
                ViewCompat.setBackground(imageView, point);
                wrapView.addView(imageView);
                TextView textView = new TextView(getContext());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                textView.setTextColor(Color.parseColor("#212121"));
                textView.setText(permission);
                wrapView.addView(textView, -1, -2);
                perLayout.addView(wrapView, -1, -2);
            }
        }
        perLayout.setVisibility(showPermissionDesc ? View.VISIBLE : View.GONE);

        // 确认
        boolean showBtnSure = !TextUtils.isEmpty(positiveBtnText);
        TextView btn_sure = findViewById(R.id.dialog_sure);
        if (showBtnSure) {
            btn_sure.setText(positiveBtnText);
            if (posBtnTextColor != 0) {
                btn_sure.setTextColor(posBtnTextColor);
            }
            btn_sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (positiveButtonClickListener != null) {
                        positiveButtonClickListener.onClick(PermissionRationaleDialog.this, v.getId());
                    }
                    dismiss();
                }
            });
        }
        btn_sure.setVisibility(showBtnSure ? View.VISIBLE : View.GONE);

        // 取消
        boolean showBtnCancel = !TextUtils.isEmpty(negativeBtnText);
        TextView btn_cancel = findViewById(R.id.dialog_cancel);
        if (showBtnCancel) {
            btn_cancel.setText(negativeBtnText);
            if (negBtnTextColor != 0) {
                btn_cancel.setTextColor(negBtnTextColor);
            }
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (negativeButtonClickListener != null) {
                        negativeButtonClickListener.onClick(PermissionRationaleDialog.this, v.getId());
                    }
                    dismiss();
                }
            });
        }
        btn_cancel.setVisibility(showBtnCancel ? View.VISIBLE : View.GONE);

        // 是否显示中间分隔线
        boolean showLine = showBtnCancel && showBtnSure;
        View lineView = findViewById(R.id.dialog_btn_parting_line);
        lineView.setVisibility(showLine ? View.VISIBLE : View.GONE);

        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            // 宽度全屏
            WindowManager windowManager = scanForActivity(getContext()).getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = display.getWidth() * 4 / 5; // 设置dialog宽度为屏幕的4/5
            lp.y = -display.getHeight() * 1 / 18; // 设置dialog宽度为屏幕的4/5
            window.setAttributes(lp);
        }

        // 点击Dialog外部消失
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public PermissionRationaleDialog setTitle(String title) {
        mTitle = title;
        return this;
    }

    public PermissionRationaleDialog setMessage(String msg) {
        mMsg = msg;
        return this;
    }

    public PermissionRationaleDialog setPermission(boolean isFailedPermission, String... permissions) {
        mPermissions = permissions;
        mIsFailedPermission = isFailedPermission;
        return this;
    }


    public PermissionRationaleDialog setPositiveButton(String btnText, OnClickListener clickListener) {
        positiveBtnText = btnText;
        positiveButtonClickListener = clickListener;
        return this;
    }

    public PermissionRationaleDialog setPositiveBtnTextColor(@ColorInt int positiveBtnTextColor) {
        if (positiveBtnTextColor != 0) {
            posBtnTextColor = positiveBtnTextColor;
        }
        return this;
    }

    public PermissionRationaleDialog setNegativeButton(String btnText, OnClickListener clickListener) {
        negativeBtnText = btnText;
        negativeButtonClickListener = clickListener;
        return this;
    }

    public PermissionRationaleDialog setNegativeBtnTextColor(@ColorInt int negativeBtnTextTextColor) {
        if (negativeBtnTextTextColor != 0) {
            negBtnTextColor = negativeBtnTextTextColor;
        }
        return this;
    }

    private static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }
}