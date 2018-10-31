package com.skx.tomike.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomike.javabean.PhotoUpImageItem;

/**
 * @author shiguotao
 * 
 *         单个相册的adapter
 */
public class PhotoAlbumItemAdapter extends BaseAdapter {
	private List<PhotoUpImageItem> list;
	private LayoutInflater layoutInflater;

	public PhotoAlbumItemAdapter(List<PhotoUpImageItem> list, Context context) {
		this.list = list;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.adapter_photo_album, parent, false);
			holder = new Holder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.image_item);
			holder.checkBox = (CheckBox) convertView.findViewById(R.id.check);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

//		Glide.with(layoutInflater.getContext()).load("file://" + list.get(position).getImagePath()).into(holder.imageView);

		holder.checkBox.setChecked(list.get(position).isSelected());
		return convertView;
	}

	class Holder {
		ImageView imageView;
		CheckBox checkBox;
	}
}
