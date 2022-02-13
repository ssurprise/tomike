package com.skx.tomike.cannon.ui.activity;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_RETROFIT;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.ToastTool;
import com.skx.tomike.cannon.R;
import com.skx.tomike.cannon.bean.WeatherMini;
import com.skx.tomike.cannon.viewmodel.RetrofitViewModel;

/**
 * 描述 : Retrofit demo。模拟请求城市天气数据
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/6/20
 */
@Route(path = ROUTE_PATH_RETROFIT)
public class RetrofitActivity extends SkxBaseActivity<RetrofitViewModel> {

    private EditText mEtCity;
    private TextView mTvTemperature;
    private TextView mTvHumidity;
    private TextView mTvWindPower;
    private TextView mTvWindDirection;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("Retrofit demo").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    protected void subscribeEvent() {
        mViewModel.getWeatherLiveData().observe(this, weatherMiniBaseBean -> {
            if (weatherMiniBaseBean == null) return;
            refreshView(weatherMiniBaseBean.getResult());
        });
    }

    @Override
    protected void initView() {
        mEtCity = findViewById(R.id.et_retrofitTest_city);
        mTvTemperature = findViewById(R.id.tv_retrofitTest_temperature_value);
        mTvHumidity = findViewById(R.id.tv_retrofitTest_humidity_value);
        mTvWindDirection = findViewById(R.id.tv_retrofit_windDirection_value);
        mTvWindPower = findViewById(R.id.tv_retrofit_windPower_value);
    }

    private void refreshView(WeatherMini weatherMini) {
        if (weatherMini != null) {
            mTvTemperature.setText(weatherMini.sk.temperature);
            mTvHumidity.setText(weatherMini.sk.humidity);
            mTvWindPower.setText(weatherMini.sk.windPower);
            mTvWindDirection.setText(weatherMini.sk.windDirection);
        }
    }

    public void onRetrofitClick(View view) {
        String targetCity = mEtCity.getText().toString();
        if (TextUtils.isEmpty(targetCity)) {
            ToastTool.showToast(this, "没有找到搜索城市");
            return;
        }
        mTvTemperature.setText("");
        mTvHumidity.setText("");

        mViewModel.queryCityWeather(targetCity);
    }

        /*
    Request 学习笔记

    @GET, @POST, @PUT, @DELETE, @PATCH or @HEAD

    1.把请求链接分成base url 和相对url，而不是完整的url，好处是：Retrofit只需要一次请求基本URL，而且要修改基础url的话只需要修改一处，而不是每个方法都修改

    2.必须指定返回类型，如果明确接受类型可以使用Call <指定类型>，Retrofit将自动映射，您将不必进行任何手动解析。如果您想要原始响应，可以使用ResponseBody替代像UserInfo这样的特定类。
    如果您根本不关心服务器的响应，可以使用Void。 在所有这些情况下，您必须将其包装成一个类型的Retrofit Call <>类。

    3.  @Body: send Java objects as request body. 发送一个java对象作为请求体
        @Url:使用动态url
        @Field: send data as form-urlencoded.发送表单数据


     动态URL(dynamic URLs)
     4.动态url： 保留@GET 注解，但是不添加的endpoint url，同时添加 @Url 注解到方法中

     5.关于动态url 和 base url 的冲突：
        5.1
        eg: Retrofit retrofit = Retrofit.Builder().baseUrl("https://your.api.url/");.build();
            UserService service = retrofit.create(UserService.class);
            service.profilePicture("https://s3.amazon.com/profile-picture/path");

            request url results in: https://s3.amazon.com/profile-picture/path
        因为设置了完全不同的 host，包括scheme （https://s3.amazon.com vs. https：//your.api.url），所以 OkHttp 的 HttpUrl 会将其解析为动态的。

        5.2
        eg: Retrofit retrofit = Retrofit.Builder().baseUrl("https://your.api.url/");.build();
            UserService service = retrofit.create(UserService.class);
            service.profilePicture("profile-picture/path");

            request url results in: https://your.api.url/profile-picture/path
        因为动态定义的 endpoint url 没有指定 host，包括scheme，所以HttpUrl 会把定义的endpoint url 拼接到base url 上组成最终请求的url

        5.3
        eg: Retrofit retrofit = Retrofit.Builder().baseUrl("https://your.api.url/v2/");.build();
            UserService service = retrofit.create(UserService.class);
            service.profilePicture("/profile-picture/path");

            request url results in: https://your.api.url/profile-picture/path
        这是因为以"/"开始的endpoint url 只会追加到 base url的 host url 上。当使用"/" 作为endpoint url的开头时，host url 后面的内容都将被省略。解决方案：endpoint url 不以"/" 开始。
     */

    /*

    // 设置请求方法为get，相对路径为注解内内容，其中{test}会被@Path注解指定内容替换   @Query用于指定参数
    @GET("/users/{user}/repos")
    Call<RespContent> reposForUser(
            @Path("user") String user
    );
     */
}
