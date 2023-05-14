package com.skx.tomike.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.skx.common.base.BaseFragment
import com.skx.tomike.R
import com.skx.tomike.tank.widget.activity.Tlvp2Adapter
import com.skx.tomike.tank.widget.view.TabLayoutMediatorX
import java.util.*

/**
 * 目录导航页
 *
 * @author shiguotao
 */
class CatalogFragment : BaseFragment<CatalogViewModel>() {

    private val tabList: MutableList<String> = ArrayList()

    private var mTbLayout: TabLayout? = null
    private var mVpContent: ViewPager2? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)
        mTbLayout = view.findViewById(R.id.tl_catalog_tab)
        mVpContent = view.findViewById(R.id.vp_catalog_content)
        mVpContent?.isUserInputEnabled = true
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel?.catalogGroupLiveData?.observe(viewLifecycleOwner, { mutableList ->
            val fragments: MutableList<DashboardFragment> = mutableListOf()
            mutableList.forEach {
                tabList.add(it.title)
                fragments.add(DashboardFragment.getInstance(it.title))
            }

            if (mContext is FragmentActivity) {
                mVpContent?.adapter = Tlvp2Adapter(
                        mContext as FragmentActivity,
                        fragments
                )
            }

            if (mTbLayout != null && mVpContent != null) {
                TabLayoutMediatorX(mTbLayout!!, mVpContent!!, false, false) { tab, position ->
                    tab.text = tabList[position]
                }.attach()
            }
        })

        mViewModel?.createCatalogGroup()
    }
}