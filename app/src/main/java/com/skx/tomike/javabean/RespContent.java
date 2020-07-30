package com.skx.tomike.javabean;

/**
 * Created by shiguotao on 2016/8/29.
 * <p>
 * 网络请求基础接收类
 */
public class RespContent<T> {

    private int status;
    private T data;
    private String errorMsg;
    private String timeStamp;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getT() {
        return data;
    }

    public void setT(T t) {
        this.data = t;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
