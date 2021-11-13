package com.skx.tomike.tank.widget.activity

import android.text.Editable
import android.text.TextWatcher
import android.text.method.ReplacementTransformationMethod
import android.widget.EditText
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_EDITTEXT_LOWER2UPPER

/**
 * 描述 : EditText 小写字母转大写.
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/7 2:31 PM
 * 扩展：inputType 的枚举类型 -> https://blog.csdn.net/chaod5659/article/details/17117193
 */
@Route(path = ROUTE_PATH_EDITTEXT_LOWER2UPPER)
class Lowercase2UppercaseActivity : SkxBaseActivity<BaseViewModel?>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("EditText 小写字母转大写").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_lowercase_2_uppercase
    }

    private val transformationMethod: ReplacementTransformationMethod =
            object : ReplacementTransformationMethod() {
                override fun getOriginal(): CharArray {
                    return charArrayOf(
                            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
                    )
                }

                override fun getReplacement(): CharArray {
                    return charArrayOf(
                            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                    )
                }
            }

    override fun initView() {
        val mEtInputBox = findViewById<EditText>(R.id.et_lowercase2Uppercase_content)
        val mTvLogcat = findViewById<TextView>(R.id.tv_lowercase2Uppercase_logcat)
        mEtInputBox.transformationMethod = transformationMethod
        mEtInputBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                mTvLogcat.text = s.toString()
            }
        })
    }
}