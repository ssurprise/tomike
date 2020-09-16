package com.skx.tomike.tanklaboratory.widget.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.skx.tomike.tanklaboratory.R;
import com.skx.common.utils.DpPxSpToolKt;
import com.skx.common.utils.SkxDrawableUtil;

import java.util.ArrayList;

/**
 * 描述 : 流式布局 demo adapter
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/24 8:05 PM
 */
public class FlowAdapter extends BaseAdapter {

    private final ArrayList<String> mTagArray = new ArrayList<>();

    {
        mTagArray.add("钢铁侠");
        mTagArray.add("雷神");
        mTagArray.add("绿巨人浩克");
        mTagArray.add("美国队长");
        mTagArray.add("黑寡妇");
        mTagArray.add("黑豹");
        mTagArray.add("蜘蛛侠");
        mTagArray.add("蚁人");
        mTagArray.add("奥创");
        mTagArray.add("奇异博士");
        mTagArray.add("银河护卫队");
        mTagArray.add("惊奇队长");
        mTagArray.add("死侍");
        mTagArray.add("钢铁侠钢铁侠钢铁侠钢铁侠钢铁侠钢铁侠钢铁侠钢铁侠");
        mTagArray.add("雷神");
        mTagArray.add("绿巨人浩克");
        mTagArray.add("美国队长");
        mTagArray.add("黑寡妇");
        mTagArray.add("黑豹");
        mTagArray.add("蜘蛛侠");
        mTagArray.add("蚁人");
        mTagArray.add("奥创");
        mTagArray.add("奇异博士");
        mTagArray.add("银河护卫队");
        mTagArray.add("惊奇队长");
        mTagArray.add("死侍");
    }

    @Override
    public int getCount() {
        return mTagArray.size();
    }

    @Override
    public Object getItem(int position) {
        return mTagArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setPadding(DpPxSpToolKt.dip2px(parent.getContext(), 12),
                DpPxSpToolKt.dip2px(parent.getContext(), 3),
                DpPxSpToolKt.dip2px(parent.getContext(), 12),
                DpPxSpToolKt.dip2px(parent.getContext(), 4));
        textView.setTextSize(12);
        textView.setGravity(Gravity.CENTER);

        textView.setText(mTagArray.get(position));
        textView.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.skx_212121));
        setItemBackground(parent.getContext(), textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setSelected(!textView.isSelected());
                setItemBackground(parent.getContext(), textView);
            }
        });

        return textView;
    }

    private void setItemBackground(Context context, TextView textView) {
        Drawable bg = textView.isSelected() ?
                new SkxDrawableUtil.Builder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(ContextCompat.getColor(context, R.color.skx_f05b72))
                        .setCornerRadius(DpPxSpToolKt.dip2px(context, 10))
                        .create() :
                new SkxDrawableUtil.Builder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setStroke(DpPxSpToolKt.dip2px(context, 1),
                                ContextCompat.getColor(context, R.color.skx_212121))
                        .setCornerRadius(DpPxSpToolKt.dip2px(context, 10))
                        .create();
        ViewCompat.setBackground(textView, bg);
    }
}
