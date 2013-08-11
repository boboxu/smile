package com.heme.smile.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
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
import com.heme.logic.module.Message.PicMsgInfo;
import com.heme.logic.module.Message.PollMsgRes;
import com.heme.logic.module.Message.VideoMsgInfo;
import com.heme.logic.module.Message.VoiceMsgInfo;
import com.heme.logic.module.notpbmessage.AreaInfo;
import com.heme.smile.BaseActivity;
import com.heme.smile.R;
import com.heme.smile.Util;

public class TestManagerActivity extends BaseActivity implements
		OnClickListener {

	private Button mLoginBtn, mParRegBtn, mStuRegBtn;
	private Button mSchoolInfoBtn, mClassInfoBtn, mLocalLoginButtn;
	private Button mC2Ctext, mC2Cvideo, mC2Cvoice, mC2Cpic,mC2CIdCard;
	private Button mC2Gtext, mC2Gvideo, mC2Gvoice, mC2Gpic,mC2GIdCard;
	private Button mVoiceTest, mPollUnread, mPollMsg;

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
			case Constans.SEND_TEXT_C2C_SUCCESS:
			case Constans.SEND_VIDEO_C2C_SUCCESS:
			case Constans.SEND_VOICE_C2C_SUCCESS:
			case Constans.SEND_PIC_C2C_SUCCESS:
				Util.showToast(TestManagerActivity.this, "发送成功");
				break;
			case Constans.SEND_TEXT_C2C_FAILED:
			case Constans.SEND_VIDEO_C2C_FAILED:
			case Constans.SEND_VOICE_C2C_FAILED:
			case Constans.SEND_PIC_C2C_FAILED:
				Util.showToast(TestManagerActivity.this, "发送失败");
				break;
			case Constans.POLL_C2C_SUCCESS:
				PollMsgRes pollresp = (PollMsgRes)msg.obj;
				Intent intent = new Intent();
				intent.setClass(TestManagerActivity.this, ResultActivity.class);
				intent.putExtra("result", pollresp);
				startActivity(intent);
				break;
			case Constans.POLL_C2C_FAILED:
				Util.showToast(TestManagerActivity.this, "拉取失败");
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

		initLine1();
		initLine2();
		initLine3();
		initLine4();
		initLine5();
	}

	private void initLine1()
	{
		mLoginBtn = (Button) findViewById(R.id.login);
		mLoginBtn.setOnClickListener(this);
		mParRegBtn = (Button) findViewById(R.id.parregBtn);
		mParRegBtn.setOnClickListener(this);
		mStuRegBtn = (Button) findViewById(R.id.sturegBtn);
		mStuRegBtn.setOnClickListener(this);
	}
	
	private void initLine2()
	{
		mSchoolInfoBtn = (Button) findViewById(R.id.schoolinfo);
		mSchoolInfoBtn.setOnClickListener(this);
		mClassInfoBtn = (Button) findViewById(R.id.classinfo);
		mClassInfoBtn.setOnClickListener(this);
		mLocalLoginButtn = (Button) findViewById(R.id.loginwithlocaldata);
		mLocalLoginButtn.setOnClickListener(this);
	}
	
	private void initLine3()
	{
		mC2Ctext = (Button) findViewById(R.id.btnc2ctext);
		mC2Ctext.setOnClickListener(this);
		mC2Cvideo = (Button) findViewById(R.id.btnc2cvideo);
		mC2Cvideo.setOnClickListener(this);
		mC2Cvoice = (Button) findViewById(R.id.btnc2cvoice);
		mC2Cvoice.setOnClickListener(this);
		mC2Cpic = (Button) findViewById(R.id.btnc2cpic);
		mC2Cpic.setOnClickListener(this);
		mC2CIdCard = (Button) findViewById(R.id.btnc2cidcard);
		mC2CIdCard.setOnClickListener(this);
	}
	
	private void initLine4()
	{
		mC2Gtext = (Button) findViewById(R.id.btnc2gtext);
		mC2Gtext.setOnClickListener(this);
		mC2GIdCard = (Button) findViewById(R.id.btnc2gidcard);
		mC2GIdCard.setOnClickListener(this);
		mC2Gpic = (Button) findViewById(R.id.btnc2gpic);
		mC2Gpic.setOnClickListener(this);
		mC2Gvideo = (Button) findViewById(R.id.btnc2gvideo);
		mC2Gvideo.setOnClickListener(this);
		mC2Gvoice = (Button) findViewById(R.id.btnc2gvoice);
		mC2Gvoice.setOnClickListener(this);
	}
	
	private void initLine5()
	{
		mVoiceTest = (Button) findViewById(R.id.btnvoicetest);
		mVoiceTest.setOnClickListener(this);
		mPollMsg = (Button) findViewById(R.id.btnpollmsg);
		mPollMsg.setOnClickListener(this);
		mPollUnread = (Button) findViewById(R.id.btnpollunread);
		mPollUnread.setOnClickListener(this);
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
		case R.id.loginwithlocaldata:
			testlocallogin();
			break;
		case R.id.btnc2ctext:
			testc2ctext();
			break;
		case R.id.btnc2cvideo:
			testc2cvideo();
			break;
		case R.id.btnc2cvoice:
			testc2cvoice();
			break;
		case R.id.btnc2cpic:
			testc2cpic();
			break;
		case R.id.btnc2cidcard:
			testc2cidcard();
			break;
		case R.id.btnc2gtext:
			testc2gtext();
		case R.id.btnc2gvideo:
			testc2gvideo();
			break;
		case R.id.btnc2gvoice:
			testc2gvoice();
			break;
		case R.id.btnc2gpic:
			testc2gpic();
			break;
		case R.id.btnc2gidcard:
			testc2gidcard();
			break;
		case R.id.btnvoicetest:
			testvoicetest();
			break;
		case R.id.btnpollmsg:
			testpollmsg();
			break;
		case R.id.btnpollunread:
			testpollunread();
			break;
		default:
			break;
		}
	}

	// 优先使用本地数据登陆
	private void testlocallogin() {
		boolean rst = LogicManager.loginManager().LoginWithSavedData(mHandler);
		if (!rst) {
			testloginmanager();
		} else {
			showWaitDialog("本地数据登陆中");
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
		showWaitDialog("外部数据登录中");
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
		showWaitDialog("注册中");
	}

	// 家长注册
	private void testparreg() {
		List<String> childlist = new ArrayList<String>();
		childlist.add("123456");
		LogicManager.registManager().setParRegInfo("波徐", "421099198734567898",
				"123", childlist);
		LogicManager.registManager().setPhoneNum("13456789098");
		LogicManager.registManager().startReg("1234", mHandler);
		showWaitDialog("注册中");
	}

	private void testclassmanager() {
		SchoolCombine.Builder schoolCombineBuilder = SchoolCombine.newBuilder();
		schoolCombineBuilder.setSchoolId("123");
		schoolCombineBuilder.setSchoolName("长沙第一中学");
		LogicManager.classInfoManager().getClassInfo(
				schoolCombineBuilder.build(), mHandler);
		showWaitDialog("获取班级信息中");
	}

	private void testc2ctext() {
		LogicManager.messageManager().sendTextMsgToUser(1234567, "1234567",
				mHandler);
		showWaitDialog("发消息中");
	}

	private void testc2cvoice() {
		VoiceMsgInfo.Builder msgInfobuilder = VoiceMsgInfo.newBuilder();
		msgInfobuilder.setStrVoiceUrl("http://www.qq.com");
		LogicManager.messageManager().sendVoiceMsgToUser(1234567,
				msgInfobuilder.build(), mHandler);
		showWaitDialog("发消息中");
	}

	private void testc2cvideo() {
		VideoMsgInfo.Builder msgInfoBuilder = VideoMsgInfo.newBuilder();
		msgInfoBuilder.setStrVideoUrl("http://www.qq.com");
		LogicManager.messageManager().sendVideoMsgToUser(1234567,
				msgInfoBuilder.build(), mHandler);
	}

	private void testc2cpic() {
		PicMsgInfo.Builder msgInfoBuilder = PicMsgInfo.newBuilder();
		msgInfoBuilder.setStrPicUrl("http://www.qq.com");
		LogicManager.messageManager().sendPicMsgToUser(1234567,
				msgInfoBuilder.build(), mHandler);
	}

	private void testc2cidcard()
	{
		LogicManager.messageManager().sendIdCardToUser(1234567, "波徐;男;码农", mHandler);
	}
	
	private void testc2gtext() {

	}

	private void testc2gvoice() {

	}

	private void testc2gvideo() {

	}

	private void testc2gpic() {

	}

	private void testc2gidcard()
	{
		
	}
	
	private void testvoicetest() {

	}

	private void testpollunread() {
		
	}

	private void testpollmsg() {
		LogicManager.messageManager().pollAllUserMsg(mHandler);
	}
}
