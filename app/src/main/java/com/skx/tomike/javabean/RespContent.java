package com.skx.tomike.javabean;

/**
 * Created by shiguotao on 2016/8/29.
 */
public class RespContent<T> {

    private int status;
    private T data;
    private String desc;
    private String error;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
