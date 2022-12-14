package com.skx.tomike.cannon.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.tomike.cannon.R;
import com.skx.tomike.cannon.bean.WeatherMini;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_OKHTTP;

@Route(path = ROUTE_PATH_OKHTTP)
public class OkHttpActivity extends AppCompatActivity {

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

    private void initializeView() {
        setContentView(R.layout.activity_okhttp);
        inputBox = findViewById(R.id.okhttp_inputBox);
        wendu_value = findViewById(R.id.okhttp_wendu_value);
        ganmao_value = findViewById(R.id.okhttp_ganmao_value);
        fengli_value = findViewById(R.id.okhttp_fengli_value);
        fengxiang_value = findViewById(R.id.okhttp_fengxiang_value);
    }

    private void installListener() {
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

    private void refreshView() {
//        if (weatherMini != null) {
//            wendu_value.setText(weatherMini.getWendu());
//            ganmao_value.setText(weatherMini.getGanmao());
//        }
    }

    public void onOkHttpClickByGet(View view) {
        wendu_value.setText("");
        ganmao_value.setText("");


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .cache(new Cache()) // 缓存
//                .cookieJar(new CookieJar() { // cookie 缓存
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        return null;
//                    }
//                })
//                .proxy(// 设置代理
//                        new Proxy(Proxy.Type.HTTP,
//                                new InetSocketAddress("host-name", 80)
//                        )
//                )
//                .addInterceptor(new Interceptor() { // 添加拦截求
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return null;
//                    }
//                })
                .build();

        Request request = new Request.Builder()
                .url("http://v.juhe.cn/")
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                response.body().

            }
        });

        /*
        //  异步请求
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
         */

    }

    public void onOkHttpClickByPost(View view) throws IOException {
        wendu_value.setText("");
        ganmao_value.setText("");

    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }
}
