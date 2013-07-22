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

public class HelpActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "HelpActivity";
	private EditText mContent;
	private Button mCancel,mConfirm;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.SUBMIT_FEEDBACK_SUCCESS:
				dismissDialog();
				Util.showToast(HelpActivity.this, "反馈意见提交成功");
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
		setContentView(R.layout.help);
		((ImageView)findViewById(R.id.backImg)).setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("意见反馈");
		mContent = (EditText)findViewById(R.id.feedback_content);
		mCancel = (Button)findViewById(R.id.cancel);
		mConfirm = (Button)findViewById(R.id.submit);
		mCancel.setOnClickListener(this);
		mConfirm.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backImg:
			finish();
			break;
		case R.id.cancel:
			finish();
			break;
		case R.id.submit:
			showWaitDialog("提交中,请稍候...");
			mHandler.sendEmptyMessageDelayed(Constans.SUBMIT_FEEDBACK_SUCCESS, 3000);
			break;

		default:
			break;
		}
	}
}
