package com.skx.tomike.tank.widget.activity

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.snackbar.Snackbar
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTER_GROUP
import com.skx.tomike.tank.ROUTE_PATH_SNACKBAR

@Route(path = ROUTE_PATH_SNACKBAR, group = ROUTER_GROUP)
class SnackBarActivity : SkxBaseActivity<BaseViewModel>(), View.OnClickListener {

    private var mTvLabel: TextView? = null

    override fun onClick(v: View) {
        if (v.id == R.id.snackbar_btn) { // 父容器 ,提示信息，持续时间
            // setAction() 用于给SnackBar设定一个Action， 右侧显示的东西，点击之后会回调OnclickListener中的Onclick方法
            Snackbar.make(v, "出现了出现了", Snackbar.LENGTH_LONG)
                .setAction("真棒") { mTvLabel!!.text = "这就对了嘛！" }.setCallback(null).show()
        }
    }

    override fun initParams() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_snackbar
    }

    override fun initView() {
        mTvLabel = findViewById(R.id.snackbar_label)
        val btn = findViewById<Button>(R.id.snackbar_btn)
        btn?.setOnClickListener(this)
    }
}