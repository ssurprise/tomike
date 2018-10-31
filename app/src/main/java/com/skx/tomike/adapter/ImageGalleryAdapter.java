package com.skx.tomike.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.skx.tomikecommonlibrary.utils.LoadImageUtil;

import java.util.List;

/**
 * Created by shiguotao on 2016/3/27.
 */
public class ImageGalleryAdapter extends BaseAdapter {
    private Context mContext;        //上下文对象

    private List<Integer> mList;
    public ImageGalleryAdapter(Context context) {
        this.mContext = context;
    }

    public void setImageList(List<Integer> list){
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int imageReasouId = mList.get(position);
        ImageView imageView = new ImageView(mContext);

//        imageView.setImageResource(imageReasouId);

        LoadImageUtil.decodeSampledBitmapFromResource(mContext.getResources(),imageReasouId,50,50);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }
}
