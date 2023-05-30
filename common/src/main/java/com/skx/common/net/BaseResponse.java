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
    public int status;
    public T result;
    @SerializedName(value = "reason", alternate = {"msg"})
    public String reason;
    public String timestamp;

    public String getResultCode() {
        return resultCode;
    }

    public int getStatus() {
        return status;
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

    public boolean isOk() {
        return status == 0
                || status == 200
                || "0".equalsIgnoreCase(resultCode)
                || "200".equalsIgnoreCase(resultCode);
    }

}
