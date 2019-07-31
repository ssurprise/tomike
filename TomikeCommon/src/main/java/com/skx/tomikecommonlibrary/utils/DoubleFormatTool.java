package com.skx.tomikecommonlibrary.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * Created by skx
 * <p/>
 * Double 数据类型处理工具类。目前基本包括“字符串转double 类型”，“四舍五入”，“保留两位小数”，“double类型的加、减、乘、除运算”
 */
public class DoubleFormatTool {

    /**
     * 字符串转Double
     *
     * @param doubleString double类型的字符串
     * @return
     */
    public static double strToDouble(String doubleString) {
        try {
            return Double.parseDouble(doubleString);
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * 得到一个Double 类型的数 四舍五入后保留小数点后两位.
     * <p/>
     * 这里调用的是  getRoundHalfUpDouble(String doubleString) 方法，
     * 而不是用  BigDecimal b = new BigDecimal(d);return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 这样的方式来。
     * 主要原因是 BigDecimal(double) 的构造参数在使用的时候会存在精度不准确的情况,而在java中浮点运算本身就是不精确的，是用IEEE标准来表示的，当然这并不是所有的浮点数都不正确，而是有一部分不准确。
     * <p/>
     * API 中是这样解释的。public BigDecimal(double val)
     * 将 double 转换为 BigDecimal，后者是 double 的二进制浮点值准确的十进制表示形式。返回的 BigDecimal 的标度是使 (10scale × val) 为整数的最小值。
     * <p/>
     * 注：
     * 1.此构造方法的结果有一定的不可预知性。有人可能认为在 Java 中写入 new BigDecimal(0.1) 所创建的 BigDecimal 正好等于 0.1（非标度值 1，其标度为 1），但是它实际上等于 0.1000000000000000055511151231257827021181583404541015625。这是因为 0.1 无法准确地表示为 double（或者说对于该情况，不能表示为任何有限长度的二进制小数）。这样，传入 到构造方法的值不会正好等于 0.1（虽然表面上等于该值）。
     * 2.另一方面，String 构造方法是完全可预知的：写入 new BigDecimal("0.1") 将创建一个 BigDecimal，它正好 等于预期的 0.1。因此，比较而言，通常建议优先使用 String 构造方法。3.
     * 当 double 必须用作 BigDecimal 的源时，请注意，此构造方法提供了一个准确转换；它不提供与以下操作相同的结果：先使用 Double.toString(double) 方法，然后使用 BigDecimal(String) 构造方法，将 double 转换为 String。要获取该结果，请使用 static valueOf(double) 方法。
     * <p/>
     * eg:  1.115  用BigDecimal(double) 的构造方法四舍五入，保留2位小数得到的值位 1.11  而BigDecimal(String) 的构造方法四舍五入，保留2位小数得到的值位 1.12
     * 而且官方的API 也是推荐使用BigDecimal（String）的构造函数。
     *
     * @param d double类型的字符串
     * @return 四舍五入后保留2位小数的值
     */
    public static double getRoundHalfUpDouble(double d) {
        return getRoundHalfUpDouble(String.valueOf(d));
    }

    /**
     * 对于一个double 型的字符串进行四舍五入和保留两位小数处理，如果最后一位小数位为0，则默认不显示
     *
     * @param doubleString double类型的字符串
     * @return 四舍五入后保留2位小数的值
     */
    public static double getRoundHalfUpDouble(String doubleString) {
        try {
            BigDecimal b = new BigDecimal(doubleString);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * 对于一个double 型的字符串进行四舍五入和保留两位小数处理，如果最后一位小数位为为0，则仍然显示。
     *
     * @param doubleString double类型的字符串
     * @return
     */
    public static String getRoundHalfUpDoubleStr(String doubleString) {
        try {
            double tempDoubleString = getRoundHalfUpDouble(doubleString);
            return String.format("%.2f", tempDoubleString);
        } catch (Exception e) {
            return doubleString;
        }
    }

    /**
     * 精确的double类型的字符串  加法运算
     *
     * @param doubleStr1 被加数
     * @param doubleStr2 加数
     * @return 两个参数的和
     */
    public static double add(String doubleStr1, String doubleStr2) {
        if (TextUtils.isEmpty(doubleStr1) || TextUtils.isEmpty(doubleStr2)) {
            return 0;
        }
        try {
            BigDecimal b1 = new BigDecimal(doubleStr1);
            BigDecimal b2 = new BigDecimal(doubleStr2);
            return b1.add(b2).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 精确的double 加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {

        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double subtract(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double divide(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
