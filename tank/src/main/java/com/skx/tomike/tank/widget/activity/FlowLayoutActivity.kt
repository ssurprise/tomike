package com.skx.tomike.tank.widget.activity

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.utils.SkxDrawableUtil
import com.skx.common.utils.dip2px
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_FLOW_LAYOUT
import com.skx.tomike.tank.widget.view.FlowLayout
import java.util.*

/**
 * 描述 : 流式布局
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/24 8:05 PM
 */
@Route(path = ROUTE_PATH_FLOW_LAYOUT)
class FlowLayoutActivity : SkxBaseActivity<BaseViewModel?>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("流式布局").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_flow_layout
    }

    override fun initView() {
        val flowLayout = findViewById<FlowLayout>(R.id.fl_flowLayoutTest_content)
        flowLayout.setAdapter(FlowAdapter())
    }


    class FlowAdapter : BaseAdapter() {

        private val mTagArray = ArrayList<String>()

        init {
            mTagArray.add("钢铁侠")
            mTagArray.add("雷神")
            mTagArray.add("绿巨人浩克")
            mTagArray.add("美国队长")
            mTagArray.add("黑寡妇")
            mTagArray.add("黑豹")
            mTagArray.add("蜘蛛侠")
            mTagArray.add("蚁人")
            mTagArray.add("奥创")
            mTagArray.add("奇异博士")
            mTagArray.add("银河护卫队")
            mTagArray.add("惊奇队长")
            mTagArray.add("死侍")
            mTagArray.add("钢铁侠钢铁侠钢铁侠钢铁侠钢铁侠钢铁侠钢铁侠钢铁侠")
            mTagArray.add("雷神")
            mTagArray.add("绿巨人浩克")
            mTagArray.add("美国队长")
            mTagArray.add("黑寡妇")
            mTagArray.add("黑豹")
            mTagArray.add("蜘蛛侠")
            mTagArray.add("蚁人")
            mTagArray.add("奥创")
            mTagArray.add("奇异博士")
            mTagArray.add("银河护卫队")
            mTagArray.add("惊奇队长")
            mTagArray.add("死侍")
        }

        override fun getCount(): Int {
            return mTagArray.size
        }

        override fun getItem(position: Int): Any {
            return mTagArray[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            if (parent == null) return null
            return TextView(parent.context).apply {
                layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setPadding(dip2px(parent.context, 12f),
                        dip2px(parent.context, 3f),
                        dip2px(parent.context, 12f),
                        dip2px(parent.context, 4f)
                )
                textSize = 12f
                gravity = Gravity.CENTER
                text = mTagArray[position]
                setTextColor(ContextCompat.getColor(parent.context, R.color.skx_212121))
                setItemBackground(parent.context, this)
                setOnClickListener {
                    isSelected = !isSelected
                    setItemBackground(parent.context, this)
                }
            }
        }

        private fun setItemBackground(context: Context, textView: TextView) {
            val bg = if (textView.isSelected) {
                SkxDrawableUtil.Builder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(ContextCompat.getColor(context, R.color.skx_f05b72))
                        .setCornerRadius(dip2px(context, 10f).toFloat())
                        .create()
            } else {
                SkxDrawableUtil.Builder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setStroke(dip2px(context, 1f),
                                ContextCompat.getColor(context, R.color.skx_212121))
                        .setCornerRadius(dip2px(context, 10f).toFloat())
                        .create()
            }
            ViewCompat.setBackground(textView, bg)
        }
    }

}