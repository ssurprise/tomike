package com.skx.tomike.bomber.basics;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.bomber.R;

import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_GSON;

/**
 * 描述 : json 解析
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/12/25 11:05 AM
 */
@Route(path = ROUTE_PATH_GSON)
public class GsonParseActivity extends SkxBaseActivity<BaseViewModel<?>> {

    private static final String URL_ENCODE = "UTF-8";

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("json 解析").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_url_encode;
    }

    @Override
    protected void initView() {
        Gson gson = new Gson();
    }
}
