package com.skx.common.permission;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.skx.common.R;

/**
 * 描述 : 权限弹窗 - 用于显示使用权限的目的
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:42 PM
 */
public class PermissionDialog extends DialogFragment {

    private FragmentActivity mContext;

    private TextView mTvTitle;
    private TextView mTvText;
    private LinearLayout mLlDescWrapper;
    private TextView mTvDesc;
    private TextView mTvCancelBtn;
    private TextView mTvConfirmBtn;


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
            setCancelable(false);
            builder.setView(root);
        }
        return builder.create();
    }

    private void initView(View root) {
//        mTvTitle = root.findViewById(R.id.tv_permission_dialog_title);
//        mTvText = root.findViewById(R.id.tv_permission_dialog_text);
//        mLlDescWrapper = root.findViewById(R.id.ll_permission_desc_wrap);
//        mTvDesc = root.findViewById(R.id.tv_permission_desc);
//        mTvCancelBtn = root.findViewById(R.id.permission_dialog_btn_cancel);
//        mTvConfirmBtn = root.findViewById(R.id.permission_dialog_btn_confirm);

//        mTvCancelBtn.setOnClickListener();
//        mTvConfirmBtn.setOnClickListener();
    }

    private void renderView() {

    }

    private void setListener() {
    }

}
