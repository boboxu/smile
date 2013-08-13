package com.heme.smile;

import java.util.LinkedList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heme.smile.util.ChatMsgEntity;

public class MessageActivity extends BaseActivity {
	private static final String TAG = "RecentMessageListActivity";
	private ListView mListView;
	private LinkedList<ChatMsgEntity> messageList = new LinkedList<ChatMsgEntity>();
	private MyAdapter mAdapter;
	private long mExitTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
			if((System.currentTimeMillis()-mExitTime) > 2000){
				Toast.makeText(getApplicationContext(), "再按一次返回键回到桌面", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis(); 
			}else {
				backToDesk();
			}
			return true;
		}	
		return true;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		emulateMsgData();
		initUI();
		findViewById(R.id.backImg).setVisibility(View.GONE);
		((TextView)findViewById(R.id.titleTextView)).setText("消息");
	}
	private void initUI(){
		setContentView(R.layout.recentmessagelist);
		mListView = (ListView)findViewById(R.id.recentmsglist);
		mAdapter = new MyAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					//公告信息
					startActivity(new Intent(MessageActivity.this, NotificationActivity.class));
					break;
				case 1:
					//家庭作业助手
					gotoWebview("家庭作业助手", "http://m.163.com");
					break;
				case 2:
					//成绩提醒助手
					gotoWebview("成绩查询助手", "http://sina.cn");
					break;
				case 3:
					//语音评测
					startActivity(new Intent(MessageActivity.this,StudentVoiceTestListActivity.class));
					break;
				case 4:
					Intent intent = new Intent(MessageActivity.this, GroupChatActivity.class);
					intent.putExtra(GroupChatActivity.GROUP_ID, "95611家长群");
					startActivity(intent);
					break;
				default:
					break;
				}
				
			}
		});
	}
	private void emulateMsgData(){
		ChatMsgEntity entity = new ChatMsgEntity();
		entity.setName("公告信息");
		entity.setMessage("请各位家长注意我的通知，速回");
		messageList.add(entity);
		ChatMsgEntity entity2 = new ChatMsgEntity();
		entity2.setName("家庭作业助手");
		entity2.setMessage("8月5号家庭作业");
		messageList.add(entity2);
		ChatMsgEntity entity3 = new ChatMsgEntity();
		entity3.setName("成绩提醒助手");
		entity3.setMessage("考试成绩出来了，请到教务处网站查询");
		messageList.add(entity3);
		ChatMsgEntity entity4 = new ChatMsgEntity();
		entity4.setName("语音测评");
		entity4.setMessage("今天的语音测评");
		messageList.add(entity4);
		ChatMsgEntity entity5 = new ChatMsgEntity();
		entity5.setName("95611家长群");
		entity5.setMessage("今天真郁闷啊，儿子不作作业");
		messageList.add(entity5);
	}
	class MyAdapter extends BaseAdapter{
		Context ctx;
		public MyAdapter(Context ctx){
			this.ctx = ctx;
		}
		@Override
		public int getCount() {
			if (messageList==null) {
				return 0;
			}
			return messageList.size();
		}
		@Override
		public Object getItem(int position) {
			if (messageList==null) {
				return null;
			}
			return messageList.get(position);
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			ChatMsgEntity data = (ChatMsgEntity)getItem(position);
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
			
			viewHolder.nick.setText(data.getName());
			if (position==0) {
				viewHolder.avatar.setImageResource(R.drawable.msg_ggxx);
			}else if (position==1) {
				viewHolder.avatar.setImageResource(R.drawable.msg_zy);
			}else if (position==2) {
				viewHolder.avatar.setImageResource(R.drawable.msg_cjcx);
			}else if (position==3) {
				viewHolder.avatar.setImageResource(R.drawable.msg_yycp);
			}else {
				viewHolder.avatar.setImageResource(R.drawable.ic_launcher);
			}
			viewHolder.content.setText(data.getMessage());
			return convertView;
		}
		class ViewHolder{
			TextView nick;
			TextView content;
			ImageView avatar;
		}
	}
}
