package com.heme.smile;

import java.util.LinkedList;
import java.util.List;

import com.heme.smile.ui.view.ChatMsgViewAdapter.IMsgViewType;
import com.heme.smile.util.BiaoqingUtil;
import com.heme.smile.util.ChatMsgEntity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NotificationActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "NotificationActivity";
	private ListView mListView;
	private MyAdapter mAdapter;
	private LinkedList<NotificationData> mNotifications = new LinkedList<NotificationActivity.NotificationData>();
	
	private void emulateData(){
		NotificationData data = new NotificationData();
		data.date = "2013年08月09日 1:10:09";
		data.name = "陈冠西老师";
		data.message = "请各位小伙伴们注意我的通知，速回";
		mNotifications.add(data);
		NotificationData data2 = new NotificationData();
		data2.date = "2013年08月07日 23:10:09";
		data2.name = "陈冠西老师";
		data2.message = "我和我的小伙伴们都吓尿了";
		mNotifications.add(data2);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		emulateData();
		initUI();
	}
	
	private void initUI(){
		setContentView(R.layout.notification);
		findViewById(R.id.backImg).setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("公告信息");
		mListView = (ListView)findViewById(R.id.listview);
		mAdapter = new MyAdapter(this, mNotifications);
		mListView.setAdapter(mAdapter);
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
	class NotificationData{
		private String name;//消息来自
		private String date;//消息日期
		private String message;//消息内容
	}
	class MyAdapter extends BaseAdapter{
		private Context ctx;
		private Handler handler;
		public void setHandler(Handler handler){
			this.handler = handler;
		}
		private static final int ITEMCOUNT = 2;// 消息类型的总数
		private List<NotificationData> coll;// 消息对象数组
		private LayoutInflater mInflater;

		public MyAdapter(Context context, List<NotificationData> coll) {
			this.ctx = context;
			this.coll = coll;
			mInflater = LayoutInflater.from(context);
		}

		public int getCount() {
			return coll.size();
		}

		public Object getItem(int position) {
			return coll.get(position);
		}

		public long getItemId(int position) {
			return position;
		}


		/**
		 * Item类型的总数
		 */
		public int getViewTypeCount() {
			return ITEMCOUNT;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {

			final NotificationData entity = coll.get(position);

			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.chatting_item_msg_text_left, null);
				viewHolder = new ViewHolder();
				viewHolder.tvSendTime = (TextView) convertView
						.findViewById(R.id.tv_sendtime);
				viewHolder.tvUserName = (TextView) convertView
						.findViewById(R.id.tv_username);
				viewHolder.tvContent = (TextView) convertView
						.findViewById(R.id.tv_chatcontent);
				viewHolder.ivAvatar = (ImageView)convertView.findViewById(R.id.iv_userhead);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.tvSendTime.setText(entity.date);
			viewHolder.tvUserName.setText(entity.name);
			viewHolder.tvUserName.setTextColor(Color.WHITE);
			viewHolder.ivAvatar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(ctx, UserDetailsActivity.class);
					intent.putExtra(SingleChatActivity.SINGLE_CHAT_NICKNAME, entity.name);
					ctx.startActivity(intent);
				}
			});
				String str = entity.message; // 消息具体内容,如果是表情，则是f001 f002等，通过转换，显示为表情图片即可
				String zhengze = "f0[0-9]{2}|f10[0-7]"; // 正则表达式，用来判断消息内是否有表情
				try {
					SpannableString urlSpannableString = BiaoqingUtil.getExpressionStringWithUrl(ctx, str, "http://[^ ^,^!^;^`^~^\n^，^！^；]*");
					SpannableString spannableString = BiaoqingUtil
							.getExpressionString(ctx, str, zhengze,"http://[^ ^,^!^;^`^~^\n^，^！^；]*");
					viewHolder.tvContent.setText(spannableString);
					viewHolder.tvContent.setMovementMethod(LinkMovementMethod.getInstance());
				} catch (Exception e) {
					e.printStackTrace();
				}
			return convertView;
		}

		class ViewHolder {
			public ImageView ivAvatar;
			public TextView tvSendTime;
			public TextView tvUserName;
			public TextView tvContent;
		}
	}
	
}
