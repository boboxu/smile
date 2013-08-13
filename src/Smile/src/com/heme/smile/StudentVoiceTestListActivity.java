package com.heme.smile;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.heme.smile.util.VoiceData;

//语音评测列表界面_学生版
public class StudentVoiceTestListActivity extends BaseActivity {
	private static final String TAG = "StudentVoiceTestListActivity";
	private List<VoiceData> mDatas = new LinkedList<VoiceData>();
	private ListView mListView;
	private MyAdapter mAdapter;
	
	private void initDatas(){
		VoiceData data = new VoiceData();
		data.nickname = "陈冠西老师";
		data.content = "读第三篇课文给我听听";
		mDatas.add(data);
		VoiceData data2 = new VoiceData();
		data2.nickname = "张三丰老师";
		data2.content = "啊啊啊我是卖报的小行家";
		mDatas.add(data2);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initDatas();
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.voidcetestlist);
		findViewById(R.id.backImg).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		((TextView)findViewById(R.id.titleTextView)).setText("语音评测");
		mListView = (ListView)findViewById(R.id.voicetestlist);
		mAdapter = new MyAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(StudentVoiceTestListActivity.this, StudentVoiceTestDetailActivity.class);
				Bundle b = new Bundle();
				VoiceData data = mDatas.get(arg2);
				b.putSerializable(StudentVoiceTestDetailActivity.VOICE_DATA, data);
				intent.putExtras(b);
				startActivity(intent);
			}
		});
	}
	
	
	
	class MyAdapter extends BaseAdapter{
		Context ctx;
		public MyAdapter(Context ctx){
			this.ctx = ctx;
		}
		@Override
		public int getCount() {
			if (mDatas==null) {
				return 0;
			}
			return mDatas.size();
		}
		@Override
		public Object getItem(int position) {
			if (mDatas==null) {
				return null;
			}
			return mDatas.get(position);
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			VoiceData data = (VoiceData)getItem(position);
			if (convertView==null) {
				convertView = LayoutInflater.from(ctx).inflate(R.layout.recentmessage_item, null);
				viewHolder = new ViewHolder();
				viewHolder.nick = (TextView)convertView.findViewById(R.id.nickname);
				viewHolder.content = (TextView)convertView.findViewById(R.id.msg_content);
				viewHolder.avatar = (ImageView)convertView.findViewById(R.id.avatar);
				convertView.setTag(viewHolder);
			}else {
				viewHolder = (ViewHolder)convertView.getTag();
			}
			
			viewHolder.nick.setText(data.nickname);
			viewHolder.avatar.setImageResource(R.drawable.ic_launcher);
			viewHolder.content.setText(data.content);
			return convertView;
		}
		class ViewHolder{
			TextView nick;
			TextView content;
			ImageView avatar;
		}
	}
}
