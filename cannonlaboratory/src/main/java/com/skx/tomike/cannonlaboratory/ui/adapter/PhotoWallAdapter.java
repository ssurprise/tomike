package com.skx.tomike.cannonlaboratory.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageItem;
import com.skx.common.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : 照片墙 adapter
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/19 4:47 PM
 */
public class PhotoWallAdapter extends RecyclerView.Adapter<PhotoWallAdapter.PhotoWallViewHolder> {

    private final List<PhotoUpImageItem> mPhotoList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public void setArrayList(List<PhotoUpImageItem> arrayList) {
        this.mPhotoList.clear();
        if (arrayList != null) {
            this.mPhotoList.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoWallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoWallViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_photo_wall, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoWallViewHolder holder, int position) {
        final int index = position;
        final PhotoUpImageItem photo = mPhotoList.get(position);

        if (!TextUtils.isEmpty(photo.getImagePath())) {
            ImageLoader.with(holder.itemView.getContext())
                    .load(photo.getImagePath())
                    .into(holder.mIvImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener == null) return;
                mOnItemClickListener.onItemClick(index, photo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

    static class PhotoWallViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvImage;

        PhotoWallViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvImage = itemView.findViewById(R.id.iv_photoWall_image);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * AdapterView has been clicked.
     */
    public interface OnItemClickListener {

        /**
         * Callback method to be invoked when an item in this AdapterView has
         * been clicked.
         *
         * @param position         点击位置
         * @param photoUpImageItem 图片数据
         */
        void onItemClick(int position, PhotoUpImageItem photoUpImageItem);
    }

}
