package com.skx.tomike.activity.function;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;
import com.skx.tomike.networkrequest.Interface.ReqResultCallBack;
import com.skx.tomike.networkrequest.SkxNetConnection;
import com.skx.tomike.javabean.WeatherMini;
import com.skx.tomike.networkrequest.javabean.TrainAllInfo;
import com.skx.tomikecommonlibrary.utils.ToastTool;

import java.util.HashMap;

public class RetrofitActivity extends SkxBaseActivity {

    private EditText inputBox;
    private TextView wendu_value;
    private TextView ganmao_value;
    private TextView fengli_value;
    private TextView fengxiang_value;
    private String targetCity;
    private WeatherMini weatherMini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        installListener();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        setContentView(R.layout.activity_retrofit);
        inputBox = (EditText) findViewById(R.id.retrofit_inputBox);
        wendu_value = (TextView) findViewById(R.id.wendu_value);
        ganmao_value = (TextView) findViewById(R.id.ganmao_value);
        fengli_value = (TextView) findViewById(R.id.fengli_value);
        fengxiang_value = (TextView) findViewById(R.id.fengxiang_value);
    }

    @Override
    public void installListener() {
        super.installListener();
        inputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                targetCity = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void refreshView() {
        super.refreshView();
        if (weatherMini != null) {
            wendu_value.setText(weatherMini.getWendu());
            ganmao_value.setText(weatherMini.getGanmao());
        }

    }

    public void onRetrofitClick(View view) {
        targetCity = "K818";
        if (TextUtils.isEmpty(targetCity)) {
            ToastTool.showToast(this, "没有找到搜索城市");
            return;
        }
        wendu_value.setText("");
        ganmao_value.setText("");

//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://wthrcdn.etouch.cn/").client(new OkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build();
//        INetConnection weather = retrofit.create(INetConnection.class);
//        Call<RespContent<WeatherMini>> resp = weather.getWeatherFromNetByGet(targetCity);


        HashMap<String, String> paramsHm = new HashMap<>();
        paramsHm.put("name", targetCity);
        paramsHm.put("dtype", "json");
        SkxNetConnection.getRequestData("train/s", paramsHm, new ReqResultCallBack<TrainAllInfo>() {
            @Override
            public void reqSuccess(TrainAllInfo respContent) {
                ToastTool.showToast(RetrofitActivity.this, "请求成功");
            }

            @Override
            public void reqFail() {
                ToastTool.showToast(RetrofitActivity.this, "请求失败");
            }
        });

//
//
//        /** 异步请求 */
//        resp.enqueue(new Callback<RespContent<WeatherMini>>() {
//            @Override
//            public void onResponse(Call<RespContent<WeatherMini>> call, Response<RespContent<WeatherMini>> response) {
//                RespContent<WeatherMini> body = response.body();
//                weatherMini = body.getT();
//                if (1000 == body.getResultcode()) {
//                    refreshView();
//                } else {
//                    ToastTool.showToast(RetrofitActivity.this, "未找到与输入城市相匹配的结果");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RespContent<WeatherMini>> call, Throwable t) {
//                ToastTool.showToast(RetrofitActivity.this, "没有找到搜索城市");
//            }
//        });

        /**
         *
         //同步请求(阻塞当前线程),需要开启子线程来执行
         try {
         Response<RespContent<WeatherMini>> execute = resp.execute();
         } catch (IOException e) {
         e.printStackTrace();
         }
         */
    }
}
