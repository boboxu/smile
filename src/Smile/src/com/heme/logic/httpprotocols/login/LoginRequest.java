package com.heme.logic.httpprotocols.login;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.LoginReq;

public class LoginRequest extends BaseBusinessRequest {
	private LoginReq.Builder mLoginDataBuilder = LoginReq.newBuilder();
	public enum LoginType
	{
		TypeTel,	//电话登陆
		TypeWX,//XX号
		
	};
	
	public void setLoginInfo(String account,String pwd,LoginType type)
	{
		mLoginDataBuilder.setId(account).setPassword(pwd);
		switch (type) {
		case TypeTel:
			mLoginDataBuilder.setClientType(0);
			break;
		case TypeWX:
			mLoginDataBuilder.setClientType(1);
			break;
		default:
			break;
		}
		super.setBody(mLoginDataBuilder.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version,int client_type) {
		mLoginDataBuilder.setClientType(client_type);
		mLoginDataBuilder.setVersionNo(version);
		super.setBody(mLoginDataBuilder.build().toByteString());
	}
}
