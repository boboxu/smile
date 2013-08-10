package com.heme.smile.test;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.login.LoginRequest.LOGINTYPE;
import com.heme.logic.module.Data.ClassCombine;
import com.heme.logic.module.Data.LoginRsp;
import com.heme.logic.module.Data.RegParentRsp;
import com.heme.logic.module.Data.SchoolCombine;
import com.heme.logic.module.notpbmessage.AreaInfo;
import com.heme.smile.AdultRegPhoneCheckActivity;
import com.heme.smile.BaseActivity;
import com.heme.smile.R;
import com.heme.smile.Util;

public class TestManagerActivity extends BaseActivity implements
		OnClickListener {

	private Button mLoginBtn, mParRegBtn, mStuRegBtn;
	private Button mSchoolInfoBtn, mClassInfoBtn;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			LoginRsp loginresp;
			RegParentRsp regresp;
			dismissDialog();
			switch (msg.what) {
			case Constans.GET_SCHOOLINFO_SUCCESS:
				break;
			case Constans.GET_SCHOOLINFO_FAILED:
				break;
			case Constans.LOGIN_SUCCESS:
				loginresp = (LoginRsp) msg.obj;
				Toast.makeText(TestManagerActivity.this, "登陆成功",
						Toast.LENGTH_SHORT).show();
				break;
			case Constans.LOGIN_FAILED:
				loginresp = (LoginRsp) msg.obj;
				Toast.makeText(TestManagerActivity.this,
						loginresp.getErrString(), Toast.LENGTH_SHORT).show();
				break;
			case Constans.ADULT_REG_FAILED:
				regresp = (RegParentRsp) msg.obj;
				if (regresp != null) {
					Util.showToast(TestManagerActivity.this,
							regresp.getErrString());
				} else {
					Util.showToast(TestManagerActivity.this, "注册失败");
				}
				break;
			case Constans.ADULT_REG_SUCCESS:
				break;
			case Constans.GET_CLASSINFO_SUCCESS:
				break;
			case Constans.GET_CLASSINFO_FAILED:
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
		setContentView(R.layout.testmanager);
		mLoginBtn = (Button) findViewById(R.id.login);
		mLoginBtn.setOnClickListener(this);
		mParRegBtn = (Button) findViewById(R.id.parregBtn);
		mParRegBtn.setOnClickListener(this);
		mStuRegBtn = (Button) findViewById(R.id.sturegBtn);
		mStuRegBtn.setOnClickListener(this);
		mSchoolInfoBtn = (Button) findViewById(R.id.schoolinfo);
		mSchoolInfoBtn.setOnClickListener(this);
		mClassInfoBtn = (Button) findViewById(R.id.classinfo);
		mClassInfoBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.login:
			testloginmanager();
			break;
		case R.id.parregBtn:
			testparreg();
			break;
		case R.id.sturegBtn:
			teststureg();
			break;
		case R.id.schoolinfo:
			testschoolmanager();
			break;
		case R.id.classinfo:
			testclassmanager();
			break;
		default:
			break;
		}
	}

	// 获取学校信息
	private void testschoolmanager() {
		AreaInfo areainfo = LogicManager.areaInfoManager().getAllAreaInfo()
				.get(0);
		LogicManager.schoolInfoManager().getSchoolInfo(areainfo, mHandler);
		showWaitDialog("拉取学校信息中");
	}

	// 登陆
	private void testloginmanager() {
		LogicManager.loginManager().Login("2222", "123456789",
				LOGINTYPE.TypeWX, mHandler);
		showWaitDialog("登陆中");
	}

	// 学生注册
	private void teststureg() {
		AreaInfo areainfo = LogicManager.areaInfoManager().getAllAreaInfo()
				.get(0);

		SchoolCombine.Builder schoolCombineBuilder = SchoolCombine.newBuilder();
		schoolCombineBuilder.setSchoolId("123");
		schoolCombineBuilder.setSchoolName("长沙第一中学");

		ClassCombine.Builder ccBuilder = ClassCombine.newBuilder();
		ccBuilder.setClassName("高一三班");
		ccBuilder.setClassId("123");

		LogicManager.registManager().setStuRegInfo("波徐", "20130812",
				"20130812", areainfo, schoolCombineBuilder.build(),
				ccBuilder.build());
		LogicManager.registManager().setPhoneNum("123456789");
		LogicManager.registManager().startReg("1234", mHandler);
	}

	// 家长注册
	private void testparreg() 
	{
		List<String> childlist = new ArrayList<String>();
		childlist.add("123456");
		LogicManager.registManager().setParRegInfo("波徐", "421099198734567898", "123", childlist);
		LogicManager.registManager().setPhoneNum("13456789098");
		LogicManager.registManager().startReg("1234", mHandler);
	}
	
	private void testclassmanager()
	{
		SchoolCombine.Builder schoolCombineBuilder = SchoolCombine.newBuilder();
		schoolCombineBuilder.setSchoolId("123");
		schoolCombineBuilder.setSchoolName("长沙第一中学");
		LogicManager.classInfoManager().getClassInfo(schoolCombineBuilder.build(), mHandler);
	}
}
