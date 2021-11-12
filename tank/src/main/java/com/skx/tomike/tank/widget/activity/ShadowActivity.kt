package com.skx.tomike.tank.widget.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTER_GROUP
import com.skx.tomike.tank.ROUTE_PATH_SHADOW

@Route(path = ROUTE_PATH_SHADOW, group = ROUTER_GROUP)
class ShadowActivity : SkxBaseActivity<BaseViewModel?>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("投影").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_shadow
    }

    override fun initView() {
        /*
        1. CardView 的投影颜色是默认的，并没有提供直接的修改方法。
        2. CardView 的投影是画在父View 上的。如果父View 直接包裹CardView 是不显示投影的

        3. 通过 layer-list 生成生成的drawable 也是不可能超过view的大小来渲染内容的

        4. 重写父View 的onDraw 方法通过 setShadowLayer 的方法

         */
    }
}