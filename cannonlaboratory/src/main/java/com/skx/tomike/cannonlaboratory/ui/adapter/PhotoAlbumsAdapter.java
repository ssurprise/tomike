package com.skx.tomike.cannonlaboratory.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageBucket;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 描述 : 相册列表的adapter
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/19 4:47 PM
 */
public class PhotoAlbumsAdapter extends RecyclerView.Adapter<PhotoAlbumsAdapter.PhotoAlbumsViewHolder> {

    private List<PhotoUpImageBucket> mArrayList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public void setArrayList(List<PhotoUpImageBucket> arrayList) {
        this.mArrayList.clear();
        if (arrayList != null) {
            this.mArrayList.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoAlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoAlbumsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_photo_albums, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAlbumsViewHolder holder, int position) {
        final int index = position;
        final PhotoUpImageBucket photoUpImageBucket = mArrayList.get(position);
        holder.mTvAlbumName.setText(photoUpImageBucket.bucketName);
        holder.mTvPhotoCount.setText(String.format(Locale.getDefault(), "%d张", photoUpImageBucket.count));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener == null) return;
                mOnItemClickListener.onItemClick(index, photoUpImageBucket);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    static class PhotoAlbumsViewHolder extends RecyclerView.ViewHolder {
        ImageView mThumbnailsImage;
        TextView mTvAlbumName;
        TextView mTvPhotoCount;

        PhotoAlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            mThumbnailsImage = itemView.findViewById(R.id.iv_photoAlbum_picture);
            mTvAlbumName = itemView.findViewById(R.id.tv_photoAlbum_name);
            mTvPhotoCount = itemView.findViewById(R.id.tv_photoAlbum_photoCount);
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
         * @param position   点击位置
         * @param photoAlbum 相册数据对象
         */
        void onItemClick(int position, PhotoUpImageBucket photoAlbum);
    }

}
