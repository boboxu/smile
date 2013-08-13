package com.heme.smile.ui.view;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heme.smile.R;
import com.heme.smile.SingleChatActivity;
import com.heme.smile.StartActivity;
import com.heme.smile.UserDetailsActivity;
import com.heme.smile.util.BiaoqingUtil;
import com.heme.smile.util.ChatMsgEntity;

public class ChatMsgViewAdapter extends BaseAdapter {
	private Context ctx;
	public static interface IMsgViewType {
		int IMVT_COM_MSG = 0;// 收到对方的消息
		int IMVT_TO_MSG = 1;// 自己发送出去的消息
	}
	private Handler handler;
	public void setHandler(Handler handler){
		this.handler = handler;
	}
	private static final int ITEMCOUNT = 2;// 消息类型的总数
	private List<ChatMsgEntity> coll;// 消息对象数组
	private LayoutInflater mInflater;

	public ChatMsgViewAdapter(Context context, List<ChatMsgEntity> coll) {
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
	 * 得到Item的类型，是对方发过来的消息，还是自己发送出去的
	 */
	public int getItemViewType(int position) {
		ChatMsgEntity entity = coll.get(position);

		if (entity.getMsgType()) {//收到的消息
			return IMsgViewType.IMVT_COM_MSG;
		} else {//自己发送的消息
			return IMsgViewType.IMVT_TO_MSG;
		}
	}

	/**
	 * Item类型的总数
	 */
	public int getViewTypeCount() {
		return ITEMCOUNT;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {

		final ChatMsgEntity entity = coll.get(position);
		boolean isComMsg = entity.getMsgType();

		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (isComMsg) {
				convertView = mInflater.inflate(
						R.layout.chatting_item_msg_text_left, null);
			} else {
				convertView = mInflater.inflate(
						R.layout.chatting_item_msg_text_right, null);
			}

			viewHolder = new ViewHolder();
			viewHolder.tvSendTime = (TextView) convertView
					.findViewById(R.id.tv_sendtime);
			viewHolder.tvUserName = (TextView) convertView
					.findViewById(R.id.tv_username);
			viewHolder.tvContent = (TextView) convertView
					.findViewById(R.id.tv_chatcontent);
			viewHolder.ivAvatar = (ImageView)convertView.findViewById(R.id.iv_userhead);
			viewHolder.isComMsg = isComMsg;

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvSendTime.setText(entity.getDate());
		viewHolder.tvUserName.setText(entity.getName());
		viewHolder.ivAvatar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ctx, UserDetailsActivity.class);
				intent.putExtra(SingleChatActivity.SINGLE_CHAT_NICKNAME, entity.getName());
				ctx.startActivity(intent);
			}
		});
		if (entity.getType()==0) {
			String str = entity.getMessage(); // 消息具体内容,如果是表情，则是f001 f002等，通过转换，显示为表情图片即可
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
		}else if (entity.getType()==1) {
			//语音消息
			SpannableString spannableString = BiaoqingUtil.getVoiceMessageExpressionString(ctx, "voice "+entity.getVoiceTime()+" 秒");
			viewHolder.tvContent.setText(spannableString);
			viewHolder.tvContent.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Message message = handler.obtainMessage(SingleChatActivity.PLAYER_VOICE_MESSAGE);
					message.arg1 = position;
					handler.sendMessage(message);
				}
			});
		}else if (entity.getType()==2) {
			Bitmap bitmap = BitmapFactory.decodeFile(entity.getMessage());
			ImageSpan imageSpan = new ImageSpan(bitmap);
			SpannableString spannableString = new SpannableString(entity.getMessage());
			spannableString.setSpan(imageSpan, 0, entity.getMessage().length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			viewHolder.tvContent.setText(spannableString);
			viewHolder.tvContent.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Message message = handler.obtainMessage(SingleChatActivity.SHOW_BIG_PICTURE);
					message.arg1 = position;
					handler.sendMessage(message);
				}
			});
		}else if (entity.getType()==3) {
			Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.video_default_150);
			ImageSpan imageSpan = new ImageSpan(bitmap);
			SpannableString spannableString = new SpannableString(entity.getMessage());
			spannableString.setSpan(imageSpan, 0, entity.getMessage().length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			viewHolder.tvContent.setText(spannableString);
			viewHolder.tvContent.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Message message = handler.obtainMessage(SingleChatActivity.OPEN_VIDEO_FILE);
					message.obj = entity.getMessage();
					handler.sendMessage(message);
				}
			});
		}else if (entity.getType()==4) {
			Bitmap bitmap = BitmapFactory.decodeFile(entity.getMessage());
			ImageSpan imageSpan = new ImageSpan(bitmap);
			SpannableString spannableString = new SpannableString(entity.getMessage());
			spannableString.setSpan(imageSpan, 0, entity.getMessage().length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			viewHolder.tvContent.setText(spannableString);
			viewHolder.tvContent.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Message message = handler.obtainMessage(SingleChatActivity.SHOW_CARD);
					message.obj = entity.getExternalContent();
					handler.sendMessage(message);
				}
			});
		}
		return convertView;
	}

	static class ViewHolder {
		public ImageView ivAvatar;
		public TextView tvSendTime;
		public TextView tvUserName;
		public TextView tvContent;
		public boolean isComMsg = true;
	}

}
