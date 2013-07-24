package com.heme.logic.httpprotocols.verifytel;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;

public class SendTelRequest extends BaseBusinessRequest {
	
	private static String PARAMNAME_PHONENO = "phoneNo";
	private static String PARAMNAME_VERIFYTYPE = "verifyType";
	public enum OperType //验证电话的操作类型
	{
		TypeReg,//注册
		TypeResetPwd,//找回密码
	}
	public void setTelNum(String tel)
	{
		addStringParam(PARAMNAME_PHONENO, tel);
	}
	
	public void setOperationType(OperType type)
	{
		int opertype = 1;
		switch (type) {
		case TypeReg:
			opertype = 1;
			break;
		case TypeResetPwd:
			opertype = 2;
			break;
		default:
			opertype = 1;
			break;
		}
		addIntParam(PARAMNAME_VERIFYTYPE, opertype);
	}
}
