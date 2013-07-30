package com.heme.logic.httpprotocols.login;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.LoginRsp;

public class LoginResponse extends BaseBusinessResponse {
	private LoginRsp mLoginrsp;
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mLoginrsp = LoginRsp.parseFrom(mAccessRespData.getBytesBody());
	}
	
	public LoginRsp getLoginRsp()
	{
		return mLoginrsp;
	}	
	
	public void setmLoginrsp(LoginRsp mLoginrsp) {
		this.mLoginrsp = mLoginrsp;
	}
}
