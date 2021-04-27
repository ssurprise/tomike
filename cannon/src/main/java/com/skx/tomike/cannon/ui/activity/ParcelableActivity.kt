package com.skx.tomike.cannon.ui.activity

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.bean.Student

/**
 * 描述 : 序列化 - Parcelable
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/12/8 1:35 PM
 */
class ParcelableActivity : SkxBaseActivity<BaseViewModel>() {

    var student: Student? = null

    override fun initParams() {
        intent?.run {
            if (hasExtra("student")) {
                student = intent.getParcelableExtra("student")
            }
        }
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("Parcelable 序列化").create()
    }

    override fun getLayoutId(): Int = R.layout.activity_parcelable

    override fun initView() {
        val mTvLogcat = findViewById<TextView>(R.id.tv_parcelable_logcat)
        mTvLogcat.text = String.format("通过 Parcelable 传递的对象为：%s,\n学号：%s \n姓名：%s \n性别：%s \n出生日期：%s \n班级：%s",
                student,
                student?.id,
                student?.name,
                student?.sex,
                student?.birth,
                student?.clazz)
    }

    fun onClick2NextPage(view: View?) {
        val student = Student()
        student.id = 1001
        student.name = "二柱子"
        student.sex = "男"
        student.birth = "2000.10.12"
        student.clazz = "三年级二班"

        val intent = Intent(this, ParcelableActivity::class.java)
        intent.putExtra("student", student)
        startActivity(intent)
    }
}