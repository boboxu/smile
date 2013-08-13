package com.heme.smile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.heme.logic.common.Constans;
import com.heme.utils.Util;

//群信息界面
public class GroupInfoActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "GroupDetailActivity";
	private PopupWindow mPopupWindow;
	private View mUserInfoView;
	private Button mSinagureCancelBtn,mSinagureOkBtn;
	private EditText mSinagureEditText;
	private TextView mGroupNickname;
	private ToggleButton mSpeakerSoundButton;  //扬声器声音开关
	private String mGroupInfoId;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.CLEAR_CHAT_HISTORY_SUCCESS:
				dismissDialog();
				Util.showToast(GroupInfoActivity.this, "恭喜,清空聊天记录成功");
				break;
			case Constans.DEL_GROUP_SUCCESS:
				dismissDialog();
				Util.showToast(GroupInfoActivity.this, "你已成功退出该群");
				GroupInfoActivity.this.finish();
				break;
			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mGroupInfoId = getIntent().getStringExtra(GroupChatActivity.GROUP_ID);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.groupinfo);
		mGroupNickname = (TextView)findViewById(R.id.groupnickname);
		findViewById(R.id.backImg).setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("群信息");
		findViewById(R.id.userinfo).setOnClickListener(this);
		mUserInfoView = LayoutInflater.from(this).inflate(R.layout.sinagureeditview, null);
		mPopupWindow = new PopupWindow(mUserInfoView, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, true);
		mSinagureCancelBtn = (Button)mUserInfoView.findViewById(R.id.cancel);
		mSinagureOkBtn = (Button)mUserInfoView.findViewById(R.id.ok);
		mSinagureCancelBtn.setOnClickListener(this);
		mSinagureOkBtn.setOnClickListener(this);
		mSinagureEditText = (EditText)mUserInfoView.findViewById(R.id.sinagure_content);
		findViewById(R.id.clear_chat_history).setOnClickListener(this);
		mSpeakerSoundButton = (ToggleButton)findViewById(R.id.speakersoundbtn);
		mSpeakerSoundButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Util.showToast(GroupInfoActivity.this, "扬声器播放语音 已打开");
				}else {
					Util.showToast(GroupInfoActivity.this, "扬声器播放语音 已关闭");
				}
			}
		});
		findViewById(R.id.quit_group).setOnClickListener(this);
		findViewById(R.id.group_data).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.userinfo:
			mSinagureEditText.setText(mGroupNickname.getText());
			mPopupWindow.setOutsideTouchable(true);
			mPopupWindow.showAtLocation(findViewById(R.id.parent_layout), Gravity.CENTER, 0, 0);
			mPopupWindow.update();
			break;
		case R.id.ok:
			if (mPopupWindow!=null&&mPopupWindow.isShowing()) {
				mPopupWindow.dismiss();
			}
			mGroupNickname.setText(mSinagureEditText.getText().toString());
			mSinagureEditText.setText("");
			break;
		case R.id.clear_chat_history:
			showAlertDialog("提示", "确定清空所有聊天记录吗?", "确定", "取消", new ClearChatHistoryListener(), null);
			break;
		case R.id.backImg:
			finish();
			break;
		case R.id.quit_group:
			showAlertDialog("提示", "确定退出该群吗?", "确定", "取消", new QuitGroupListener(), null);
			break;
		case R.id.group_data:
			Intent dataIntent = new Intent(GroupInfoActivity.this, GroupDataActivity.class);
			dataIntent.putExtra(GroupChatActivity.GROUP_ID, mGroupInfoId);
			startActivity(dataIntent);
			break;
		default:
			break;
		}
	}
	class QuitGroupListener implements LeftButtonclickListener{
		@Override
		public void onLeftBtnClick() {
			// TODO Auto-generated method stub
			//执行清空聊天记录的逻辑
			showWaitDialog("正在向服务器发送退群请求,请稍候");
			
			mHandler.sendEmptyMessageDelayed(Constans.DEL_GROUP_SUCCESS, 1000);
		}
	}
	class ClearChatHistoryListener implements LeftButtonclickListener{
		@Override
		public void onLeftBtnClick() {
			// TODO Auto-generated method stub
			//执行清空聊天记录的逻辑
			showWaitDialog("正在清空聊天记录,请稍候");
			
			mHandler.sendEmptyMessageDelayed(Constans.CLEAR_CHAT_HISTORY_SUCCESS, 3000);
		}
	}
}
