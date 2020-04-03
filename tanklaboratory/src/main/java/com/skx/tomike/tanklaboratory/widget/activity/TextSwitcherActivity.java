package com.skx.tomike.tanklaboratory.widget.activity;

import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

/**
 * 描述 : TextSwitcher demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 8:31 PM
 */
public class TextSwitcherActivity extends SkxBaseActivity {

    private final String NAME_COFFEE = "咖啡机";
    private final String NAME_CAKE = "面包机";

    private TextSwitcher textSwitcher;
    private TextSwitcher textSwitcher2;

    private String index = NAME_COFFEE;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("TextSwitcher 实现效果").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_text_switcher;
    }

    @Override
    protected void initView() {
        textSwitcher = findViewById(R.id.ts_textSwitcher_xml_implement);

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);
        textSwitcher.setCurrentText(index);

        textSwitcher2 = findViewById(R.id.ts_textSwitcher_code_implement);
        textSwitcher2.setInAnimation(this, R.anim.show);
        textSwitcher2.setOutAnimation(this, R.anim.hide);
        textSwitcher2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(TextSwitcherActivity.this);
                textView.setTextSize(14);
                textView.setTextColor(Color.BLACK);
                return textView;
            }
        });
        textSwitcher2.setCurrentText(index);

        findViewById(R.id.btn_textSwitcher_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NAME_COFFEE.equalsIgnoreCase(index)) {
                    index = NAME_CAKE;

                } else if (NAME_CAKE.equalsIgnoreCase(index)) {
                    index = NAME_COFFEE;
                }
                textSwitcher.setText(index);
                textSwitcher2.setText(index);
            }
        });
    }
}
