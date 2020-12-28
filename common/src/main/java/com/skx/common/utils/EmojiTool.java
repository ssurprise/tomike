package com.skx.common.utils;

import androidx.annotation.NonNull;

/**
 * Created by shiguotao on 2017/3/26.
 * <p>
 * emoji表情工具类
 */
public class EmojiTool {

    /**
     * 判断文本是否包含emoji表情
     *
     * @param targetText 目标文本
     * @return
     */
    public static boolean containsEmoji(CharSequence targetText) {
        int len = targetText.length();
        for (int i = 0; i < len; i++) {
            if (isEmojiCharacter(targetText.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9)
                || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

    public static CharSequence filterEmoji(CharSequence source) {
        if (!containsEmoji(source)) {
            return source;// 如果不包含，直接返回
        }

        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            } else {
            }
        }
        if (buf == null) {
            return "";
        } else {
            if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    /**
     * 获得替换emoji表情后的文本
     *
     * @param targetText  目标文本
     * @param replacement 替换字符串
     * @return 替换掉emoji后的文本
     */
    public static String getFilteredEmojiText(String targetText, @NonNull String replacement) {
        if (targetText == null) return null;
        return targetText.replaceAll("[^\\x00-\\xff]", replacement);
    }
}
