package com.skx.tomike.tank.animation.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R

/**
 * 共享元素1
 */
class ShareElementActivity : SkxBaseActivity<BaseViewModel>(), View.OnClickListener {

    override fun initParams() {}

    override fun getLayoutId(): Int {
        return R.layout.activity_share_element
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("共享元素 - 界面1").create()
    }

    override fun initView() {
        findViewById<ImageView>(R.id.iv_shareElement_imageView).setOnClickListener(this)
        findViewById<ImageView>(R.id.iv_shareElement_imageView2).setOnClickListener(this)
        findViewById<ImageView>(R.id.iv_shareElement_imageView3).setOnClickListener(this)
        findViewById<ImageView>(R.id.iv_shareElement_imageView4).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_shareElement_defView).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(mActivity, ShareElement2Activity::class.java)
        // create the transition animation - the images in the layouts
        // of both activities are defined with android:transitionName="robot"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions.makeSceneTransitionAnimation(
                    this@ShareElementActivity,
                    v,
                    "shareElement")
            this@ShareElementActivity.startActivity(intent, options.toBundle())
        } else {
            this@ShareElementActivity.startActivity(intent)
        }
    }
    /*
    1. 设置共享元素（View）有相同的 android:transitionName 属性。但是在API 30中界面A中并
       没有设置 android:transitionName 属性，但结果却同样实现了共享元素的跳转功能，具体原因未知！！
    2. 配置跳转参数
       android.app.ActivityOptions.makeSceneTransitionAnimation(android.app.Activity,
            android.view.View, java.lang.String)
       android.app.Activity.startActivity(android.content.Intent, android.os.Bundle)
     */
}