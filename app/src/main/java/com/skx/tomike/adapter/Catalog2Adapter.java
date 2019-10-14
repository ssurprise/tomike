package com.skx.tomike.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.model.CatalogCellModel;

import java.util.List;

/**
 * 目录列表Adapter
 *
 * @author shiguotao
 *         Created on 2016/4/11.
 */
public class Catalog2Adapter extends RecyclerView.Adapter<Catalog2Adapter.CatalogCellViewHolder> {

    private static final int VIEW_TYPE_PARENT = 1;
    private static final int VIEW_TYPE_CHILD = 2;


    private Context context;
    private List<CatalogCellModel> mList;
    private LayoutInflater mInflater;

    public Catalog2Adapter(Context context, List<CatalogCellModel> list) {
        super();
        this.context = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(context);
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
        return mList != null ? mList.size() : 0;
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


    @Override
    public CatalogCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_PARENT:
                return new CatalogGroupViewHolder(mInflater.inflate(R.layout.adapter_catalog_item_parent, parent, false));
            default:
                return new CatalogChildViewHolder(mInflater.inflate(R.layout.adapter_catalog_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(CatalogCellViewHolder holder, int position) {
        holder.bindData(mList.get(position));
    }

    abstract class CatalogCellViewHolder extends RecyclerView.ViewHolder {

        CatalogCellViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindData(CatalogCellModel cellModel);
    }

    class CatalogChildViewHolder extends CatalogCellViewHolder {
        TextView tvCellName;

        CatalogChildViewHolder(View itemView) {
            super(itemView);
            tvCellName = (TextView) itemView.findViewById(R.id.catalog_item_child_name);
        }

        @Override
        public void bindData(final CatalogCellModel cellModel) {
            tvCellName.setText(cellModel.getTitle());
            tvCellName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        v.setElevation(5.0f);
                    }
                    try {
                        intent = new Intent(context, Class.forName(cellModel.getTarget()));
                        context.startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    class CatalogGroupViewHolder extends CatalogCellViewHolder {

        TextView tvGroupTittle;
        ImageView imgvGroupArrow;

        CatalogGroupViewHolder(View itemView) {
            super(itemView);
            tvGroupTittle = (TextView) itemView.findViewById(R.id.catalog_item_group_name);
            imgvGroupArrow = (ImageView) itemView.findViewById(R.id.catalog_item_group_arrow);
        }

        @Override
        public void bindData(CatalogCellModel cellModel) {
            tvGroupTittle.setText(cellModel.getTitle());
        }
    }
}
