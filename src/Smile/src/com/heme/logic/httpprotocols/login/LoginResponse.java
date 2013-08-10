package com.heme.logic.httpprotocols.login;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.LoginRsp;

public class LoginResponse extends BaseBusinessResponse {
	public static final String LOGINRSPDATAFILENAME = "loginrspinfo";
	private LoginRsp mLoginrsp;
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mLoginrsp = mDataSvrProto.getLoginRspInfo();
	}
	
	public LoginRsp getLoginRsp()
	{
		return mLoginrsp;
	}	
	
	public void setmLoginrsp(LoginRsp mLoginrsp) {
		this.mLoginrsp = mLoginrsp;
	}
}
