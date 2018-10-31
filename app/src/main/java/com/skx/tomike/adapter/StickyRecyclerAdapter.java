package com.skx.tomike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skx.tomike.R;

import java.util.List;

/**
 * Created by shiguotao on 2016/11/12.
 */
public class StickyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM_TYPE_COMMENT = 0;
    private static final int ITEM_TYPE_HEAD = 1;
    private static final int ITEM_TYPE_FOOT = 2;

    private List<String> mDataList;
    private Context mContext;

    public StickyRecyclerAdapter(Context context, List<String> nationList) {
        this.mContext = context;
        this.mDataList = nationList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE_FOOT){
            View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_recycler_item_footer, parent, false);
            return new FooterViewHolder(view);

        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_sticky_recycler_item, parent, false);
            return new CommonItemHolder(view);

        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if(position ==getItemCount()-1){
            return ITEM_TYPE_FOOT;
        }
        return ITEM_TYPE_COMMENT;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommonItemHolder) {
            String nationBaseInfoDto = mDataList.get(position);
            CommonItemHolder nationItemHolder = (CommonItemHolder) holder;
            nationItemHolder.name.setText(nationBaseInfoDto);


        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder itemHeaderHolder = (FooterViewHolder) holder;
            itemHeaderHolder.tagName.setText("加载更多");
        }
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() + 1 : 0;
    }

    class CommonItemHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView mobileNationCode;

        private CommonItemHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nationList_item_name);
            mobileNationCode = (TextView) itemView.findViewById(R.id.nationList_item_mobileNationCode);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView tagName;

        private FooterViewHolder(View itemView) {
            super(itemView);
            tagName = (TextView) itemView;
        }
    }
}
