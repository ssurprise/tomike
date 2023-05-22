package com.skx.common.net;

/**
 * 如果自定义的异常类  实现此接口
 */
public interface IResponseErrorMsg {
    void getErrorMsg(int errorCode);

}
