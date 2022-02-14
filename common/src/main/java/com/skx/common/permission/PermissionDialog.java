package com.skx.common.permission;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;

import com.skx.common.R;

import static com.skx.common.utils.DpPxSpToolKt.dip2px;

/**
 * 描述 : 权限弹窗 - 用于显示使用权限的目的
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:42 PM
 */
public class PermissionDialog extends DialogFragment {

    private FragmentActivity mContext;

    private TextView mTvTitle;
    private ViewGroup perLayout;
    private TextView mTvText;
    private TextView mTvDesc;
    private View mDivideLineView;
    private TextView mTvCancelBtn;
    private TextView mTvConfirmBtn;


    private String mTitle;
    private String mMsg;
    private boolean mIsFailedPermission;
    private String[] mPermissions;
    private int posBtnTextColor;
    private int negBtnTextColor;

    private String positiveBtnText;
    private DialogInterface.OnClickListener positiveButtonClickListener;
    private String negativeBtnText;
    private DialogInterface.OnClickListener negativeButtonClickListener;


    public PermissionDialog getInstance() {
        PermissionDialog permissionDialog = new PermissionDialog();
        Bundle bundle = new Bundle();

        permissionDialog.setArguments(bundle);
        return permissionDialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (FragmentActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog getDialog() {
        return super.getDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        if (mContext != null) {
            View root = LayoutInflater.from(mContext).inflate(R.layout.permission_dialog_layout, null);
            initView(root);
            renderView();
            setListener();
            // 点击Dialog外部消失
            setCancelable(false);
            builder.setView(root);
        }
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
                    /*
             val window: Window? = dialog?.window
        window?.apply {
            // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
            setBackgroundDrawableResource(android.R.color.transparent)
            val params: WindowManager.LayoutParams? = attributes
            params?.gravity = Gravity.CENTER
            // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
            params?.width = resources.displayMetrics.widthPixels - ScreenUtil.dip2px(mContext, 105f)
            attributes = params
        }
             */
    }

    private void initView(View root) {
        mTvTitle = root.findViewById(R.id.tv_permission_dialog_title);
        mTvText = root.findViewById(R.id.tv_permission_dialog_text);
        perLayout = root.findViewById(R.id.ll_permission_desc_layout);
        mTvDesc = root.findViewById(R.id.tv_permission_desc);
        mDivideLineView = root.findViewById(R.id.dialog_btn_parting_line);
        mTvCancelBtn = root.findViewById(R.id.tv_permission_dialog_btn_cancel);
        mTvConfirmBtn = root.findViewById(R.id.tv_permission_dialog_btn_confirm);


        mTvCancelBtn.setOnClickListener(v -> {
            if (negativeButtonClickListener != null) {
                negativeButtonClickListener.onClick(getDialog() , v.getId());
            }
            dismiss();
        });
        mTvConfirmBtn.setOnClickListener(v -> {
            if (positiveButtonClickListener != null) {
                positiveButtonClickListener.onClick(getDialog(), v.getId());
            }
            dismiss();
        });
    }

    private void renderView() {
        boolean showPermissionDesc = mPermissions != null && mPermissions.length != 0;
        // 权限信息
        if (showPermissionDesc) {
            mTvDesc.setText(mIsFailedPermission ? "请求失败的权限：" : "需要请求的权限：");
            Drawable point = ContextCompat.getDrawable(mContext, R.drawable.tip_point);
            for (String permission : mPermissions) {
                LinearLayout wrapView = new LinearLayout(mContext);
                wrapView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                wrapView.setOrientation(LinearLayout.HORIZONTAL);
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(mContext, 5), dip2px(mContext, 5));
                layoutParams.topMargin = dip2px(mContext, 7);
                layoutParams.leftMargin = dip2px(mContext, 2);
                layoutParams.rightMargin = dip2px(mContext, 5);
                imageView.setLayoutParams(layoutParams);
                ViewCompat.setBackground(imageView, point);
                wrapView.addView(imageView);
                TextView textView = new TextView(mContext);
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
        if (showBtnSure) {
            mTvConfirmBtn.setText(positiveBtnText);
            if (posBtnTextColor != 0) {
                mTvConfirmBtn.setTextColor(posBtnTextColor);
            }
        }
        mTvConfirmBtn.setVisibility(showBtnSure ? View.VISIBLE : View.GONE);

        // 取消
        boolean showBtnCancel = !TextUtils.isEmpty(negativeBtnText);
        if (showBtnCancel) {
            mTvCancelBtn.setText(negativeBtnText);
            if (negBtnTextColor != 0) {
                mTvCancelBtn.setTextColor(negBtnTextColor);
            }
        }
        mTvCancelBtn.setVisibility(showBtnCancel ? View.VISIBLE : View.GONE);

        // 是否显示中间分隔线
        boolean showLine = showBtnCancel && showBtnSure;
        mDivideLineView.setVisibility(showLine ? View.VISIBLE : View.GONE);
    }

    private void setListener() {
    }

}
