package com.heme.smile;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.userinfo.updateuserinfo.UpdateUserInfoRequest.SEXTYPE;
import com.heme.logic.module.Data.ClassCombine;
import com.heme.logic.module.Data.SchoolCombine;
import com.heme.logic.module.notpbmessage.AreaInfo;

public class AdultRegActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = "AdultRegActivity";
	private RelativeLayout mLayout1, mLayout2, mLayout3, mLayout4, mLayout5;
	private EditText mUsername, mIdnumber, mPwd, mConfirmPwd;
	private EditText mChildId1, mChildId2, mChildId3, mChildId4, mChildId5;
	private Button mAddBtn1, mAddBtn2, mAddBtn3, mAddBtn4;
	private Button mNextStepBtn;
	private CheckBox mCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			default:
				break;
			}
		};
	};

	private void initUI() {
		setContentView(R.layout.adultreg);
		((TextView) findViewById(R.id.titleTextView)).setText("家长注册");
		((ImageView) findViewById(R.id.backImg))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						AdultRegActivity.this.finish();
					}
				});
		mCheckBox = (CheckBox) findViewById(R.id.agree_checkbox);
		mLayout1 = (RelativeLayout) findViewById(R.id.child_id_rl1);
		mLayout2 = (RelativeLayout) findViewById(R.id.child_id_rl2);
		mLayout3 = (RelativeLayout) findViewById(R.id.child_id_rl3);
		mLayout4 = (RelativeLayout) findViewById(R.id.child_id_rl4);
		mLayout5 = (RelativeLayout) findViewById(R.id.child_id_rl5);
		mUsername = (EditText) findViewById(R.id.username_edittext);
		mIdnumber = (EditText) findViewById(R.id.id_number_edittext);
		mPwd = (EditText) findViewById(R.id.pwd_edittext);
		mConfirmPwd = (EditText) findViewById(R.id.confirm_pwd_edittext);

		mChildId1 = (EditText) findViewById(R.id.child_id1);
		mChildId2 = (EditText) findViewById(R.id.child_id2);
		mChildId3 = (EditText) findViewById(R.id.child_id3);
		mChildId4 = (EditText) findViewById(R.id.child_id4);
		mChildId5 = (EditText) findViewById(R.id.child_id5);

		mAddBtn1 = (Button) findViewById(R.id.add1);
		mAddBtn1.setOnClickListener(this);
		mAddBtn2 = (Button) findViewById(R.id.add2);
		mAddBtn2.setOnClickListener(this);
		mAddBtn3 = (Button) findViewById(R.id.add3);
		mAddBtn3.setOnClickListener(this);
		mAddBtn4 = (Button) findViewById(R.id.add4);
		mAddBtn4.setOnClickListener(this);

		mNextStepBtn = (Button) findViewById(R.id.nextstep);
		mNextStepBtn.setOnClickListener(this);
	}

	// 组装小孩ID号
	private List<String> getChildId() {
		List<String> list = new ArrayList<String>();
		if (mChildId1.getVisibility() == View.VISIBLE) {
			list.add(mChildId1.getText().toString().trim());
		}
		if (mChildId2.getVisibility() == View.VISIBLE) {
			list.add(mChildId2.getText().toString().trim());
		}
		if (mChildId3.getVisibility() == View.VISIBLE) {
			list.add(mChildId3.getText().toString().trim());
		}
		if (mChildId4.getVisibility() == View.VISIBLE) {
			list.add(mChildId4.getText().toString().trim());
		}
		if (mChildId5.getVisibility() == View.VISIBLE) {
			list.add(mChildId5.getText().toString().trim());
		}
		return list;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add1:
			mLayout2.setVisibility(View.VISIBLE);
			mAddBtn1.setVisibility(View.INVISIBLE);
			break;
		case R.id.add2:
			mLayout3.setVisibility(View.VISIBLE);
			mAddBtn2.setVisibility(View.INVISIBLE);
			break;
		case R.id.add3:
			mLayout4.setVisibility(View.VISIBLE);
			mAddBtn3.setVisibility(View.INVISIBLE);
			break;
		case R.id.add4:
			mLayout5.setVisibility(View.VISIBLE);
			mAddBtn4.setVisibility(View.INVISIBLE);
			break;
		case R.id.nextstep:
//			if (mUsername.getText().toString().trim().equals("")
//					|| mIdnumber.getText().toString().trim().equals("")
//					|| mPwd.getText().toString().trim().equals("")
//					|| mConfirmPwd.getText().toString().trim().equals("")) {
//				Util.showToast(AdultRegActivity.this, "帐户密码身份证信息不能为空");
//				return;
//			}
//			if (mIdnumber.getText().toString().trim().length() != 18) {
//				Util.showToast(AdultRegActivity.this, "身份证号必须为18位");
//				return;
//			}
//			List<String> childIds = getChildId();
//			if (childIds.size() == 0) {
//				Util.showToast(AdultRegActivity.this, "小孩ID不能为空");
//				return;
//			}
//			if (!mCheckBox.isChecked()) {
//				Util.showToast(AdultRegActivity.this, "请勾选同意使用条款");
//				return;
//			}
//			if (!mPwd.getText().toString()
//					.equals(mConfirmPwd.getText().toString())) {
//				Util.showToast(AdultRegActivity.this, "两次输入的密码不一致");
//				return;
//			}
//			if (mPwd.getText().toString().trim().length() < 6
//					|| mConfirmPwd.getText().toString().trim().length() < 6) {
//				Util.showToast(AdultRegActivity.this, "密码最少为6位");
//				return;
//			}
//			showWaitDialog("注册中,请稍候...");
			// 模拟服务器注册
			// mHandler.sendEmptyMessageDelayed(Constans.ADULT_REG_SUCCESS,
			// 3000);
//			LogicManager.registManager().setParRegInfo("123456789",
//					mUsername.getText().toString(),
//					mIdnumber.getText().toString(), mPwd.getText().toString(),
//					childIds);

			SchoolCombine.Builder schoolCombineBuilder = SchoolCombine.newBuilder();
			schoolCombineBuilder.setSchoolId("123");
			schoolCombineBuilder.setSchoolName("长沙第一中学");
			
			ClassCombine.Builder ccBuilder = ClassCombine.newBuilder();
			ccBuilder.setClassName("高一三班");
			ccBuilder.setClassId("123");
			
			LogicManager.registManager().setStuRegInfo("波徐", "123456789", "123456789",SEXTYPE.MALE, LogicManager.areaInfoManager().getAllAreaInfo().get(0),schoolCombineBuilder.build() , ccBuilder.build());
			Intent intent = new Intent(AdultRegActivity.this,
					AdultRegPhoneCheckActivity.class);
			intent.setAction(AdultRegPhoneCheckActivity.ACTIONREG);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
