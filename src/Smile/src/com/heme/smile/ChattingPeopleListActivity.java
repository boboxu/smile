package com.heme.smile;

import java.util.LinkedList;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.heme.utils.Util;

/**
 * 获取正在聊天的好友列表，点击某个人的头像查看他的详细资料
 * @author Administrator
 *
 */
public class ChattingPeopleListActivity extends BaseActivity {
	private static final String TAG = "ChattingFriendListActivity";
	private String mChaterName = "";
	private GridView mGridView;
	private LinkedList<ChattingPeople> mChattingPeopleList = new LinkedList<ChattingPeopleListActivity.ChattingPeople>();
	private ChattingPeopleListAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mChaterName = getIntent().getStringExtra(SingleChatActivity.SINGLE_CHAT_NICKNAME);
		super.onCreate(savedInstanceState);
		initData();
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.chattingfriendlist);
		findViewById(R.id.backImg).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChattingPeopleListActivity.this.finish();
			}
		});
		((TextView)findViewById(R.id.titleTextView)).setText("联系人信息");
		mGridView = (GridView)findViewById(R.id.chatting_people_grid);
		mAdapter = new ChattingPeopleListAdapter(ChattingPeopleListActivity.this);
		mGridView.setAdapter(mAdapter);
		mGridView.setNumColumns(3);
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ChattingPeople entity = mChattingPeopleList.get(arg2);
				if (entity!=null) {
					if (entity.id==-1) {
						Util.showToast(ChattingPeopleListActivity.this, "添加新联系人");
					}else {
						Intent intent = new Intent(ChattingPeopleListActivity.this,UserDetailsActivity.class);
						intent.putExtra(SingleChatActivity.SINGLE_CHAT_NICKNAME, mChaterName);
						startActivity(intent);
					}
				}
			}
		});
	}
	private void initData(){
		ChattingPeople entity1 = new ChattingPeople();
		entity1.nickname = mChaterName;
		entity1.avatarPath = "";
		mChattingPeopleList.add(entity1);
		ChattingPeople entity2 = new ChattingPeople();
		entity2.nickname = "";
		entity2.avatarPath = "";
		entity2.id = -1;
		mChattingPeopleList.add(entity2);
	}
	class ChattingPeopleListAdapter extends BaseAdapter {
		private Context ctx;
		
		public ChattingPeopleListAdapter(Context context){
			this.ctx = context;
		}
		@Override
		public int getCount() {
			if (mChattingPeopleList==null) {
				return 0;
			}
			return mChattingPeopleList.size();
		}

		@Override
		public Object getItem(int arg0) {
			if (mChattingPeopleList==null) {
				return null;
			}
			return mChattingPeopleList.get(arg0);
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
				convertView = LayoutInflater.from(this.ctx).inflate(R.layout.chattingpeopleitem, null);
				viewHolder = new ViewHolder();
				viewHolder.tvNickname = (TextView)convertView.findViewById(R.id.nickname);
				viewHolder.ivAvatar = (ImageView)convertView.findViewById(R.id.avatar);
				convertView.setTag(viewHolder);
			}else {
				viewHolder = (ViewHolder)convertView.getTag();
			}
			ChattingPeople entity = mChattingPeopleList.get(position);
			if (entity.id==-1) {
				viewHolder.tvNickname.setText("");
				viewHolder.ivAvatar.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
			}else {
				viewHolder.tvNickname.setText(entity.nickname);
				if (entity.avatarPath!=null&&!entity.avatarPath.trim().equals("")) {
					viewHolder.ivAvatar.setImageBitmap(BitmapFactory.decodeFile(entity.avatarPath));
				}else {
					viewHolder.ivAvatar.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
				}
			}
			return convertView;
		}
	}
	class ViewHolder {
		public TextView tvNickname;
		public ImageView ivAvatar;
	}
	class ChattingPeople{
		String avatarPath = "";
		String nickname = "";
		int id;
	}
}
