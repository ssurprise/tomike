package com.skx.tomike.activity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ScaleXSpan;
import android.widget.SeekBar;
import android.widget.TextView;

import com.skx.tomike.R;

/**
 * TextView 设置字间距
 */
public class TextWordSpacingActivity extends SkxBaseActivity implements SeekBar.OnSeekBarChangeListener {

    String content = "壹贰弎肆伍陆柒捌玖使JQK大王小王数1字2";
    private TextView tv_textSpacing_original;
    private TextView tv_textSpecing_change;
    private TextView tv_textSpacing_label;
    private SeekBar seekb_contaienr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_word_spacing);

        initializeView();
        installListener();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        tv_textSpacing_original = (TextView) findViewById(R.id.textSpacing_original);
        tv_textSpecing_change = (TextView) findViewById(R.id.textSpacing_change);
        seekb_contaienr = (SeekBar) findViewById(R.id.textSpacing_seekBar_hun);
        tv_textSpacing_label = (TextView) findViewById(R.id.textSpacing_label);


        tv_textSpacing_original.setText(content);
        tv_textSpecing_change.setText(content);
        seekb_contaienr.setMax(10);
        tv_textSpacing_label.setText("字间距：" + 0);
    }

    @Override
    public void installListener() {
        super.installListener();
        seekb_contaienr.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.textSpacing_seekBar_hun:
                tv_textSpacing_label.setText("字间距：" + progress);
                tv_textSpecing_change.setText(applyKerning(content, progress));
                break;
            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

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
