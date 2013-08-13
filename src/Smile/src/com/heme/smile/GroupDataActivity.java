package com.heme.smile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

//群资料界面 
public class GroupDataActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "GroupDataActivity";
	private Button mSendMsgBtn;
	private String mGroupInfoId;
	private TextView mGroupIdView,mGroupMasterView,mGroupIntrodueView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mGroupInfoId = getIntent().getStringExtra(GroupChatActivity.GROUP_ID);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.groupdata);
		findViewById(R.id.backImg).setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("群资料");
		mSendMsgBtn = (Button)findViewById(R.id.send_msg_btn);
		mSendMsgBtn.setOnClickListener(this);
		mGroupIdView = (TextView)findViewById(R.id.group_id);
		mGroupIdView.setText("群号:7423326");
		mGroupMasterView = (TextView)findViewById(R.id.group_master);
		mGroupMasterView.setText("我是你鹏哥(班主任)");
		mGroupIntrodueView = (TextView)findViewById(R.id.group_introduce);
		mGroupIntrodueView.setText("我们群是一个很好玩的群，吃喝玩乐，样样精彩。大家快来加入呀~~~");
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backImg:
			GroupDataActivity.this.finish();
			break;
		case R.id.send_msg_btn:
			Intent intent = new Intent(GroupDataActivity.this, GroupChatActivity.class);
			intent.putExtra(GroupChatActivity.GROUP_ID, mGroupInfoId);
			startActivity(intent);
			this.finish();
			break;
		default:
			break;
		}
	}
}
