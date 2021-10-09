package com.skx.tomike.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.R;
import com.skx.tomike.model.CatalogCellModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录列表Adapter
 *
 * @author shiguotao
 * Created on 2016/4/11.
 */
public class Catalog2Adapter extends RecyclerView.Adapter<Catalog2Adapter.CatalogCellViewHolder> {

    private static final int VIEW_TYPE_PARENT = 1;
    private static final int VIEW_TYPE_CHILD = 2;

    private final List<CatalogCellModel> mList = new ArrayList<>();

    public Catalog2Adapter(List<CatalogCellModel> list) {
        super();
        mList.clear();
        if (list != null) {
            mList.addAll(list);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final int oldSpanCnt = gridLayoutManager.getSpanCount();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == VIEW_TYPE_PARENT) {
                        return oldSpanCnt;
                    } else {
                        return 1;
                    }

                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public int getItemViewType(int position) {
        CatalogCellModel catalogCellModel = mList.get(position);
        if (catalogCellModel.isParent()) {
            return VIEW_TYPE_PARENT;
        } else {
            return VIEW_TYPE_CHILD;
        }
    }

    @NonNull
    @Override
    public CatalogCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_PARENT) {
            return new CatalogGroupViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.adapter_catalog_item_parent, parent, false));
        }
        return new CatalogChildViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_catalog_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CatalogCellViewHolder holder, int position) {
        holder.bindData(mList.get(position));
    }

    abstract static class CatalogCellViewHolder extends RecyclerView.ViewHolder {

        CatalogCellViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindData(CatalogCellModel cellModel);
    }

    static class CatalogChildViewHolder extends CatalogCellViewHolder {
        TextView tvCellName;

        CatalogChildViewHolder(View itemView) {
            super(itemView);
            tvCellName = (TextView) itemView.findViewById(R.id.catalog_item_child_name);
        }

        @Override
        public void bindData(final CatalogCellModel cellModel) {
            tvCellName.setText(cellModel.getTitle());
            tvCellName.setOnClickListener(v -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    v.setElevation(5.0f);
                }
                try {
                    Intent intent = new Intent(itemView.getContext(), Class.forName(cellModel.getTarget()));
                    ((Activity) itemView.getContext()).startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    static class CatalogGroupViewHolder extends CatalogCellViewHolder {

        TextView tvGroupTittle;
        ImageView ivGroupArrow;

        CatalogGroupViewHolder(View itemView) {
            super(itemView);
            tvGroupTittle = (TextView) itemView.findViewById(R.id.catalog_item_group_name);
            ivGroupArrow = (ImageView) itemView.findViewById(R.id.catalog_item_group_arrow);
        }

        @Override
        public void bindData(CatalogCellModel cellModel) {
            tvGroupTittle.setText(cellModel.getTitle());
        }
    }
}
