package com.skx.tomike.tanklaboratory.widget.adapter;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;
import com.skx.tomikecommonlibrary.utils.SkxDrawableUtil;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setPadding(DpPxSpTool.INSTANCE.dip2px(parent.getContext(), 5),
                DpPxSpTool.INSTANCE.dip2px(parent.getContext(), 2),
                DpPxSpTool.INSTANCE.dip2px(parent.getContext(), 5),
                DpPxSpTool.INSTANCE.dip2px(parent.getContext(), 2));
        textView.setTextSize(12);
        textView.setGravity(Gravity.CENTER);

        textView.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.skx_212121));
        ViewCompat.setBackground(textView,
                new SkxDrawableUtil.Builder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(ContextCompat.getColor(parent.getContext(), R.color.skx_ff4081))
                        .setCornerRadius(8)
                        .create());

        textView.setText(mTagArray.get(position));
        return textView;
    }
}
