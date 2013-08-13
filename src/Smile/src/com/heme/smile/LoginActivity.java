package com.heme.smile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.heme.logic.common.Configuration;
import com.heme.logic.common.Constans;
import com.heme.utils.Util;

public class LoginActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "LoginActivity";
	private long mExitTime = 0;
	private CheckBox mAgreeCheckBox;
	private TextView mRuleTextView;
	private EditText mUserName,mPwd;
	private Button mLoginBtn,mRegBtn,mForgetPwdBtn;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.LOGIN_SUCCESS:
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		initUI();
		super.onCreate(savedInstanceState);
		addIconToStatusbar(R.drawable.ic_launcher);
	}
	private void initUI(){
		setContentView(R.layout.login);
		findViewById(R.id.backImg).setOnClickListener(this);
		mAgreeCheckBox = (CheckBox)findViewById(R.id.agree_checkbox);
		mRuleTextView = (TextView)findViewById(R.id.rule);
		mRuleTextView.setOnClickListener(this);
		mUserName = (EditText)findViewById(R.id.username_edittext);
		mPwd = (EditText)findViewById(R.id.pwd_edittext);
		mLoginBtn = (Button)findViewById(R.id.login);
		mLoginBtn.setOnClickListener(this);
		mRegBtn = (Button)findViewById(R.id.regBtn);
		if (Configuration.APP_VERSION==0) {
			mRegBtn.setText("家长注册");
		}else if (Configuration.APP_VERSION==1) {
			mRegBtn.setText("学生注册");
		}else if (Configuration.APP_VERSION==2) {
			mRegBtn.setText("老师注册");
		}
		mRegBtn.setOnClickListener(this);
		mForgetPwdBtn = (Button)findViewById(R.id.forgetPwdBtn);
		mForgetPwdBtn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backImg:
			backToDesk();
			break;
		case R.id.rule:
			startActivity(new Intent(this, UserRuleActivity.class));
			break;
		case R.id.login:
			Log.i(TAG, "mUserName.getText().toString().trim() = "+mUserName.getText().toString().trim() +",mPwd.getText().toString().trim()="+mPwd.getText().toString().trim());
			if (mUserName.getText().toString().trim().equals("")||mPwd.getText().toString().trim().equals("")) {
				Util.showToast(this, "请填写帐号和密码");
				return;
			}
			if (!mAgreeCheckBox.isChecked()) {
				Util.showToast(this, "请勾选 '同意使用条款和隐私政策'");
				return;
			}
			
			showWaitDialog("登录中,请稍候...");
			//模拟成功登录
			mHandler.sendEmptyMessageDelayed(Constans.LOGIN_SUCCESS, 3000);
			break;
		case R.id.regBtn:
			Intent intent = new Intent();
			if (Configuration.APP_VERSION==0) {
				//家长版
				intent.setClass(LoginActivity.this, AdultRegActivity.class);
			}else if (Configuration.APP_VERSION==1) {
				//学生版
				intent.setClass(LoginActivity.this, StudentRegActivity.class);
			}
			startActivity(intent);
			break;
		case R.id.forgetPwdBtn:
			startActivity(new Intent(this, GetbackPassword.class));
			break;
		default:
			break;
		}
	}
	
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
		return super.onKeyDown(keyCode, event);
	}
}
