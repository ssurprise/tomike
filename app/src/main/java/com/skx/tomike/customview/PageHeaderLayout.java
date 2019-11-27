package com.skx.tomike.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skx.tomike.R;

/**
 * 页面页眉布局
 */
public class PageHeaderLayout extends RelativeLayout {
    private ImageView iv_backImage;
    private TextView tv_headerName;
    private TextView tv_operate;

    public PageHeaderLayout(Context context) {
        this(context, null);
    }

    public PageHeaderLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageHeaderLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_page_header, this, true);
        iv_backImage = (ImageView) findViewById(R.id.layout_page_header_backImage);
        tv_headerName = (TextView) findViewById(R.id.layout_page_header_name);
        tv_operate = (TextView) findViewById(R.id.layout_page_header_operate);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PageHeaderLayout);
        String headerText = ta.getString(R.styleable.PageHeaderLayout_headerText);
        float headerTextSize = ta.getFloat(R.styleable.PageHeaderLayout_headerTextSize, 14);
        int headerTextColor = ta.getColor(R.styleable.PageHeaderLayout_headerTextColor,getResources().getColor(R.color.skx_323232));
        String operateText = ta.getString(R.styleable.PageHeaderLayout_operateText);
        float operateTextSize = ta.getFloat(R.styleable.PageHeaderLayout_operateTextSize, 14);
        int operateTextColor = ta.getColor(R.styleable.PageHeaderLayout_operateTextColor, getResources().getColor(R.color.skx_ff4081));
        int backResId = ta.getResourceId(R.styleable.PageHeaderLayout_backBtnSrc,R.drawable.back_img);

        setHeaderName(headerText);
        setHeaderTextSize(headerTextSize);
        setHeaderTextColor(headerTextColor);
        setOperateName(operateText);
        setOperateTextSize(operateTextSize);
        setOperateTextColor(operateTextColor);
        setBackBtnImage(backResId);
        ta.recycle();

        setBackgroundColor(Color.parseColor("#ffffff"));
        tv_headerName.getPaint().setFakeBoldText(true);
        tv_operate.getPaint().setFakeBoldText(true);
    }

    /**
     * 设置页面页眉 标题
     *
     * @param headerName 标题
     */
    public void setHeaderName(String headerName) {
        tv_headerName.setText(headerName);
    }

    /**
     * 设置页眉字体大小
     *
     * @param headerTextSize 字体大小
     */
    public void setHeaderTextSize(Float headerTextSize) {
        tv_headerName.setTextSize(headerTextSize);
    }

    /**
     * 设置页眉字体颜色
     *
     * @param headerTextColor 字体颜色
     */
    public void setHeaderTextColor(int headerTextColor) {
        tv_headerName.setTextColor(headerTextColor);
    }

    /**
     * 设置操作按钮显示名称
     *
     * @param operateName 名称
     */
    public void setOperateName(String operateName) {
        tv_operate.setText(operateName);
    }

    /**
     * 设置操作按钮文字大小
     *
     * @param operateTextSize 文字大小
     */
    public void setOperateTextSize(Float operateTextSize) {
        tv_operate.setTextSize(operateTextSize);
    }

    /**
     * 操作按钮文字颜色
     *
     * @param operateTextColor 颜色
     */
    public void setOperateTextColor(int operateTextColor) {
        tv_operate.setTextColor(operateTextColor);
    }

    /**
     * 设置返回按钮图片
     *
     * @param resId 图片的资源id
     */
    public void setBackBtnImage(int resId) {
        iv_backImage.setImageResource(resId);
    }

    public void setBackBtnClickListener(OnClickListener listener){
        iv_backImage.setOnClickListener(listener);
    }

    public void setOperateBtnClickListener(OnClickListener listener){
        tv_operate.setOnClickListener(listener);
    }
}
