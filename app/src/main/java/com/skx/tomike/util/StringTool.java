package com.skx.tomike.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ScaleXSpan;

/**
 * Created by shiguotao on 2017/3/29.
 * <p>
 * 字符串工具类
 */
public class StringTool {

    /**
     * 获取包含中文的字符串长度,汉字占2个单位，英文、数字或下划线占1个单位
     *
     * @param validateStr 指定的字符串
     * @return 字符串的长度
     */
    public static int getChineseLength(String validateStr) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < validateStr.length(); i++) {
            String temp = validateStr.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 获得部分高亮显示的 SpannableStringBuilder
     *
     * @param content        内容
     * @param startPos       高亮起始位置
     * @param endPos         高亮结束位置
     * @param defaultColor   默认颜色 eg:ContextCompat.getColor(mContext, R.color.xz_323232)
     * @param highlightColor 高亮颜色 eg:ContextCompat.getColor(mContext, R.color.xz_ff4081)
     * @return 高亮效果的SpannableStringBuilder
     */
    public static SpannableStringBuilder getHighlightSpannable(String content, int startPos, int endPos, int defaultColor, int highlightColor) {
        if (content == null) return null;
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);

        ForegroundColorSpan defaultForegroundColorSpan = new ForegroundColorSpan(defaultColor);
        if (content.length() < startPos || content.length() < endPos || startPos >= endPos) {
            ssb.setSpan(defaultForegroundColorSpan, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        } else {
            ForegroundColorSpan highlightForegroundColorSpan2 = new ForegroundColorSpan(highlightColor);

            ssb.setSpan(defaultForegroundColorSpan, 0, startPos, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            ssb.setSpan(highlightForegroundColorSpan2, startPos, endPos, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            ssb.setSpan(defaultForegroundColorSpan, endPos, ssb.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return ssb;
    }

    /**
     * 获得部分高亮显示的 SpannableStringBuilder
     *
     * @param content                   内容
     * @param startPos                  高亮起始位置
     * @param endPos                    高亮结束位置
     * @param defaultColorResourcesId   默认颜色 eg: R.color.xz_323232
     * @param highlightColorResourcesId 高亮颜色 eg:R.color.xz_ff4081
     * @return 高亮效果的SpannableStringBuilder
     */
    public static SpannableStringBuilder getHighlightSpannable(Context context, String content, int startPos, int endPos, int defaultColorResourcesId, int highlightColorResourcesId) {
        return getHighlightSpannable(content, startPos, endPos, ContextCompat.getColor(context, defaultColorResourcesId), ContextCompat.getColor(context, highlightColorResourcesId));
    }

    /**
     * 调整文本字间距
     *
     * @param target  源内容
     * @param kerning 间距
     * @return 调整间距后的Spannable
     */
    public static Spannable textSpacing(CharSequence target, float kerning) {
        if (target == null) return null;
        final int srcLength = target.length();
        if (srcLength < 2) return target instanceof Spannable
                ? (Spannable) target
                : new SpannableString(target);

        final String nonBreakingSpace = "\u00A0";
        final SpannableStringBuilder builder = target instanceof SpannableStringBuilder
                ? (SpannableStringBuilder) target
                : new SpannableStringBuilder(target);
        for (int i = target.length() - 1; i >= 1; i--) {
            builder.insert(i, nonBreakingSpace);
            builder.setSpan(new ScaleXSpan(kerning), i, i + 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }
}
