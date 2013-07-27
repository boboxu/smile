package com.heme.smile.common;

import android.R.integer;

public class Constans {
	public static final int LOGIN_SUCCESS = 0;
	public static final int LOGIN_FAILED = LOGIN_SUCCESS + 1;
	//服务器验证用户输入的验证码正确
	public static final int VERIFY_CHECKCODE_SUCCESS = LOGIN_FAILED + 1;
	//验证码验证错误 
	public static final int VERIFY_CHECKCODE_FAILED = VERIFY_CHECKCODE_SUCCESS + 1;
	//家长注册成功
	public static final int ADULT_REG_SUCCESS = VERIFY_CHECKCODE_FAILED + 1;
	//家长注册失败
	public static final int ADULT_REG_FAILED = ADULT_REG_SUCCESS + 1;
	//用户注册时填写手机号，服务器给该手机号发送验证码成功的回调
	public static final int SEND_REG_CHECK_CODE_SUCCESS = ADULT_REG_FAILED + 1;
	//发送验证码失败的回调
	public static final int SEND_REG_CHECK_CODE_FAILED = SEND_REG_CHECK_CODE_SUCCESS + 1;
	//输入验证码界面倒计时
	public static final int INPUT_CHECKCODE_COUNT_DOWN = SEND_REG_CHECK_CODE_FAILED + 1;
	//清空聊天记录成功
	public static final int CLEAR_CHAT_HISTORY_SUCCESS = INPUT_CHECKCODE_COUNT_DOWN + 1;
	//清空聊天记录失败
	public static final int CLEAR_CHAT_HISTORY_FAILED = CLEAR_CHAT_HISTORY_SUCCESS + 1;
	//修改密码成功
	public static final int UPDATE_PWD_SUCCESS = CLEAR_CHAT_HISTORY_FAILED + 1;
	//修改密码失败
	public static final int UPDATE_PWD_FAILED = UPDATE_PWD_SUCCESS + 1;
	//反馈意见提交成功
	public static final int SUBMIT_FEEDBACK_SUCCESS = UPDATE_PWD_FAILED + 1;
	//反馈意见提交失败
	public static final int SUBMIT_FEEDBACK_FAILED = SUBMIT_FEEDBACK_SUCCESS + 1;
	//向PC发送绿色上网的处理指令成功
	public static final int SEND_PC_COMMOND_SUCCESS = SUBMIT_FEEDBACK_FAILED + 1;
	//发送指令失败
	public static final int SEND_PC_COMMOND_FAILED = SEND_PC_COMMOND_SUCCESS + 1;
}
