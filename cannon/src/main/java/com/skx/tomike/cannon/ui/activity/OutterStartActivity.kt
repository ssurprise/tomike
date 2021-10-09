package com.skx.tomike.cannon.ui.activity

import android.content.Intent
import android.net.Uri
import android.widget.TextView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.utils.ToastTool
import com.skx.tomike.cannon.R

class OutterStartActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("打开外部APP").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_outter_start
    }

    override fun initView() {
        findViewById<TextView>(R.id.tv_outterStart_to).setOnClickListener {
            try {
                val intent = Intent()
                intent.data = Uri.parse("openapp.xzdz://xiaozhu/openpage?page=index")
                startActivity(intent)
            } catch (e: Exception) {
                ToastTool.showToast(this, e.message)
                e.printStackTrace()
            }
        }
    }
}