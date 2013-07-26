package com.heme.logic.httpprotocols.verifytel;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;

public class CheckVerifyRequest extends BaseBusinessRequest {
	private static final String PARAMNAME_VERIFYCODE = "verifyCode";
	private static final String PARAMNAME_PHONENO = "phoneNo";
	public void setCheckVerifyCode(String code)
	{
		addStringParam(PARAMNAME_VERIFYCODE, code);
	}
	
	public void setTelNum(String telnum)
	{
		addStringParam(PARAMNAME_PHONENO, telnum);
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		// TODO Auto-generated method stub
		
	}
	
}
