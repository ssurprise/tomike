package com.skx.tomike.cannon.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R

/**
 * 描述 : Lifecycle demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/10 9:15 AM
 */
class LifecycleActivity2 : SkxBaseActivity<BaseViewModel?>(), View.OnClickListener {

    private val tv: TextView by lazy {
        findViewById(R.id.tv_lifecycle_content)
    }

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("Activity生命周期 demo").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_lifecycle_test2
    }

    override fun initView() {
        findViewById<Button>(R.id.btn_lifecycle_showDialog).setOnClickListener(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv.append("onCreate \n")
        Log.e("Activity_Lifecycle", "Activity2:create")
    }

    override fun onStart() {
        super.onStart()
        tv.append("onStart \n")
        Log.e("Activity_Lifecycle", "Activity2:start")
    }

    override fun onResume() {
        super.onResume()
        tv.append("onResume \n")
        Log.e("Activity_Lifecycle", "Activity2:resume")
    }

    override fun onPause() {
        super.onPause()
        tv.append("onPause \n")
        Log.e("Activity_Lifecycle", "Activity2:pause")
    }

    override fun onStop() {
        super.onStop()
        tv.append("onStop \n")
        Log.e("Activity_Lifecycle", "Activity2:stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        tv.append("onDestroy \n")
        Log.e("Activity_Lifecycle", "Activity2:destroy")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_lifecycle_showDialog -> {
                onPopDialog()
            }
            R.id.btn_lifecycle_openNewActivity -> {
//                onOpenNewActivity()
            }
        }
    }

    private fun onPopDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("My AlertDialog")
        builder.setMessage("弹出dialog ，观察activity 声明周期的变化")
        builder.setCancelable(false)
        builder.setPositiveButton("关闭") { dialog, _ ->
            dialog.dismiss()
            Log.e("Lifecycle", "-> 关闭dialog")
        }
        Log.e("Lifecycle", "-> 弹出dialog")
        builder.show()
    }

}