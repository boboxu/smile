package com.heme.logic.httpprotocols.login;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;

public class LoginRequest extends BaseBusinessRequest {
	
	private static final String PARAMNAME_ID = "id";
	private static final String PARAMNAME_PWD = "password";
	private static final String PARAMNAME_TYPE = "type";
	enum LoginType
	{
		TypeTel,	//电话登陆
		TypeWX,//XX号
		
	};
	
	public void setLoginInfo(String account,String pwd,LoginType type)
	{
		addStringParam(PARAMNAME_ID, account);
		addStringParam(PARAMNAME_PWD, pwd);
		switch (type) {
		case TypeTel:
			addIntParam(PARAMNAME_TYPE, 0);
			break;
		case TypeWX:
			addIntParam(PARAMNAME_TYPE, 1);
			break;
		default:
			break;
		}
	}
}
