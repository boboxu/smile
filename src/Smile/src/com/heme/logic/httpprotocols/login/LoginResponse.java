package com.heme.logic.httpprotocols.login;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.LoginRsp;

public class LoginResponse extends BaseBusinessResponse {
	private LoginRsp mLoginrsp;
	@Override
	public void parseData() {
		// TODO Auto-generated method stub
		super.parseData();
		//测试代码
		try {
			mLoginrsp = LoginRsp.parseFrom(mAccessRespData.getBytesBody());
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public LoginRsp getLoginRsp()
	{
		return mLoginrsp;
	}
}
