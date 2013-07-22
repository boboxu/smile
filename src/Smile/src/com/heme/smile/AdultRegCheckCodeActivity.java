package com.heme.smile;

import com.heme.smile.common.Constans;
import com.heme.smile.R;
import com.heme.smile.R.layout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class AdultRegCheckCodeActivity extends BaseActivity {
	private static final String TAG = "AdultRegCheckCodeActivity";
	public static final String REG_PHONENUMBER = "reg_phonenumber";
	private String mPhoneNum;
	private TextView mTipTextView;
	private Button mResendBtn,mNextStepBtn;
	private EditText mCheckCode;
	private int mRemainTime = 60;
	//60秒内输入
	private int mMaxTime = 60;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.INPUT_CHECKCODE_COUNT_DOWN:
				mRemainTime--;
				if (mRemainTime>0) {
					mResendBtn.setVisibility(View.INVISIBLE);
					mNextStepBtn.setClickable(true);
					mTipTextView.setText(Html.fromHtml("<font color='white'>请输入手机收到的验证码:(</font><font color='red'>"+mRemainTime+"</font><font color='white'>秒内输入)</font>"),BufferType.SPANNABLE);
					sendEmptyMessageDelayed(Constans.INPUT_CHECKCODE_COUNT_DOWN, 1000);
				}else {
					mResendBtn.setVisibility(View.VISIBLE);
					mNextStepBtn.setClickable(false);
					mTipTextView.setTextColor(Color.WHITE);
					mTipTextView.setText("抱歉,由于你60秒内未完成验证，验证码已失效，请点击重新发送");
				}
				break;
			case Constans.SEND_REG_CHECK_CODE_SUCCESS:
				dismissDialog();
				Util.showToast(AdultRegCheckCodeActivity.this, "重新发送验证码成功");
				mRemainTime = 60;
				sendEmptyMessageDelayed(Constans.INPUT_CHECKCODE_COUNT_DOWN, 1000);
				break;
			case Constans.VERIFY_CHECKCODE_SUCCESS:
				dismissDialog();
				showWaitDialog("欢迎你加入微校.正为你自动登录中，请稍候");
				sendEmptyMessageDelayed(Constans.LOGIN_SUCCESS, 3000);
				break;
			case Constans.LOGIN_SUCCESS:
				dismissDialog();
				startActivity(new Intent(AdultRegCheckCodeActivity.this,MainActivity.class));
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
		mPhoneNum = getIntent().getStringExtra(REG_PHONENUMBER);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.adult_reg_checkcode);
		((TextView)findViewById(R.id.titleTextView)).setText("注册验证");
		((ImageView)findViewById(R.id.backImg)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AdultRegCheckCodeActivity.this.finish();
			}
		});
		mTipTextView = (TextView)findViewById(R.id.timecountdown);
		mTipTextView.setText(Html.fromHtml("<font color='white'>请输入手机收到的验证码:(</font><font color='red'>"+mRemainTime+"</font><font color='white'>秒内输入)</font>"),BufferType.SPANNABLE);
		mCheckCode = (EditText)findViewById(R.id.checkcode);
		mResendBtn = (Button)findViewById(R.id.resend);
		mNextStepBtn = (Button)findViewById(R.id.nextstep);
		mResendBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHandler.removeMessages(Constans.INPUT_CHECKCODE_COUNT_DOWN);  //去掉倒计时
				showWaitDialog("重新发送验证码中,请稍候");
				mHandler.sendEmptyMessageDelayed(Constans.SEND_REG_CHECK_CODE_SUCCESS, 3000);
			}
		});
		mNextStepBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mRemainTime<=0) {
					Util.showToast(AdultRegCheckCodeActivity.this, "该验证码已已失效，请点击重发按钮，重新发送验证码");
					return;
				}
				if (mCheckCode.getText().toString().trim().equals("")) {
					Util.showToast(AdultRegCheckCodeActivity.this, "请填写验证码");
					return;
				}
				showWaitDialog("验证中,请稍候...");
				mHandler.sendEmptyMessageDelayed(Constans.VERIFY_CHECKCODE_SUCCESS, 3000);
			}
		});
		mHandler.sendEmptyMessageDelayed(Constans.INPUT_CHECKCODE_COUNT_DOWN, 1000);
	}
}
