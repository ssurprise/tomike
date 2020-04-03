package com.skx.tomikecommonlibrary.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.skx.tomikecommonlibrary.R;

public abstract class SkxBaseActivity<T extends BaseViewModel> extends BaseMvvmActivity<T> {

    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        nativeThemeStyle();
        super.onCreate(savedInstanceState);
        initParams();
        initContentView();
        initView();
        subscribeEvent();
    }

    /**
     * 初始化主题样式
     */
    protected void nativeThemeStyle() {
    }

    private void initContentView() {
        final TitleConfig titleConfig = configHeaderTitle();
        if (titleConfig == null) {
            // 没有配置title 信息，加载子类布局
            setContentView(getLayoutId());
            return;
        }

        setContentView(R.layout.layout_base_ui);

        // 配置title view
        if (titleConfig.getCustomTitleView() == null) {
            buildDefaultTitleView(titleConfig);
        } else {
            buildCustomTitleView(titleConfig);
        }

        // 配置内容view
        FrameLayout view = findViewById(R.id.fl_baseUI_content);
        View inflate = LayoutInflater.from(this).inflate(getLayoutId(), view, false);
        if (inflate != null) {
            view.addView(inflate);
        }
    }

    private void buildDefaultTitleView(final TitleConfig config) {
        // 标题
        TextView mTvTitle = findViewById(R.id.tv_baseUI_header_title);
        mTvTitle.setText(config.getTitleText());
        if (config.getTitleTextColor() > 0) {
            mTvTitle.setTextColor(config.getTitleTextColor());
        }
        if (config.getTitleTextSize() > 0) {
            mTvTitle.setTextSize(config.getTitleTextSize());
        }

        // 左边返回按钮
        ImageView mBtnBack = findViewById(R.id.iv_baseUI_header_back);
        if (config.getBackBtnRes() > 0) {
            mBtnBack.setImageResource(config.getBackBtnRes());
        }
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (config.getBackBtnClickListener() == null) {
                    finish();
                    return;
                }
                config.getBackBtnClickListener().onClick(v);
            }
        });

        // 右边更多按钮
        ImageView mBtnMore = findViewById(R.id.iv_baseUI_header_more);
        if (config.getMoreBtnRes() > 0) {
            mBtnMore.setImageResource(config.getMoreBtnRes());
        }
        mBtnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (config.getMoreBtnClickListener() == null) {
                    finish();
                    return;
                }
                config.getMoreBtnClickListener().onClick(v);
            }
        });
    }

    private void buildCustomTitleView(TitleConfig config) {
        if (config == null || config.getCustomTitleView() == null) {
            return;
        }
        RelativeLayout mTitleWrap = findViewById(R.id.rl_baseUI_header_title);
        mTitleWrap.removeAllViews();
        mTitleWrap.addView(config.getCustomTitleView());
    }

    /**
     * 初始化参数
     */
    protected abstract void initParams();

    /**
     * 配置页面标题
     */
    protected TitleConfig configHeaderTitle() {
        return null;
    }

    /**
     * 获取页面布局
     *
     * @return 页面布局
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 事件订阅。可用于监听LiveData 的变化
     */
    protected void subscribeEvent() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
