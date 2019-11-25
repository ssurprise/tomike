package com.skx.tomike.animlaboratory.tools;

import android.support.annotation.Keep;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shiguotao on 2016/5/6.
 * <p/>
 * 属性动画帮助类
 */
public class ViewPropertyHelper {
    private View mView;
    private int height;
    private int padding;
    private int paddingLeft;
    private int paddingTop;
    private int paddingBottom;
    private int paddingRight;
    private int width;

    public ViewPropertyHelper(View view) {
        super();
        this.mView = view;
    }

    @Keep
    public int getHeight() {
        return height;
    }

    @Keep
    public void setHeight(int height) {
        this.height = height;
        ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
        layoutParams.height = height;
        mView.setLayoutParams(layoutParams);
    }

    @Keep
    public int getPadding() {
        return padding;
    }

    @Keep
    public void setPadding(int padding) {
        this.padding = padding;
        mView.setPadding(padding, padding, padding, padding);
    }

    @Keep
    public int getPaddingTop() {
        return paddingTop;
    }

    @Keep
    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
        mView.setPadding(mView.getPaddingLeft(), paddingTop, mView.getPaddingRight(), mView.getPaddingBottom());
    }

    @Keep
    public int getWidth() {
        return width;
    }

    @Keep
    public void setWidth(int width) {
        this.width = width;
        ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
        layoutParams.width = width;
        mView.setLayoutParams(layoutParams);
    }

    @Keep
    public int getPaddingLeft() {
        return paddingLeft;
    }

    @Keep
    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        mView.setPadding(paddingLeft, mView.getPaddingTop(), mView.getPaddingRight(), mView.getPaddingBottom());
    }

    @Keep
    public int getPaddingBottom() {
        return paddingBottom;
    }

    @Keep
    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
        mView.setPadding(mView.getPaddingLeft(), mView.getPaddingTop(), mView.getPaddingRight(), this.paddingBottom);
    }

    @Keep
    public int getPaddingRight() {
        return paddingRight;
    }

    @Keep
    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        mView.setPadding(mView.getPaddingLeft(), mView.getPaddingTop(), paddingRight, mView.getPaddingBottom());
    }
}
