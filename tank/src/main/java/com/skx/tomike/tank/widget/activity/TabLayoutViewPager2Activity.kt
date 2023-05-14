package com.skx.tomike.tank.widget.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_TABLELAYOUT_VIEWPAGER2
import java.util.*

/**
 * 描述 : TabLayout + ViewPager2
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 :  2021/12/10 10:07 上午
 */
@Route(path = ROUTE_PATH_TABLELAYOUT_VIEWPAGER2)
class TabLayoutViewPager2Activity : SkxBaseActivity<BaseViewModel<*>>() {

    private val mTabLayout: TabLayout by lazy {
        findViewById(R.id.tl_tb4vp2_tab)
    }
    private val vpContent: ViewPager2 by lazy {
        findViewById(R.id.vp_tb4vp2_content)
    }

    private val tabList: MutableList<String> = ArrayList()

    override fun initParams() {
        tabList.add("全是幺蛾子")
        tabList.add("难")
        tabList.add("are you kidding？")
        tabList.add("太难了吧")
        tabList.add("here you are")
        tabList.add("搞笑呢吧")
        tabList.add("all right")
    }

    override fun layoutId(): Int {
        return R.layout.activity_tablayout_viewpager2
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("TabLayout + ViewPager2").create()
    }

    override fun initView() {
        vpContent.adapter = Tlvp2Adapter(
            this,
            mutableListOf(
                Tlvp2Fragment(),
                Tlvp2Fragment(),
                Tlvp2Fragment(),
                Tlvp2Fragment(),
                Tlvp2Fragment(),
                Tlvp2Fragment(),
                Tlvp2Fragment()
            )
        )
        TabLayoutMediator(mTabLayout, vpContent) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

class Tlvp2Adapter(
    fragmentActivity: FragmentActivity,
    private val fragments: List<Fragment>
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}

class Tlvp2Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tlwp2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}