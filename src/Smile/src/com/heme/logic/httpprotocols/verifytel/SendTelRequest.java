package com.heme.logic.httpprotocols.verifytel;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;

public class SendTelRequest extends BaseBusinessRequest {
	
	private static String PARAMNAME_PHONENO = "phoneNo";
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
		String operString = "reg";
		switch (type) {
		case TypeReg:
			operString = "reg";
			break;
		case TypeResetPwd:
			operString = "resetpwd";
			break;
		default:
			operString = "reg";
			break;
		}
		addStringParam("optype", operString);
	}
}
