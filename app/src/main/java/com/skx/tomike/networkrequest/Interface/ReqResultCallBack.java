package com.skx.tomike.networkrequest.Interface;

/**
 * Created by shiguotao on 2017/6/20.
 * <p>
 * 网络请求结果回调
 */
public interface ReqResultCallBack<T> {
    void reqSuccess(T respContent);

    void reqFail();
}
