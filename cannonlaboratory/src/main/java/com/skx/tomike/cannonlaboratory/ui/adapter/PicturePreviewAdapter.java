package com.skx.tomike.cannonlaboratory.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.IPicture;
import com.skx.tomikecommonlibrary.imageloader.ImageLoader;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PicturePreviewAdapter extends PagerAdapter {

    private final List<IPicture> mPicturesList = new ArrayList<>();

    public PicturePreviewAdapter(List<IPicture> pictures) {
        setData(pictures);
    }

    public void setData(List<IPicture> pictures) {
        mPicturesList.clear();
        if (pictures != null) {
            mPicturesList.addAll(pictures);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPicturesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = (ImageView) LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_picture_preview_item, container, false);
        ImageLoader.with(container.getContext())
                .load(mPicturesList.get(position).getPicturePath())
                .placeholder(R.drawable.icon_picture_placeholer)
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NotNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
