package com.skx.common.base.d;

import android.text.TextUtils;

import androidx.annotation.Nullable;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 拼接user-agent，公参
 */
public class RequestInterceptor implements Interceptor {
    public static final String PARAMS_IN_POST = "bodyParams";
    public static final String POST_BLOCK_URL = "postBlockUrl";
    private static String HEADER_USER_AGENT = "User-Agent";
    private final String DOMAIN_API = "Domain-Name";

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = buildUserAgent(chain.request());
        if (request.url().host().contains("xiaozhu"))
            if (request.method().equals("GET")) {
                request = addGetParams(request);
            } else if (request.method().equals("POST")) {
                request = addPostParams3(request);
            }
        return chain.proceed(request);
    }

    private Request addGetParams(Request request) {
        //添加公共参数
        HttpUrl httpUrl = request.url();
        HttpUrl.Builder builder = httpUrl.newBuilder();
        if (isNeedCommonParams(httpUrl, request.headers(DOMAIN_API))) {
            TreeMap<String, String> commonParams = buildCommonParams();
            if (!commonParams.isEmpty()) {
                for (Map.Entry<String, String> entry : commonParams.entrySet()) {
                    builder.setQueryParameter(entry.getKey(), entry.getValue());
                }
            }
        }
        httpUrl = builder.build();
        request = request.newBuilder().url(httpUrl).build();
        return request;
    }

    private Request addPostParams3(Request request) throws UnsupportedEncodingException {
        if (request.body() instanceof FormBody) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            FormBody formBody = (FormBody) request.body();
            HttpUrl httpUrl = request.url().newBuilder()
                    .build();
            HttpUrl.Builder builder = httpUrl.newBuilder();

            // 将body转成url的参数
            List<String> paramsInPostList = obtainParamsInList(request);
            boolean shieldThisPost = isShieldThisPost(request, getForcePostPath(request));
            for (int i = 0; i < formBody.size(); i++) {
                String key = formBody.encodedName(i);
                String value = URLDecoder.decode(formBody.encodedValue(i), "UTF-8");
                if (shieldThisPost || paramsInPostList.contains(key)) {
                    bodyBuilder.addEncoded(key, formBody.encodedValue(i));
                } else {
                    builder.setQueryParameter(key, value);
                }
            }
            if (isNeedCommonParams(httpUrl, request.headers(DOMAIN_API))) {
                TreeMap<String, String> commonParams = buildCommonParams();
                if (!commonParams.isEmpty()) {
                    for (Map.Entry<String, String> stringStringEntry : commonParams.entrySet()) {
                        builder.setQueryParameter(stringStringEntry.getKey(), stringStringEntry.getValue());
                    }
                }
            }
            httpUrl = builder.build();
            request = request.newBuilder().url(httpUrl).post(bodyBuilder.build()).build();
        } else if (request.body() instanceof MultipartBody) {
            HttpUrl.Builder urlBuilder = request.url().newBuilder();
            if (isNeedCommonParams(request.url(), request.headers(DOMAIN_API))) {
                TreeMap<String, String> commonParams = buildCommonParams();
                if (commonParams.size() > 0) {
                    for (Map.Entry<String, String> stringStringEntry : commonParams.entrySet()) {
                        urlBuilder.setQueryParameter(stringStringEntry.getKey(), stringStringEntry.getValue());
                    }
                }
            }
            request = request.newBuilder().url(urlBuilder.build()).build();
        } else if (request.body() != null) {
            try {
                // post 请求方式，Content-Type: application/json 情景下添加公参
                if (request.body().contentType() != null
                        && "application".equalsIgnoreCase(Objects.requireNonNull(request.body().contentType()).type())
                        && "json".equalsIgnoreCase(Objects.requireNonNull(request.body().contentType()).subtype())) {
                    HttpUrl.Builder urlBuilder = request.url().newBuilder();
                    if (isNeedCommonParams(request.url(), request.headers(DOMAIN_API))) {
                        TreeMap<String, String> commonParams = buildCommonParams();
                        if (!commonParams.isEmpty()) {
                            for (Map.Entry<String, String> stringStringEntry : commonParams.entrySet()) {
                                urlBuilder.setQueryParameter(stringStringEntry.getKey(), stringStringEntry.getValue());
                            }
                        }
                    }
                    request = request.newBuilder().url(urlBuilder.build()).build();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return request;
    }


    @SuppressWarnings("deprecation")
    private static TreeMap<String, String> buildCommonParams() {
        TreeMap<String, String> requestParams = new TreeMap<>();
//        String mUserId = ModuleServiceManager.getClassTarget(LoginModuleService.class).getUserId();
//        String mSessionId = User.getMy().sessionId;
//        if (!TextUtils.isEmpty(mUserId) && !mUserId.equals("0")) {
//            requestParams.put("userId", mUserId);
//        }
//        if (!requestParams.containsKey("sessId") && !TextUtils.isEmpty(mSessionId) && !mSessionId.equals("0")) {
//            requestParams.put("sessId", mSessionId);
//        }
//        if (!requestParams.containsKey("uniqueId")) {
//            requestParams.put("uniqueId", DeviceIdUtil.INSTANCE.getDeviceId());
//        }
//        requestParams.put("dispathChannel", AppUtils.getInstance().getAppChannel(AppConfig.CHANNEL_META_KEY));
//        requestParams.put("gatets", String.valueOf(AppRunTimeUtils.getInstance().getTimeWithX() / 1000));
//        String anonymousId = "";
//        if (!TextUtils.isEmpty(SensorsDataAPI.sharedInstance().getAnonymousId())) {
//            anonymousId = SensorsDataAPI.sharedInstance().getAnonymousId();
//        } else if (!TextUtils.isEmpty(DeviceIdUtil.INSTANCE.getDeviceId())) {
//            anonymousId = DeviceIdUtil.INSTANCE.getDeviceId();
//        }
//        requestParams.put("anonymous_id", anonymousId);
        return requestParams;
    }

    private Request buildUserAgent(Request request) {
        Map<String, String> headerParamsMap = headerParamsInjector() == null ? new HashMap<String, String>() : headerParamsInjector();
        Headers originHeaders = request.headers();
        Headers.Builder headerBuilder = originHeaders.newBuilder();
        for (Map.Entry<String, String> entry : headerParamsMap.entrySet()) {
            headerBuilder.set(entry.getKey(), entry.getValue());
        }
        Request.Builder builder = request.newBuilder()
                .headers(headerBuilder.build())
                .removeHeader("User-Agent")
                .removeHeader("Connection")
                .removeHeader("Charset")
                .removeHeader("Accept-Encoding")
                .addHeader(HEADER_USER_AGENT, getUserAgent())
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Charset", "UTF-8");

//        if (SensorsDataAPI.sharedInstance().getAnonymousId() != null) {
//            builder.addHeader("VisitUserId", SensorsDataAPI.sharedInstance().getAnonymousId());
//        }
//        if (EnvHttpRequestHeader.getInstance().isCheck()) {
//            builder.addHeader("x-virtual-env", EnvHttpRequestHeader.getInstance().getVirtualEnv());
//        }
        return builder.build();
    }

    private boolean isNeedCommonParams(HttpUrl url, List<String> apiHeader) {
        //新的验签匹配规则（HttpSignedUtils.isSignUrl(url.host())）匹配不全，还需要保留老的匹配规则条件 HttpSignedUtils.isXZSignPath(url.encodedPath())
        // || HttpSignedUtils.isNewMicroServiceUrl(apiHeader)
//        return (HttpSignedUtils.isXZSignPath(url.encodedPath()) || XZPreInformation.getInstance().isShortPath(apiHeader) || HttpSignedUtils.isSignUrl(url.host()))
//                // verifymobile 域名不需要拼接公参
//                && !apiHeader.contains(GlobalConstants.BIZ_HOST_VERIFYMOBILE);
        return false;
    }

    private boolean isShieldThisPost(Request request, String userForcePostPath) {
        if (TextUtils.isEmpty(userForcePostPath)) return false;
        return request.url().toString().contains(userForcePostPath);
    }

    @Nullable
    private String getForcePostPath(Request request) {
        List<String> list = request.headers(POST_BLOCK_URL);
        return list.size() > 0 ? list.get(0) : null;
    }

    @NotNull
    private List<String> obtainParamsInList(Request request) {
        return request.headers(PARAMS_IN_POST);
    }

    public String getUserAgent() {
//        return HttpSignedUtils.getUserAgent() + NetworkUtils.getAPNType(AppContext.getInstance().getAppContext());
        return "";
    }

    public Map<String, String> headerParamsInjector() {
//        return ModuleServiceManager.getClassTarget(LoginModuleService.class).getBaseRequestHeader();
        return null;
    }
}
