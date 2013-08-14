package com.heme.smile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//发送名片的确认界面
public class SendBusinessCardConfirmActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "SendBusinessCardConfirmActivity";
	private ImageView mAvatarImg,mToAvatarImg;
	private TextView mNickname,mUserIdTextView,mToNickname,mToUserIdTextView;
	public static final String SELECT_CARD_ID = "select_card_id";
	//先模拟
	private String mSelectCardName = "";
	private Button mSendBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mSelectCardName = getIntent().getStringExtra(SELECT_CARD_ID);
		initUI();
		findViewById(R.id.backImg).setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("推荐给朋友");
	}
	private void initUI(){
		setContentView(R.layout.sendbusinesscardconfirm);
		mSendBtn = (Button)findViewById(R.id.rightBtn);
		mSendBtn.setVisibility(View.VISIBLE);
		mSendBtn.setText("发送");
		mSendBtn.setOnClickListener(this);
		mAvatarImg =  (ImageView)findViewById(R.id.avatar);
		mNickname = (TextView)findViewById(R.id.nickname);
		mUserIdTextView = (TextView)findViewById(R.id.userid);
		mNickname.setText(mSelectCardName);
		mUserIdTextView.setText("微校号:4323232");
		mAvatarImg.setImageResource(R.drawable.ic_launcher);
		
		mToAvatarImg = (ImageView)findViewById(R.id.toavatar);
		mToNickname = (TextView)findViewById(R.id.tonickname);
		mToUserIdTextView = (TextView)findViewById(R.id.touserid);
		mToAvatarImg.setImageResource(R.drawable.ic_launcher);
		mToNickname.setText("xxx");
		mToUserIdTextView.setText("459984");
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backImg:
			this.finish();
			break;
		case R.id.rightBtn:
			Intent intent = new Intent();
			intent.putExtra(SingleChatActivity.CARD_NICKNAME, mSelectCardName);
			intent.putExtra(SingleChatActivity.CARD_USERID, "4323232");
			setResult(SingleChatActivity.REQUEST_CARD,intent);
			finish();
			break;
		default:
			break;
		}
	}
	
	
}
