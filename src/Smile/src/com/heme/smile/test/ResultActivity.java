package com.heme.smile.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.heme.logic.module.Message.CommonMsg;
import com.heme.logic.module.Message.PollMsgRes;
import com.heme.smile.BaseActivity;
import com.heme.smile.R;

public class ResultActivity extends BaseActivity {
	ListView mListView;
	TextView mTextView;
	private static List<String> mMsgList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitytestresult);
		mTextView = (TextView) findViewById(R.id.textrst);

		Intent intent = getIntent();
		Object dataObject = intent.getExtras().get("result");
		if (dataObject instanceof PollMsgRes) {
			List<CommonMsg> msglist = ((PollMsgRes) dataObject)
					.getRptMsgPollmsgList();
			String strFormat = "%d%d到%d的%s信息%s";
			for (int i = 0; i < msglist.size(); i++) {
				CommonMsg msgItem = msglist.get(i);
				switch (msgItem.getUint32ContentType()) {
				case CT_Text:
					mMsgList.add(String.format(strFormat,i,
							msgItem.getUint64FromUid(),
							msgItem.getUint64ToUid(0), "文本",
							msgItem.getStrTextMsg()));
					break;
				case CT_Picture:
					mMsgList.add(String.format(strFormat, i,msgItem
							.getUint64FromUid(), msgItem.getUint64ToUid(0),
							"图片", msgItem.getMsgPicMsg().getStrPicUrl()));
					break;
				case CT_File:
					mMsgList.add(String.format(strFormat, i,msgItem
							.getUint64FromUid(), msgItem.getUint64ToUid(0),
							"文件", msgItem.getMsgFileInfo().getStrFileUrl()));
					break;
				case CT_Voice:
					mMsgList.add(String.format(strFormat, i,msgItem
							.getUint64FromUid(), msgItem.getUint64ToUid(0),
							"声音", msgItem.getMsgVoiceMsg().getStrVoiceUrl()));
					break;
				case CT_Video:
					mMsgList.add(String.format(strFormat, i,msgItem
							.getUint64FromUid(), msgItem.getUint64ToUid(0),
							"视频", msgItem.getMsgVideoMsg().getStrVideoUrl()));
					break;
				case CT_IDCard:
					mMsgList.add(String.format(strFormat,i,
							msgItem.getUint64FromUid(),
							msgItem.getUint64ToUid(0), "名片",
							msgItem.getStrTextMsg()));
					break;
				default:
					break;
				}
			}
			strFormat = ((PollMsgRes) dataObject).getUint32CompleteFlag() == 0?"未读完有%d条消息":"已拉完有%d条消息";
			mTextView.setText(String.format(strFormat, msglist.size()));
		}

		mListView = (ListView) findViewById(R.id.lvrst);

		mListView.setAdapter(new LvAdapter(ResultActivity.this));

	}

	static class LvAdapter extends BaseAdapter implements SectionIndexer {
		private Context mContext;

		@SuppressWarnings("unchecked")
		public LvAdapter(Context mContext) {
			this.mContext = mContext;
		}

		@Override
		public int getCount() {
			return mMsgList.size();
		}

		@Override
		public Object getItem(int position) {
			return mMsgList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final String nickName = mMsgList.get(position);
			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.activitytestresultitem, null);
				viewHolder = new ViewHolder();
				viewHolder.tvNick = (TextView) convertView
						.findViewById(R.id.textView1);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.tvNick.setText(nickName);
			return convertView;
		}

		static class ViewHolder {
			TextView tvCatalog;// 目录
			ImageView ivAvatar;// 头像
			TextView tvNick;// 昵称
		}

		@Override
		public int getSectionForPosition(int position) {
			return 0;
		}

		@Override
		public Object[] getSections() {
			return null;
		}

		@Override
		public int getPositionForSection(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
