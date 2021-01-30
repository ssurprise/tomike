package com.skx.tomike.cannonlaboratory.ui.activity;

import android.os.Environment;
import android.widget.TextView;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.cannonlaboratory.R;
import com.tencent.tinker.lib.tinker.TinkerInstaller;


/**
 * 描述 : 热修复 - 微信Tinker
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/1/21 6:08 PM
 */
public class HotfixActivity extends SkxBaseActivity<BaseViewModel> {

    private TextView mTvHint;

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("热修复 - 微信Tinker").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hotfix_tinker;
    }

    @Override
    protected void initView() {
        mTvHint = findViewById(R.id.tv_hotfix_hint);
        findViewById(R.id.tv_hotfix_calculation).setOnClickListener(v -> {
            mTvHint.setText("0不能当做除数啊！ 啊，啊，啊  bong~ bong ~  爆炸了");
        });

        findViewById(R.id.tv_hotfix_fixBtn).setOnClickListener(v -> {
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
        });
    }
}
