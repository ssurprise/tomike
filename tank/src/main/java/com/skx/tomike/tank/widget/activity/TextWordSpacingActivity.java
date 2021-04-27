package com.skx.tomike.tank.widget.activity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ScaleXSpan;
import android.widget.SeekBar;
import android.widget.TextView;

import com.skx.tomike.tank.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

import java.util.Locale;

/**
 * 描述 : TextView 设置字间距
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 8:27 PM
 */
public class TextWordSpacingActivity extends SkxBaseActivity<BaseViewModel> {

    private final String TEST_CONTENT = "壹a贰b弎c肆d伍e陆f柒g捌h玖i拾g佰k仟l万mnaJQK大王小王数1字2";

    private TextView mTvMutable;
    private TextView mTvMutableLabel;

    @Override
    protected void initParams() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_text_view_word_spacing;
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("TextView 设置字间距").create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        TextView tvStandard = findViewById(R.id.tv_textSpacing_standard);
        tvStandard.setText(TEST_CONTENT);

        mTvMutable = findViewById(R.id.tv_textSpacing_mutable);
        mTvMutable.setText(TEST_CONTENT);

        SeekBar seekBarMutable = findViewById(R.id.sb_textSpacing_mutable_progress);
        seekBarMutable.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvMutableLabel.setText(String.format(Locale.getDefault(), "字间距：%d", progress));
                mTvMutable.setText(applyKerning(TEST_CONTENT, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mTvMutableLabel = findViewById(R.id.tv_textSpacing_mutable_factor);
        mTvMutableLabel.setText("字间距：0");
    }

    /**
     * 调整内容间距。
     * 实现原理：在相邻的2个内容之间插入一个空字符，通过控制空字符的横向缩放倍数来实现字间距大小的调整。
     *
     * @param src     目标源
     * @param kerning 缩放倍数
     * @return 调整字间距后的内容
     */
    public static Spannable applyKerning(CharSequence src, float kerning) {
        if (src == null) return null;
        // 当间距数 <= 0，直接返回
        if (kerning <= 0) return src instanceof Spannable
                ? (Spannable) src
                : new SpannableString(src);

        final int srcLength = src.length();
        // 当源内容长度不足2位时，返回原内容
        if (srcLength < 2)
            return src instanceof Spannable ? (Spannable) src : new SpannableString(src);

        final String nonBreakingSpace = "\u00A0";
        final SpannableStringBuilder builder = src instanceof SpannableStringBuilder
                ? (SpannableStringBuilder) src : new SpannableStringBuilder(src);

        // 插入间距的
        for (int i = src.length() - 1; i >= 1; i--) {
            builder.insert(i, nonBreakingSpace);
            builder.setSpan(new ScaleXSpan(kerning), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }
}
