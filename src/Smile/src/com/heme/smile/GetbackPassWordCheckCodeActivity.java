package com.heme.smile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.heme.logic.common.Constans;
import com.heme.utils.Util;

public class GetbackPassWordCheckCodeActivity extends BaseActivity {
	public static final String PHONENUM = "phonenum";
	private TextView mPhoneNum;
	private EditText mCheckNum;
	private Button mNextStepBtn;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.VERIFY_CHECKCODE_SUCCESS:
				dismissDialog();
				showNewPassword("123456");
				break;
			case Constans.LOGIN_SUCCESS:
				dismissDialog();
				startActivity(new Intent(GetbackPassWordCheckCodeActivity.this, MainActivity.class));
				finish();
				break;
			default:
				break;
			}
		};
	};
	private void showNewPassword(String pwd){
		AlertDialog.Builder builder = new AlertDialog.Builder(GetbackPassWordCheckCodeActivity.this);
    	builder.setTitle("恭喜，验证通过");
    	builder.setMessage("请牢记你的新密码: "+pwd);
    	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showWaitDialog("登录中,请稍候...");
				//模拟登录
				mHandler.sendEmptyMessageDelayed(Constans.LOGIN_SUCCESS, 3000);
			}
		});
    	
    	builder.create().show();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.getbackpwd_checkcode);
		((TextView)findViewById(R.id.titleTextView)).setText("填写验证码");
		mPhoneNum = (TextView)findViewById(R.id.phonenum);
		mPhoneNum.setTextColor(Color.RED);
		mPhoneNum.setText(getIntent().getStringExtra(PHONENUM));
		mCheckNum = (EditText)findViewById(R.id.checkcode);
		mNextStepBtn = (Button)findViewById(R.id.nextstep);
		mNextStepBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mCheckNum.getText().toString().trim().equals("")) {
					Util.showToast(GetbackPassWordCheckCodeActivity.this, "请填写你收到的验证码");
					return;
				}
				//模拟取得服务器的新密码
				showWaitDialog("验证码校验中,请稍候...");
				mHandler.sendEmptyMessageDelayed(Constans.VERIFY_CHECKCODE_SUCCESS, 1500);
			}
		});
	}
}
