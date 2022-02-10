package com.skx.tomike.cannon.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseFragment
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_FRAGMENT

/**
 * 描述 : fragment 学习
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/12/31 2:11 下午
 */
@Route(path = ROUTE_PATH_FRAGMENT)
class FragmentPracticeActivity : SkxBaseActivity<BaseViewModel>(), View.OnClickListener {

    private var index = 0

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("fragment demo").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_fragment_practice
    }

    override fun initView() {
        findViewById<TextView>(R.id.tv_fgmt_add).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_fgmt_replace).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_fgmt_back).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_fgmt_add -> {
                index++
                Log.e(TAG, "index -> $index")
                val instance = FgmtPartice.getInstance("$index")
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.add(R.id.tv_fgmt_screen, instance, "$index")
                transaction.addToBackStack("$index")
                transaction.commit()
            }
            R.id.tv_fgmt_replace -> {
                index++
                val instance = FgmtPartice.getInstance("$index")
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.tv_fgmt_screen, instance, "$index")
                transaction.addToBackStack("$index")
                transaction.commit()
            }
            R.id.tv_fgmt_back -> {
                onBackPressed()
            }
        }
    }
}

class FgmtPartice : BaseFragment() {

    private var mTvTxt: TextView? = null
    private var mIndex = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIndex = arguments?.getString(KEY_INDEX) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_practice, container, false)
        mTvTxt = view.findViewById(R.id.tv_fgmt_txt)
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTvTxt?.text = "fragment ->  $mIndex -> hashCode:${hashCode()}"
    }

    companion object {
        const val KEY_INDEX = "index"
        fun getInstance(index: String): FgmtPartice {
            return FgmtPartice().apply {
                arguments = Bundle().apply {
                    putString(KEY_INDEX, index)
                }
            }
        }
    }
}