package com.skx.tomikecommonlibrary.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.skx.tomikecommonlibrary.R;

public abstract class SkxBaseActivity<T extends BaseViewModel> extends BaseMvvmActivity<T> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        nativeThemeStyle();
        super.onCreate(savedInstanceState);
        initParams();
        initContentView();
        subscribeEvent();
    }

    /**
     * 初始化主题样式
     */
    protected void nativeThemeStyle() {

    }

    private void initContentView() {
        if (useDefaultLayout()) {
            setContentView(R.layout.layout_base_ui);
            findViewById(R.id.iv_baseUI_header_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            TextView mTvTitle = findViewById(R.id.tv_baseUI_header_title);
            ImageView mIvMore = findViewById(R.id.iv_baseUI_header_more);
            configHeaderTitleView(mTvTitle);

            FrameLayout view = findViewById(R.id.fl_baseUI_content);
            View inflate = LayoutInflater.from(this).inflate(getLayoutId(), view, false);
            if (inflate != null) {
                view.addView(inflate);
            }

        } else {
            setContentView(getLayoutId());
        }
    }

    /**
     * 是否使用默认布局
     *
     * @return true：使用默认布局
     */
    protected boolean useDefaultLayout() {
        return true;
    }

    /**
     * 配置页面标题
     *
     * @param title 页面标题view
     */
    protected void configHeaderTitleView(@NonNull TextView title) {

    }

    /**
     * 初始化参数
     */
    protected abstract void initParams();

    /**
     * 获取页面布局
     *
     * @return 页面布局
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 事件订阅。可用于监听LiveData 的变化
     */
    protected abstract void subscribeEvent();
}
