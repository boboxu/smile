package com.heme.smile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UserDetailsActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "ChattingFriendListActivity";
	private Button mSendMsgButton;
	private String mCharterName;
	private TextView mNickName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mCharterName = getIntent().getStringExtra(SingleChatActivity.SINGLE_CHAT_NICKNAME);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.userdetails);
		mNickName = (TextView)findViewById(R.id.nickname);
		mNickName.setText(mCharterName);
		findViewById(R.id.backImg).setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("详细资料");
		mSendMsgButton = (Button)findViewById(R.id.send_msg_btn);
		mSendMsgButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.backImg:
			finish();
			break;
		case R.id.send_msg_btn:
			if (mCharterName!=null) {
				Intent intent = new Intent(UserDetailsActivity.this, SingleChatActivity.class);
				intent.putExtra(SingleChatActivity.SINGLE_CHAT_NICKNAME, mCharterName);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}
}
