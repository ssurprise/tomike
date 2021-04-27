package com.skx.tomike.bomber.coroutine

import android.os.Bundle
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.tomike.bomber.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 描述 : 协程demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/1/7 3:27 PM
 */
class CoroutineActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {
    }

    override fun getLayoutId(): Int = R.layout.activity_coroutine

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            delay(1000)
        }
    }
}