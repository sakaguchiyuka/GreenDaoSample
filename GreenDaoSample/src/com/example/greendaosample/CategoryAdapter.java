package com.example.greendaosample;

import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<Category> {
	private LayoutInflater mLayoutInflater;
	private OnClickListener mItemClickListener;

	public CategoryAdapter(Context context, List<Category> objects, OnClickListener itemClickListener) {
		super(context, 0, objects);

		mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mItemClickListener = itemClickListener;
	}

	@Override
	public void addAll(Collection<? extends Category> collection) {
		if (null == collection || 0 == collection.size()) {
			return;
		}
		for (Category category : collection) {
			add(category);
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView) {
			convertView = mLayoutInflater.inflate(R.layout.category_list_item, null);
			convertView.findViewById(R.id.category_delete_btn).setOnClickListener(mItemClickListener);
		}
		Category category = getItem(position);
		setText(convertView, R.id.category_id, "id:"+String.valueOf(category.getId()));
		setText(convertView, R.id.category_name, "name:" + category.getCategory_name());
		convertView.findViewById(R.id.category_delete_btn).setTag(position);
		return convertView;
	}

	protected void setText(View v, int id, String text) {
		((TextView)v.findViewById(id)).setText(text);
	}
}
