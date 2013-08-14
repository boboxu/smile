package com.heme.smile;

import java.util.LinkedList;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.heme.smile.testdata.ChatGroup;

//群组和社区列表activity
public class GroupListActivity extends BaseActivity {
	private static final String TAG = "GroupListActivity";
	private ListView mListView;
	LinkedList<ChatGroup> groups = new LinkedList<ChatGroup>();
	private MyAdapter mAdapter;
	private void initGroupData(){
    	ChatGroup group1 = new ChatGroup();
    	group1.id = 1;
    	group1.serverId = 1;
    	group1.name = "三一班老师群";
    	groups.add(group1);
    	ChatGroup group2 = new ChatGroup();
    	group2.id = 2;
    	group2.serverId = 2;
    	group2.name = "三一班学生群";
    	groups.add(group2);
    	ChatGroup group3 = new ChatGroup();
    	group3.id = 3;
    	group3.serverId = 3;
    	group3.name = "三一班家长群";
    	groups.add(group3);
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initGroupData();
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.grouplist);
		((TextView)findViewById(R.id.titleTextView)).setText("我的群组");
		findViewById(R.id.backImg).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GroupListActivity.this.finish();
			}
		});
		mListView = (ListView)findViewById(R.id.listview);
		mAdapter = new MyAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(GroupListActivity.this, GroupChatActivity.class);
				intent.putExtra(GroupChatActivity.GROUP_ID, groups.get(arg2).name);
				startActivity(intent);
			}
		});
	}
	
	private class MyAdapter extends BaseAdapter{
		private Context ctx;
		
		public MyAdapter(Context ctx){
			this.ctx = ctx;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return groups.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return groups.get(position);
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
				convertView = LayoutInflater.from(this.ctx).inflate(R.layout.contact_item, null);
				viewHolder = new ViewHolder();
				viewHolder.ivAvatar = (ImageView)convertView.findViewById(R.id.contactitem_avatar_iv);
				viewHolder.tvNick = (TextView)convertView.findViewById(R.id.contactitem_nick);
				convertView.findViewById(R.id.contactitem_catalog).setVisibility(View.GONE);
				convertView.setTag(viewHolder);
			}else {
				viewHolder = (ViewHolder)convertView.getTag();
			}
			ChatGroup group = groups.get(position);
			viewHolder.ivAvatar.setImageResource(R.drawable.ic_launcher);
			viewHolder.tvNick.setText(group.name);
			return convertView;
		}
		class ViewHolder{
			ImageView ivAvatar;//头像
			TextView tvNick;//昵称
		}
	}
}
