package com.skx.tomike.tank.widget.activity;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.SuperscriptSpan;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;
import com.skx.tomike.tank.widget.view.TagImageSpan;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTER_GROUP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TEXTVIEW_SPANNABLESTRING;

/**
 * 描述 : 富文本显示 - SpannableString
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/12/25 11:28 AM
 */
@Route(path = ROUTE_PATH_TEXTVIEW_SPANNABLESTRING, group = ROUTER_GROUP)
public class SpannableStringBuilderActivity extends SkxBaseActivity<BaseViewModel> {

    TextView textView;
    TextView textView_topMark;
    String contentStr = "时间：{23:48}";
    String contentStr1 = "价格：$199.99HDK";

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("富文本显示 - SpannableString").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_spannable_string_builder;
    }

    @Override
    protected void initView() {
        textView = findViewById(R.id.textView_value);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView_topMark = findViewById(R.id.textView_topMark);

        setStateView(textView, contentStr);

        setMarkView();
    }

    private void setMarkView() {
        SpannableString ss = new SpannableString(contentStr1);
        //设置上下标
//        ss.setSpan(new SubscriptSpan(), 7, contentStr1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //下标
        ss.setSpan(new SuperscriptSpan(), 7, contentStr1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //上标
        textView_topMark.setText(ss);
    }

    private void setStateView(TextView textView, String contentStr) {
        int tagStart = contentStr.indexOf("{");
        int tagEnd = contentStr.indexOf("}");
        String contentStr0 = contentStr.substring(0, tagStart);
        SpannableString contentStr0Style = new SpannableString(contentStr0);
        contentStr0Style.setSpan(new BackgroundColorSpan(Color.parseColor("#ffffff")), 0, contentStr0.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        contentStr0Style.setSpan(new ForegroundColorSpan(Color.parseColor("#323232")), 0, contentStr0.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(contentStr0Style);

        for (int i = tagStart; i <= tagEnd; i++) {
            char c = contentStr.charAt(i);
            String charStr = String.format(" %s ", c);
            SpannableString style = new SpannableString(charStr);
            style.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, charStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
            if (" : ".equals(charStr)) {
                textView.append(charStr);
                continue;
            } else if (" { ".equals(charStr) || " } ".equals(charStr)) {
                continue;
            } else {
                TagImageSpan span = new TagImageSpan(0, charStr.length());
                style.setSpan(span, 0, charStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            textView.append(style);
        }

        String contentStr2 = contentStr.substring(tagEnd + 1);
        SpannableString contentStr2Style = new SpannableString(contentStr2);
        contentStr2Style.setSpan(new BackgroundColorSpan(Color.parseColor("#ffffff")), 0, contentStr2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        contentStr2Style.setSpan(new ForegroundColorSpan(Color.parseColor("#323232")), 0, contentStr2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.append(contentStr2Style);
    }
}
