package com.heme.smile;

import com.heme.smile.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyMicroSchoolActivity extends BaseActivity implements OnClickListener{
	private static final String TAG= "MyMicroSchoolActivity";
	private RelativeLayout mEditSinagureLayout;
	private EditText mSinagureEditText;
	private Button mSinagureCancelBtn,mSinagureOkBtn;
	private TextView mSinagureTextView;
	private int mCurUserLoginState = 0;   //用户在线的状态 0在线 1离线 
	private TextView mUserState;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.mymicoschool);
		findViewById(R.id.editBtn).setOnClickListener(this);
		mEditSinagureLayout = (RelativeLayout)findViewById(R.id.edit_sinagure_rl);
		mSinagureCancelBtn = (Button)findViewById(R.id.cancel);
		mSinagureOkBtn = (Button)findViewById(R.id.ok);
		mSinagureCancelBtn.setOnClickListener(this);
		mSinagureOkBtn.setOnClickListener(this);
		mSinagureEditText = (EditText)findViewById(R.id.sinagure_content);
		mSinagureTextView = (TextView)findViewById(R.id.sinagure);
		findViewById(R.id.user_state_rl).setOnClickListener(this);
		mUserState = (TextView)findViewById(R.id.user_state);
	}
	private void updateUserState(){
		if (mCurUserLoginState==0) {
			mUserState.setText("在线");
			Util.showToast(MyMicroSchoolActivity.this, "当前状态: 在线");
		}else if (mCurUserLoginState==1) {
			mUserState.setText("离线");
			Util.showToast(MyMicroSchoolActivity.this, "当前状态: 离线");
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.editBtn:
			mEditSinagureLayout.setVisibility(View.VISIBLE);
			break;
		case R.id.cancel:
			mEditSinagureLayout.setVisibility(View.GONE);
			break;
		case R.id.ok:
			mEditSinagureLayout.setVisibility(View.GONE);
			Util.showToast(this, "个性签名修改成功");
			mSinagureTextView.setText(mSinagureEditText.getText().toString());
			break;
		case R.id.user_state_rl:
			if (mCurUserLoginState==0) {
				mCurUserLoginState = 1;
			}else {
				mCurUserLoginState = 0;
			}
			updateUserState();
			break;
		default:
			break;
		}
	}
	
}
