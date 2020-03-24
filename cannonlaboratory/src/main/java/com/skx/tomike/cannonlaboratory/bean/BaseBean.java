package com.skx.tomike.cannonlaboratory.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 描述 : 接口请求的基本数据
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/8/29
 */
public class BaseBean<T> {

    @SerializedName(value = "resultcode", alternate = {"resultCode"})
    public String resultCode;
    public int status;
    public T result;
    public String reason;
    public String timestamp;

    public String getResultCode() {
        return resultCode;
    }

    public T getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
