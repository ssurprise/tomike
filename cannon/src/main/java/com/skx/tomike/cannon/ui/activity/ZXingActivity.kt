package com.skx.tomike.cannon.ui.activity

import android.content.Intent
import android.util.Log
import android.view.View
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.tomike.cannon.R


/**
 * 描述 : 扫描二维码（ZXing 库）
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/7/22 4:22 下午
 */
class ZXingActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_zxing
    }

    override fun initView() {
    }

    fun toScan(view: View) {
        val intent = Intent(this, com.skx.qrcode.activity.CaptureActivity::class.java)
        startActivityForResult(intent, 100)
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