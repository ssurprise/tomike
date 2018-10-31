package com.skx.tomike.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.skx.tomike.R;
import com.skx.tomike.javabean.PhotoUpImageBucket;

/**
 * @author shiguotao
 * 
 *         相册列表的adapter
 */
public class PhotoAlbumsAdapter extends BaseAdapter {
	private List<PhotoUpImageBucket> mArrayList;
	private LayoutInflater layoutInflater;

	public PhotoAlbumsAdapter(Context context, List<PhotoUpImageBucket> arrayList) {
		super();
		layoutInflater = LayoutInflater.from(context);
		this.mArrayList = arrayList;
	}

	@Override
	public int getCount() {
		return mArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return mArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PhotoUpImageBucket photoUpImageBucket = mArrayList.get(position);
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = layoutInflater.inflate(R.layout.adapter_photo_albums, parent, false);
			holder.image = (ImageView) convertView.findViewById(R.id.album_firstPhoto);
			holder.name = (TextView) convertView.findViewById(R.id.album_name);
			holder.count = (TextView) convertView.findViewById(R.id.album_photoCount);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.count.setText("" + photoUpImageBucket.getCount());
		holder.name.setText(photoUpImageBucket.getBucketName());

//		Glide.with(layoutInflater.getContext()).load("file://" + photoUpImageBucket.getImageList().get(0).getImagePath()).into(holder.image);

		return convertView;
	}

	class Holder {
		ImageView image;
		TextView name;
		TextView count;
	}

	public void setArrayList(List<PhotoUpImageBucket> arrayList) {
		this.mArrayList = arrayList;
	}
}
