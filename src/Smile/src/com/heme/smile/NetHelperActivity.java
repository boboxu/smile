package com.heme.smile;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NetHelperActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "NetHelperActivity";
	private ListView mListView;
	private ArrayList<NotifyData> datas = new ArrayList<NetHelperActivity.NotifyData>();
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		emulateDatas();
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.nethelper);
		findViewById(R.id.backImg).setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("告警信息");
		mListView = (ListView)findViewById(R.id.listview);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(NetHelperActivity.this, SendPcCommondActivity.class);
				intent.putExtra(SendPcCommondActivity.NOTIFY_ID, datas.get(arg2).id);
				startActivity(intent);
			}
		});
		adapter = new MyAdapter(this);
		mListView.setAdapter(adapter);
	}
	private void emulateDatas(){
		NotifyData data = new NotifyData();
		data.id = 1;
		data.time = "11:34";
		data.content = "报警,你儿子正在撸管!!!";
		datas.add(data);
		NotifyData notifyData = new NotifyData();
		notifyData.id = 2;
		notifyData.time = "12:22";
		notifyData.content = "报警,你儿子正在看快播";
		datas.add(notifyData);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.backImg:
			finish();
			break;

		default:
			break;
		}
	}
	class NotifyData{
		int id;
		String time;
		String content;
	}
	class MyAdapter extends BaseAdapter{
		Context ctx;
		
		public MyAdapter(Context ctx){
			this.ctx = ctx;
		}

		@Override
		public int getCount() {
			if (datas==null) {
				return 0;
			}
			return datas.size();
		}

		@Override
		public Object getItem(int position) {
			if (datas==null) {
				return null;
			}
			return datas.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			NotifyData data = (NotifyData)getItem(position);
			if (convertView==null) {
				convertView = LayoutInflater.from(ctx).inflate(R.layout.nethelper_item, null);
				viewHolder = new ViewHolder();
				convertView.setTag(viewHolder);
			}else {
				viewHolder = (ViewHolder)convertView.getTag();
			}
			viewHolder.time = (TextView)convertView.findViewById(R.id.time);
			viewHolder.time.setText(data.time);
			viewHolder.content = (TextView)convertView.findViewById(R.id.content);
			viewHolder.content.setText(data.content);
			return convertView;
		}
		
		
	}
	class ViewHolder{
		TextView time;
		TextView content;
	}
}
