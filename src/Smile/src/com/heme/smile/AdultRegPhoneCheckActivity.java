package com.heme.smile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.module.Data.RegParentRsp;

public class AdultRegPhoneCheckActivity extends BaseActivity {
	public static String ACTIONREG = "ACTIONREG";
	public static String ACTIONFINDPWD = "ACTIONFINDPWD";
	private static final String TAG = "AdultRegPhoneCheckActivity";
	private Button mNextStepBtn;
	private EditText mPhoneNum;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.SEND_REG_CHECK_CODE_SUCCESS:
				dismissDialog();
				Intent intent = new Intent(AdultRegPhoneCheckActivity.this, AdultRegCheckCodeActivity.class);
				intent.putExtra(AdultRegCheckCodeActivity.REG_PHONENUMBER, mPhoneNum.getText().toString().trim());
				startActivity(intent);
				break;
			case Constans.ADULT_REG_FAILED:
				dismissDialog();
				RegParentRsp resp = (RegParentRsp)msg.obj;
				if (resp != null) 
				{
					Util.showToast(AdultRegPhoneCheckActivity.this, resp.getErrString());
				}
				else
				{
					Util.showToast(AdultRegPhoneCheckActivity.this, "注册失败");
				}
				break;
			case Constans.ADULT_REG_SUCCESS:
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
		setContentView(R.layout.adultregphonecheck);
		((TextView)findViewById(R.id.titleTextView)).setText("手机验证");
		((ImageView)findViewById(R.id.backImg)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AdultRegPhoneCheckActivity.this.finish();
			}
		});
		mPhoneNum = (EditText)findViewById(R.id.phone);
		mNextStepBtn = (Button)findViewById(R.id.nextstep);
		mNextStepBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mPhoneNum.getText().toString().trim().equals("")) {
					Util.showToast(AdultRegPhoneCheckActivity.this, "请输入你的手机号");
					return;
				}
//				showWaitDialog("验证码发送中，请注意查收");
				showWaitDialog("注册中,请稍候...");
//				mHandler.sendEmptyMessageDelayed(Constans.SEND_REG_CHECK_CODE_SUCCESS, 3000);
				LogicManager.registManager().setPhoneNum(mPhoneNum.getText().toString());
				LogicManager.registManager().startReg("", mHandler);
			}
		});
	}
}
