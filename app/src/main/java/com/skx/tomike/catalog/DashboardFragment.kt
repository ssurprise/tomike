package com.skx.tomike.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skx.common.base.BaseFragment
import com.skx.common.utils.dip2px
import com.skx.common.widget.GridSpaceItemDecoration
import com.skx.tomike.R

/**
 * 目录导航页
 *
 * @author shiguotao
 */
class DashboardFragment : BaseFragment<CatalogViewModel>() {

    private var mRv: RecyclerView? = null
    private var mAdapter: DashboardAdapter = DashboardAdapter()
    private var mKey: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mKey = arguments?.getString(KEY) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        mRv = view.findViewById(R.id.rv_dashboard)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRv?.run {
            layoutManager = GridLayoutManager(activity, 4)
            addItemDecoration(
                GridSpaceItemDecoration(
                    4,
                    dip2px(view.context, 8f),
                    dip2px(view.context, 6f)
                )
            )
            adapter = mAdapter.also {
                it.setItemClickListener { _, item ->
                    mViewModel?.add2RecentHistory(item)
                }
            }
        }
        if (true == mViewModel?.isRecentHistoryGroup(mKey)) {
            // 最近使用
            Log.e("111111","111-0")
            mViewModel?.recentItemLiveData?.observe(viewLifecycleOwner) {
                Log.e("111111","111-1")
                mAdapter.setData(it)
            }
        } else {
            mViewModel?.catalogItemLiveData?.observe(viewLifecycleOwner) {
                Log.e("111111","222")
                mAdapter.setData(it)
            }
        }
        mViewModel?.fetchCatalogByKey(mKey)
    }

    override fun onResume() {
        super.onResume()
        if (true == mViewModel?.isRecentHistoryGroup(mKey)) {
            mViewModel?.fetchCatalogByKey(mKey)
        }
    }

    override fun getNickName(): String {
        return mKey
    }

    companion object {

        private const val KEY = "key_dashboard"

        fun getInstance(key: String): DashboardFragment {
            val fragment = DashboardFragment()
            fragment.arguments = Bundle().apply {
                putString(KEY, key)
            }
            return fragment
        }
    }
}