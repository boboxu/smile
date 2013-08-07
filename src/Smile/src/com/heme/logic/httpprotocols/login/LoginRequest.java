package com.heme.logic.httpprotocols.login;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.LoginReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class LoginRequest extends BaseBusinessRequest {

//	private LoginReq.Builder mLoginDataBuilder = LoginReq.newBuilder();
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
		((LoginReq.Builder)mDataBuilder).setId(account).setPassword(pwd);
		mDataSvrBuilder.setEnumCmd(Cmd.Login);
		mDataSvrBuilder.setLoginReqInfo(((LoginReq.Builder)mDataBuilder).build());
		super.buildAccessReq(mDataSvrBuilder.build().toByteString());
		
	}

	@Override
	public void setVersionAndClientType(String version,int client_type) {
		((LoginReq.Builder)mDataBuilder).setClientType(client_type);
		((LoginReq.Builder)mDataBuilder).setVersionNo(version);
	}

	@Override
	public void initmDataBuilder() { 
		mDataBuilder = LoginReq.newBuilder();
	}
}
