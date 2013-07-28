package com.heme.logic.httpprotocols.login;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.LoginReq;

public class LoginRequest extends BaseBusinessRequest {
	private LoginReq.Builder mLoginDataBuilder = LoginReq.newBuilder();
	public enum LOGINTYPE
	{
		TypeTel,	//电话登陆
		TypeWX;//XX号
		public static int value(LOGINTYPE type)
		{
			switch (type) {
			case TypeTel:
				return 1;
			case TypeWX:
				return 2;
			default:
				return 1;
			}
		}
	};
	
	public void setLoginInfo(String account,String pwd,LOGINTYPE type)
	{
		mLoginDataBuilder.setId(account).setPassword(pwd);
		super.setBody(mLoginDataBuilder.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version,int client_type) {
		mLoginDataBuilder.setClientType(client_type);
		mLoginDataBuilder.setVersionNo(version);
		super.setBody(mLoginDataBuilder.build().toByteString());
	}
}
