package com.heme.smile.ui.view;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.heme.smile.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TextGridAdapter extends BaseAdapter {
	private Context ctx;
	private LinkedHashMap<String, String> mOperations = new LinkedHashMap<String, String>();
	private LinkedList<String> mKeyList = new LinkedList<String>();
	public TextGridAdapter(Context context,LinkedHashMap<String, String> opes,LinkedList<String> keys){
		this.ctx = context;
		this.mOperations = opes;
		this.mKeyList = keys;
	}
	@Override
	public int getCount() {
		if (mKeyList==null) {
			return 0;
		}
		return mKeyList.size();
	}

	@Override
	public Object getItem(int arg0) {
		if (mKeyList==null||mKeyList.size()==0) {
			return null;
		}
		return mKeyList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView==null) {
			convertView = LayoutInflater.from(ctx).inflate(R.layout.text_grid_item, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView)convertView.findViewById(R.id.operate_key);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.textView.setText((String)getItem(position));
		return convertView;
	}
	
	class ViewHolder{
		TextView textView;
	}

}
