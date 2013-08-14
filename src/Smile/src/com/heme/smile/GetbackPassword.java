package com.heme.smile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.heme.utils.Util;

public class GetbackPassword extends BaseActivity implements OnClickListener{
	private CheckBox mAgreeCheckBox;
	private TextView mRuleTextView;
	private EditText mPhoneNum;
	private Button mNextStep;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}
	private void initUI(){
		setContentView(R.layout.getbackpwd_writephonenum);
		((TextView)findViewById(R.id.titleTextView)).setText("填写手机号");
		mAgreeCheckBox = (CheckBox)findViewById(R.id.agree_checkbox);
		mRuleTextView = (TextView)findViewById(R.id.rule);
		mRuleTextView.setOnClickListener(this);
		mPhoneNum = (EditText)findViewById(R.id.phonenum);
		mNextStep = (Button)findViewById(R.id.nextstep);
		mNextStep.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rule:
			startActivity(new Intent(this, UserRuleActivity.class));
			break;
		case R.id.nextstep:
			if (mPhoneNum.getText().toString().trim().equals("")) {
				Util.showToast(this, "请填写手机号");
				return;
			}
			if (!mAgreeCheckBox.isChecked()) {
				Util.showToast(this, "请勾选 '同意使用条款和隐私政策'");
				return;
			}
			Intent intent = new Intent(GetbackPassword.this,GetbackPassWordCheckCodeActivity.class);
			intent.putExtra(GetbackPassWordCheckCodeActivity.PHONENUM, mPhoneNum.getText().toString().trim());
			startActivity(intent);
			finish();
			break;
		}
	}
}
