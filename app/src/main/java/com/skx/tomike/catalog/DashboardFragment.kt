package com.skx.tomike.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skx.common.base.BaseFragment
import com.skx.common.base.BaseViewModel
import com.skx.common.utils.dip2px
import com.skx.common.widget.GridSpaceItemDecoration
import com.skx.tomike.R

/**
 * 目录导航页
 *
 * @author shiguotao
 */
class DashboardFragment : BaseFragment<BaseViewModel<*>>() {

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
            addItemDecoration(GridSpaceItemDecoration(4,
                    dip2px(view.context, 8f),
                    dip2px(view.context, 6f))
            )
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