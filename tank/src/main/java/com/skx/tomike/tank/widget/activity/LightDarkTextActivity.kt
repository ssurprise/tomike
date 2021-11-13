package com.skx.tomike.tank.widget.activity

import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_EDITTEXT_LIGHT2DARK
import java.util.*

/**
 * 描述 : EditText明暗文切换
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/1 7:46 PM
 */
@Route(path = ROUTE_PATH_EDITTEXT_LIGHT2DARK)
class LightDarkTextActivity : SkxBaseActivity<BaseViewModel>() {

    private var isDarkStatus = false
    private var btnSwitch: AppCompatTextView? = null
    private val editTexts: MutableList<AppCompatEditText> = ArrayList(4)
    private var index = 0

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("EditText 明暗文切换").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_light_dark_text
    }

    override fun initView() {
        val input = findViewById<AppCompatEditText>(R.id.lightDarkText_input)
        val input1 = findViewById<AppCompatEditText>(R.id.lightDarkText_input1)
        val input2 = findViewById<AppCompatEditText>(R.id.lightDarkText_input2)
        val input3 = findViewById<AppCompatEditText>(R.id.lightDarkText_input3)

        editTexts.add(input)
        editTexts.add(input1)
        editTexts.add(input2)
        editTexts.add(input3)

        btnSwitch = findViewById(R.id.lightDarkText_switch)
        btnSwitch?.text = "暗文"

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val length = s.length
                if (length >= 1 && index < editTexts.size - 1) {
                    editTexts[++index].requestFocus(EditorInfo.IME_ACTION_NEXT)
                }
            }
        }
        input.addTextChangedListener(textWatcher)
        input1.addTextChangedListener(textWatcher)
        input2.addTextChangedListener(textWatcher)
        input3.addTextChangedListener(textWatcher)
        btnSwitch?.setOnClickListener {
            isDarkStatus = !isDarkStatus
            if (isDarkStatus) {
                btnSwitch?.text = "明文"
                for (et in editTexts) {
                    et.transformationMethod = PasswordCharSequenceStyle()
                }
            } else {
                btnSwitch?.text = "暗文"
                for (et in editTexts) {
                    et.transformationMethod = HideReturnsTransformationMethod.getInstance()
                }
            }
        }
    }

    inner class PasswordCharSequenceStyle : PasswordTransformationMethod() {
        override fun getTransformation(source: CharSequence, view: View): CharSequence {
            return PasswordCharSequence(source)
        }
    }

    inner class PasswordCharSequence(val mSource: CharSequence) : CharSequence {

        override val length: Int
            get() = mSource.length


        override fun get(index: Int): Char {
            return '*'
        }

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return mSource.subSequence(startIndex, endIndex)
        }

    }
}