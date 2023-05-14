package com.skx.tomike.cannon.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_LIVEDATA

/**
 * 描述 : Lifecycle demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/10 9:15 AM
 */
@Route(path = ROUTE_PATH_LIVEDATA)
class LivedataActivity : SkxBaseActivity<BaseViewModel<*>>(), View.OnClickListener {

    private val source: MutableLiveData<Int> = MutableLiveData()

    private val tv: TextView by lazy {
        findViewById(R.id.tv_lifecycle_content)
    }


    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("Lifecycle demo").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_livedata_test
    }

    override fun initView() {
        findViewById<Button>(R.id.btn_livedata_showDialog).setOnClickListener(this)
        findViewById<Button>(R.id.btn_livedata_set).setOnClickListener(this)
        findViewById<Button>(R.id.btn_livedata_post).setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        source.observe(this) { abc ->

        }
    }

    override fun onResume() {
        super.onResume()
//        tv.append("在 onResume() 方法里添加另外一个 Observer \n")
//        lifecycle.addObserver(Re2(tv))
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Activity_Lifecycle", "onRestart()")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_livedata_showDialog -> {
            }
            R.id.btn_livedata_set -> {
                source.value = 1
            }
            R.id.btn_livedata_post -> {
            }
        }
    }
}