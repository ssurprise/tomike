package com.skx.tomike.cannon.ui.activity

import android.content.Intent
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.utils.ToastTool
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTER_GROUP
import com.skx.tomike.cannon.ROUTE_PATH_zxing


/**
 * 描述 : 扫描二维码（ZXing 库）
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/7/22 4:22 下午
 */
@Route(path = ROUTE_PATH_zxing, group = ROUTER_GROUP)
class ZXingActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_zxing
    }

    override fun initView() {
    }

    fun toScan(view: View) {
        ToastTool.showToast(this,"demo 同步有问题，导致部分代码丢失，该功能暂不可用")
//        val intent = Intent(this, com.skx.qrcode.activity.CaptureActivity::class.java)
//        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            100 -> {
                if (data != null && data.hasExtra("result")) {
                    Log.e(TAG, "result:" + data.getStringExtra("result"))
                }
            }
        }
    }
}