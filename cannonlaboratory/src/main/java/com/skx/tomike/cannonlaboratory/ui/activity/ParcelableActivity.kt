package com.skx.tomike.cannonlaboratory.ui.activity

import android.content.Intent
import android.util.Log
import android.view.View
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.tomike.cannonlaboratory.R
import com.skx.tomike.cannonlaboratory.bean.Student

/**
 * 描述 : 序列化 - Parcelable
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/12/8 1:35 PM
 */
class ParcelableActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {
        intent?.run {
            if (hasExtra("student")) {
                val student = intent.getParcelableExtra<Student>("student")

                Log.e(TAG, String.format("通过 Parcelable 传递的对象为：%s,学号：%s,姓名：%s,性别：%s,出生日期：%s,班级：%s",
                        student,
                        student.id,
                        student.name,
                        student.sex,
                        student.birth,
                        student.clazz))
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_parcelable

    override fun initView() {
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