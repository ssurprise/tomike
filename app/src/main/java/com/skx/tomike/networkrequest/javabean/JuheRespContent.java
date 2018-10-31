package com.skx.tomike.networkrequest.javabean;

/**
 * Created by shiguotao on 2016/8/29.
 * <p>
 * 聚合网免费数据 网络请求基础接收类
 */
public class JuheRespContent<T> {

    private String resultcode;
    private String reason;
    private T result;
    private String error_code;

    public String getResultcode() {
        return resultcode;
    }

    public T getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public String getError_code() {
        return error_code;
    }
}
