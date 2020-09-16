package com.skx.common.base;

import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

/**
 * 描述 : 头部view配置类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/23 2:01 PM
 */
public class TitleConfig {

    private @DrawableRes
    int mBackBtnRes;
    private @DrawableRes
    int mMoreBtnRes;
    private CharSequence mTitleText;
    private int mTitleTextSize;
    private @ColorRes
    int mTitleTextColor;
    private View.OnClickListener mBackBtnClickListener;
    private View.OnClickListener mMoreBtnClickListener;
    private View mCustomTitleView;

    public int getBackBtnRes() {
        return mBackBtnRes;
    }

    public int getMoreBtnRes() {
        return mMoreBtnRes;
    }

    public CharSequence getTitleText() {
        return mTitleText;
    }

    public int getTitleTextSize() {
        return mTitleTextSize;
    }

    public int getTitleTextColor() {
        return mTitleTextColor;
    }

    public View getCustomTitleView() {
        return mCustomTitleView;
    }

    public View.OnClickListener getBackBtnClickListener() {
        return mBackBtnClickListener;
    }

    public View.OnClickListener getMoreBtnClickListener() {
        return mMoreBtnClickListener;
    }

    public static class Builder {
        private @DrawableRes
        int mBackBtnRes;
        private @DrawableRes
        int mMoreBtnRes;
        private CharSequence mTitleText;
        private int mTitleTextSize;
        private @ColorRes
        int mTitleTextColor;
        private View.OnClickListener mBackBtnClickListener;
        private View.OnClickListener mMoreBtnClickListener;
        private View mCustomTitleView;

        public Builder setBackBtnRes(@DrawableRes int backRes) {
            this.mBackBtnRes = backRes;
            return this;
        }

        public Builder setMoreBtnRes(@DrawableRes int moreRes) {
            this.mMoreBtnRes = moreRes;
            return this;
        }

        public Builder setTitleText(CharSequence title) {
            this.mTitleText = title;
            return this;
        }

        public Builder setTitleTextSize(int titleTextSize) {
            this.mTitleTextSize = titleTextSize;
            return this;
        }

        public Builder setTitleTextColor(@ColorRes int titleTextColor) {
            this.mTitleTextColor = titleTextColor;
            return this;
        }

        public Builder setCustomTitleView(View customTitleView) {
            this.mCustomTitleView = customTitleView;
            return this;
        }

        public Builder setBackBtnClickListener(View.OnClickListener backBtnClickListener) {
            this.mBackBtnClickListener = backBtnClickListener;
            return this;
        }

        public Builder setMoreBtnClickListener(View.OnClickListener moreBtnClickListener) {
            this.mMoreBtnClickListener = moreBtnClickListener;
            return this;
        }

        public TitleConfig create() {
            TitleConfig titleConfig = new TitleConfig();
            titleConfig.mBackBtnRes = this.mBackBtnRes;
            titleConfig.mMoreBtnRes = this.mMoreBtnRes;
            titleConfig.mTitleText = this.mTitleText;
            titleConfig.mTitleTextSize = this.mTitleTextSize;
            titleConfig.mTitleTextColor = this.mTitleTextColor;
            titleConfig.mCustomTitleView = this.mCustomTitleView;
            titleConfig.mBackBtnClickListener = this.mBackBtnClickListener;
            titleConfig.mMoreBtnClickListener = this.mMoreBtnClickListener;
            return titleConfig;
        }
    }
}
