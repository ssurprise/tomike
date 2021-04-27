package com.skx.tomike.tank.widget.activity

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.widget.adapter.RecyclerViewBannerAdapter
import java.util.*

/**
 * 描述 : RecyclerView child count 测试
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
class RecyclerViewChildCountActivity : SkxBaseActivity<BaseViewModel?>(), View.OnClickListener {

    private var mRvShow: RecyclerView? = null

    private val mBannerList: MutableList<String> = ArrayList()
    private val mContentList: MutableList<String> = LinkedList()

    override fun initParams() {
        mBannerList.add("http://pic1.win4000.com/wallpaper/6/58194994a591e.jpg")
        mBannerList.add("http://pic1.win4000.com/wallpaper/6/5819499e74cf8.jpg")
        mBannerList.add("http://pic1.win4000.com/wallpaper/3/584f9928e1771.jpg")
        mBannerList.add("http://pic1.win4000.com/wallpaper/3/584f992d6bd62.jpg")
        mBannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590993126378&di=b2f667623306f875b48d50f120267a88&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F201611_18_11%2Fa46lpo74655993745596.gif")
        mBannerList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1761055549,119613524&fm=26&gp=0.jpg")
        var i = 0
        val j = 50
        while (i < j) {
            mContentList.add("第" + i + "个")
            i++
        }
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("RecyclerView child count 测试").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_recyclerview_child_count
    }

    override fun initView() {
        findViewById<TextView>(R.id.btn_recyclerviewChildCount_horizontal).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_recyclerviewChildCount_verticalFixHeight).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_recyclerviewChildCount_verticalMatchParent).setOnClickListener(this)

        mRvShow = findViewById(R.id.rv_recyclerviewChildCount_show)
        mRvShow?.layoutManager = LinearLayoutManager(this)
        mRvShow?.adapter = RecyclerViewBannerAdapter(mBannerList)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_recyclerviewChildCount_horizontal -> {
                mRvShow?.apply {
                    val layoutParams = layoutParams
                    layoutParams.height = 600
                    setLayoutParams(layoutParams)

                    val layoutManager = LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false)
                    mRvShow?.layoutManager = layoutManager
//                    mRvShow?.adapter?.notifyDataSetChanged()
                }
            }
            R.id.btn_recyclerviewChildCount_verticalFixHeight -> {
                mRvShow?.apply {
                    val layoutParams = layoutParams
                    layoutParams.height = 1000
                    setLayoutParams(layoutParams)

                    val layoutManager = LinearLayoutManager(mActivity)
                    mRvShow?.layoutManager = layoutManager
//                    mRvShow?.adapter?.notifyDataSetChanged()
                }
            }
            R.id.btn_recyclerviewChildCount_verticalMatchParent -> {
                mRvShow?.apply {
                    val layoutParams = layoutParams
                    layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
                    setLayoutParams(layoutParams)

                    val layoutManager = LinearLayoutManager(mActivity)
                    mRvShow?.layoutManager = layoutManager
//                    mRvShow?.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

}