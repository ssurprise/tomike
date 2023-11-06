package com.skx.tomike.cannon.ui.activity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_DOWNLOAD

/**
 * 描述 : 下载管理 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/30 4:34 PM
 */
@Route(path = ROUTE_PATH_DOWNLOAD)
class DownloadActivity : SkxBaseActivity<BaseViewModel<*>?>(), View.OnClickListener {

    override fun initParams() {}
    override fun configHeaderTitle(): TitleConfig? {
        return TitleConfig.Builder().setTitleText("下载管理demo").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_download_manage
    }

    override fun initView() {
        findViewById<View>(R.id.tv_download_settingBtn).setOnClickListener(this)
        findViewById<View>(R.id.tv_download_addTestDataBtn).setOnClickListener(this)
        val rv_download_tasks = findViewById<RecyclerView>(R.id.rv_download_tasks)
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.tv_download_settingBtn) {
        } else if (id == R.id.tv_download_addTestDataBtn) {
        }
    }
}