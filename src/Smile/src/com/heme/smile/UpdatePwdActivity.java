package com.heme.smile;

import com.heme.smile.common.Constans;
import com.heme.smile.R;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UpdatePwdActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "UpdatePwdActivity";
	private EditText mCurPwd,mNewPwd,mConfirmPwd;
	private Button mOkBtn,mCancelBtn;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.UPDATE_PWD_SUCCESS:
				dismissDialog();
				Util.showToast(UpdatePwdActivity.this, "修改密码成功");
				finish();
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
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.updatepwd);
		((TextView)findViewById(R.id.titleTextView)).setText("修改密码");
		((ImageView)findViewById(R.id.backImg)).setOnClickListener(this);
		mCurPwd = (EditText)findViewById(R.id.current_pwd);
		mNewPwd = (EditText)findViewById(R.id.new_pwd);
		mConfirmPwd = (EditText)findViewById(R.id.confirm_new_pwd);
		mOkBtn = (Button)findViewById(R.id.ok);
		mOkBtn.setOnClickListener(this);
		mCancelBtn = (Button)findViewById(R.id.cancel);
		mCancelBtn.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backImg:
			finish();
			break;
		case R.id.ok:
			if (mCurPwd.getText().toString().trim().equals("")||mNewPwd.getText().toString().trim().equals("")||mConfirmPwd.getText().toString().trim().equals("")) {
				Util.showToast(this, "密码不能为空");
				return;
			}
			if (mCurPwd.getText().toString().equals(mNewPwd.getText().toString())) {
				Util.showToast(this, "新密码不能跟旧密码相同");
				return;
			}
			if (!mNewPwd.getText().toString().equals(mConfirmPwd.getText().toString())) {
				Util.showToast(this, "两次输入的新密码必须相同");
				return;
			}
			showWaitDialog("修改密码中,请稍候...");
			mHandler.sendEmptyMessageDelayed(Constans.UPDATE_PWD_SUCCESS, 3000);
			break;
		default:
			break;
		}
	}
}
