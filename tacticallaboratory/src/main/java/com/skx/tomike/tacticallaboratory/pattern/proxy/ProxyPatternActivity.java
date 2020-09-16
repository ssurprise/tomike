package com.skx.tomike.tacticallaboratory.pattern.proxy;

import android.view.View;
import android.widget.RadioGroup;

import com.skx.tomike.tacticallaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.ToastTool;

/**
 * 描述 : 代理模式
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/14 9:22 AM
 */
public class ProxyPatternActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    private ICarFactory iCarFactory;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("代理模式").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pattern_proxy;
    }

    @Override
    protected void initView() {
        RadioGroup mCbCar = findViewById(R.id.cb_proxy_cars);
        mCbCar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cb_proxy_audi) {
                    iCarFactory = new AudiCareFactory();
                } else if (checkedId == R.id.cb_proxy_benz) {
                    iCarFactory = new BenzCareFactory();
                }
            }
        });

        findViewById(R.id.tv_proxy_staticProxy).setOnClickListener(this);
        findViewById(R.id.tv_proxy_dynamicProxy).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (iCarFactory == null) {
            ToastTool.showToast(mActivity, "您要买什么车？");
            return;
        }
        if (v.getId() == R.id.tv_proxy_staticProxy) {
            staticProxy();

        } else if (v.getId() == R.id.tv_proxy_dynamicProxy) {
            dynamicProxy();
        }
    }

    /**
     * 静态代理示例
     */
    private void staticProxy() {
        if (iCarFactory == null) {
            return;
        }
        CarFactoryProxy carFactoryProxy = new CarFactoryProxy();
        carFactoryProxy.setProxy(iCarFactory);
        carFactoryProxy.saleCar();
    }


    /**
     * 动态代理示例
     */
    private void dynamicProxy() {
        if (iCarFactory == null) {
            return;
        }
        CarFactoryProxyPro factoryProxyPro = new CarFactoryProxyPro();
        factoryProxyPro.setProxy(iCarFactory);

        ICarFactory proxy = (ICarFactory) factoryProxyPro.getProxy();
        proxy.saleCar();
    }
}
