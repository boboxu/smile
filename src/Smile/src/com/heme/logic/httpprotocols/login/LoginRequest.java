package com.heme.logic.httpprotocols.login;

import android.os.Environment;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.module.Data.LoginReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class LoginRequest extends BaseBusinessRequest {

	public static final String LOGINDATAPATH = "smile/logininfo";
	
	private LoginReq.Builder mLoginDataBuilder;

	public enum LOGINTYPE {
		TypeTel, // 电话登陆
		TypeWX;// XX号
		public static int value(LOGINTYPE type) {
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

	public void setLoginInfo(String account, String pwd, LOGINTYPE type) {
		mLoginDataBuilder.setId(account).setPassword(pwd);
		
		mDataSvrProtoBuilder.setEnumCmd(Cmd.Login);
		mDataSvrProtoBuilder.setLoginReqInfo(mLoginDataBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());

	}

	@Override
	public void setVersionAndClientType(String version, int client_type) {
		mLoginDataBuilder.setClientType(client_type);
		mLoginDataBuilder.setVersionNo(version);
	}

	@Override
	public void initmDataBuilder() {
		mLoginDataBuilder = LoginReq.newBuilder();
	}
	
	public LoginReq.Builder getmLoginDataBuilder() {
		return mLoginDataBuilder;
	}
}
