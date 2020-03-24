package com.skx.tomike.cannonlaboratory.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.BaseBean;
import com.skx.tomike.cannonlaboratory.bean.WeatherMini;
import com.skx.tomike.cannonlaboratory.viewmodel.RetrofitViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;
import com.skx.tomikecommonlibrary.utils.ToastTool;

/**
 * 描述 : Retrofit demo。模拟请求城市天气数据
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/9/5
 */
public class RetrofitActivity extends SkxBaseActivity<RetrofitViewModel> {

    private EditText mEtCity;
    private TextView mTvTemperature;
    private TextView mTvHumidity;
    private TextView mTvWindPower;
    private TextView mTvWindDirection;
    private String targetCity;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("Retrofit demo").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    protected void subscribeEvent() {
        mViewModel.getWeatherLiveData().observe(this, new Observer<BaseBean<WeatherMini>>() {
            @Override
            public void onChanged(BaseBean<WeatherMini> weatherMiniBaseBean) {
                if (weatherMiniBaseBean == null) return;
                refreshView(weatherMiniBaseBean.getResult());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {
        mEtCity = findViewById(R.id.et_retrofitTest_city);
        mTvTemperature = findViewById(R.id.tv_retrofitTest_temperature_value);
        mTvHumidity = findViewById(R.id.tv_retrofitTest_humidity_value);
        mTvWindDirection = findViewById(R.id.tv_retrofit_windDirection_value);
        mTvWindPower = findViewById(R.id.tv_retrofit_windPower_value);
    }

    private void initListener() {
        mEtCity.addTextChangedListener(new TextWatcher() {
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

    private void refreshView(WeatherMini weatherMini) {
        if (weatherMini != null) {
            mTvTemperature.setText(weatherMini.sk.temperature);
            mTvHumidity.setText(weatherMini.sk.humidity);
            mTvWindPower.setText(weatherMini.sk.windPower);
            mTvWindDirection.setText(weatherMini.sk.windDirection);
        }
    }

    public void onRetrofitClick(View view) {
        if (TextUtils.isEmpty(targetCity)) {
            ToastTool.showToast(this, "没有找到搜索城市");
            return;
        }
        mTvTemperature.setText("");
        mTvHumidity.setText("");

        mViewModel.queryCityWeather(targetCity);
    }
}
