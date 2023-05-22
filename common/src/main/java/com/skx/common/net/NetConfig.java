package com.skx.common.net;



import okhttp3.OkHttpClient;

public class NetConfig {

    private String baseUrl;
    private OkHttpClient.Builder builder;
    private IResponseErrorMsg iResponseErrorMsg;

    public void setErrorResponse(IResponseErrorMsg errorResponse) {
        this.iResponseErrorMsg = errorResponse;
    }

    public NetConfig(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public void setBuilder(OkHttpClient.Builder builder) {
        this.builder = builder;
    }


    String getBaseUrl() {
        return baseUrl;
    }

    OkHttpClient.Builder getBuilder() {
        return builder;
    }

    IResponseErrorMsg getiResponseErrorMsg() {
        return iResponseErrorMsg;
    }

}
