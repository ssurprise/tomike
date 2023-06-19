package com.skx.common.net;

import com.google.gson.annotations.SerializedName;

/**
 * 描述 : 接口返回的基础数据结构
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/30 11:27 下午
 */
public class BaseResponse<T> {

    @SerializedName(value = "resultcode", alternate = {"resultCode"})
    public String resultCode;
    @SerializedName(value = "code", alternate = {"status"})
    public int code = -1;
    public T result;
    @SerializedName(value = "msg", alternate = {"reason"})
    public String msg;
    public String timestamp;

    public String getResultCode() {
        return resultCode;
    }

    public int getCode() {
        return code;
    }

    public T getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isOk() {
        return code == 0
                || code == 200
                || "0".equalsIgnoreCase(resultCode)
                || "200".equalsIgnoreCase(resultCode);
    }

}
