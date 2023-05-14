package com.skx.tomike.cannon.ui.activity

import android.view.Gravity
import android.widget.Button
import android.widget.RadioGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_DIALOG
import com.skx.tomike.cannon.ui.dialog.SkxDialogFragment

@Route(path = ROUTE_PATH_DIALOG)
class DialogActivity : SkxBaseActivity<BaseViewModel<*>>() {

    private var gravity = Gravity.CENTER

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("Dialog 多样化显示").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_dialog
    }

    override fun initView() {
        findViewById<RadioGroup>(R.id.rg_dialog_pos).setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rbtn_dialog_center -> {
                    gravity = Gravity.CENTER
                }
                R.id.rbtn_dialog_bottom -> {
                    gravity = Gravity.BOTTOM
                }
                else -> {

                }
            }
        }
        findViewById<Button>(R.id.btn_dialog_show).setOnClickListener {
            val newInstance = SkxDialogFragment.newInstance(
                    "看看",
                    "一把钥匙是几个钥匙？那你猜猜，你说几把就几把",
                    "取消",
                    "确定",
                    gravity
            )
            val mDialog = newInstance.dialog
            if (mDialog == null || !mDialog.isShowing) {
                newInstance.show(supportFragmentManager, "SkxDialogFragment")
            }
        }
    }
}