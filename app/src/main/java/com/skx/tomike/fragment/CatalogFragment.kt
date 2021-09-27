package com.skx.tomike.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skx.common.base.BaseFragment
import com.skx.tomike.R
import com.skx.tomike.adapter.Catalog2Adapter
import com.skx.tomike.model.CatalogListModel

/**
 * 目录导航页
 *
 * @author shiguotao
 */
class CatalogFragment : BaseFragment() {

    private var mCatalogRecyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)
        mCatalogRecyclerView = view.findViewById(R.id.catalog_recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // GridView 展示形式
        mCatalogRecyclerView?.run {
            layoutManager = GridLayoutManager(activity, 4)
            val mCatalogAdapter = Catalog2Adapter(CatalogListModel.createCatalogGroup())
            adapter = mCatalogAdapter
        }
    }
}