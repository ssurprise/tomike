package com.skx.tomike.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skx.common.base.BaseFragment
import com.skx.tomike.R
import com.skx.tomike.adapter.DashboardAdapter
import com.skx.tomike.model.CatalogListModel

/**
 * 目录导航页
 *
 * @author shiguotao
 */
class DashboardFragment : BaseFragment() {

    private var mRv: RecyclerView? = null
    private var mKey: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mKey = arguments?.getString(KEY) ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        mRv = view.findViewById(R.id.rv_dashboard)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRv?.run {
            layoutManager = GridLayoutManager(activity, 4)
            adapter = DashboardAdapter(CatalogListModel.fetchCatalogByKey(mKey))
        }
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