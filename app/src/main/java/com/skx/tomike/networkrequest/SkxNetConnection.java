package com.skx.tomike.networkrequest;

import com.skx.tomike.networkrequest.Interface.INetConnection;
import com.skx.tomike.networkrequest.Interface.ReqResultCallBack;
import com.skx.tomike.networkrequest.config.NetConnectionConfig;
import com.skx.tomike.networkrequest.javabean.JuheRespContent;
import com.skx.tomike.networkrequest.javabean.TrainAllInfo;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author shiguotao
 */
public class SkxNetConnection {

    public static <T> void getRequestData(String url, HashMap<String, String> paramsMap, ReqResultCallBack<T> resultCallBack) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(NetConnectionConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();
//        RetrofirHttpService apiStores = retrofit.create(RetrofirHttpService.class);
        INetConnection client = retrofit.create(INetConnection.class);
        if (paramsMap == null) {
            paramsMap = new HashMap<>();
        }
        paramsMap.put("key", "bbd86dbcdde6ff5f5bd9953b3e41eeda");

        Call<JuheRespContent> call = client.juheRequestByGet(url, paramsMap);
        call.enqueue(new Callback<JuheRespContent>() {
            @Override
            public void onResponse(Call<JuheRespContent> call, Response<JuheRespContent> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                int code = response.code();
                if (200 == code) {
                    JuheRespContent body = response.body();
//                    body.getResult()
                    TrainAllInfo trainAllInfo = (TrainAllInfo) body.getResult();
                    String message = response.message();
                }
            }

            @Override
            public void onFailure(Call<JuheRespContent> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
            }
        });
    }
}
