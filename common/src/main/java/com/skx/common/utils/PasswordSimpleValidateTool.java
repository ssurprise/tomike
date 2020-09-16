package com.skx.common.utils;

/**
 * Created by shiguotao on 2016/9/27.
 * <p>
 * 简单密码过滤
 */
public class PasswordSimpleValidateTool {

    /**
     * @param newPwd
     * @return 0: 符合 1：不符合
     */
    public static int validatePassword(String newPwd) {
        if ("01234567890".indexOf(newPwd) > -1 || "09876543210".indexOf(newPwd) > -1) {
            return 1;
        }
        int half = newPwd.length() / 2;
        return validateString(newPwd.substring(0, half), newPwd.substring(half, newPwd.length()));

    }

    /**
     * 两个字符串是否过于相似
     *
     * @param str1
     * @param str2
     * @return
     */
    private static int validateString(String str1, String str2) {
        int distance = 0;
        if (str1.length() == str2.length()) {
            distance = getDistance(str1, str2);
            double weight = (double) distance / str1.length();
            if (weight < 0.5) {
                return 1;
            } else {
                return 0;
            }
        }
        String temp = null;
        if (str1.length() > str2.length()) {
            temp = str1;
            str1 = str2;
            str2 = temp;
        }
        for (int i = 0; i < str2.length() - str1.length(); i++) {
            distance = getDistance(str1, str2.substring(i, i + str1.length()));
            double weight = (double) distance / str1.length();
            if (weight < 0.5) {
                return 1;
            }
        }
        return 0;
    }

    public static int getDistance(String str1, String str2) {
        int distance;
        if (str1.length() != str2.length()) {
            distance = -1;
        } else {
            distance = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }
}
