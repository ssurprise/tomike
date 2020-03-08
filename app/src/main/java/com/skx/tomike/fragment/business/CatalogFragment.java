package com.skx.tomike.fragment.business;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.R;
import com.skx.tomike.adapter.Catalog2Adapter;
import com.skx.tomike.customview.DividerGridItemDecoration;
import com.skx.tomike.customview.DividerItemDecoration;
import com.skx.tomike.model.CatalogListModel;


/**
 * 目录导航页
 *
 * @author shiguotao
 */
public class CatalogFragment extends Fragment {

    private RecyclerView mCatalogRecyclerView;
    private Catalog2Adapter mCatalogAdapter;
    private DividerGridItemDecoration dividerGridItemDecoration;
    private DividerItemDecoration dividerItemDecoration;

    private int mCurrentType = 0;

    public CatalogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        mCatalogRecyclerView = view.findViewById(R.id.catalog_recyclerView);

        TextView mBtnStyle = view.findViewById(R.id.catalog_style);
        mBtnStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentType == 0) {
                    mCurrentType = 1;
                    mCatalogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    // 需要先把之前的ItemDecoration清除掉 否则会出现重叠或者位置错乱
                    mCatalogRecyclerView.removeItemDecoration(dividerGridItemDecoration);
                    mCatalogRecyclerView.removeItemDecoration(dividerItemDecoration);
                    mCatalogAdapter.notifyDataSetChanged();

                } else {
                    mCurrentType = 0;
                    mCatalogRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                    mCatalogRecyclerView.removeItemDecoration(dividerItemDecoration);
                    mCatalogRecyclerView.removeItemDecoration(dividerGridItemDecoration);
                    mCatalogAdapter.notifyDataSetChanged();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 列展示分割线
        dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        // 网状格分割线
        dividerGridItemDecoration = new DividerGridItemDecoration(getActivity());
        // GridView 展示形式
        mCatalogRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

//        mCatalogRecyclerView.addItemDecoration(dividerGridItemDecoration);

        mCatalogAdapter = new Catalog2Adapter(getActivity(), CatalogListModel.createCatalogGroup());
        mCatalogRecyclerView.setAdapter(mCatalogAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
