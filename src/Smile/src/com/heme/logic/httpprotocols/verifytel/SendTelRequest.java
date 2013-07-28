package com.heme.logic.httpprotocols.verifytel;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.VerifyPhoneReq;

public class SendTelRequest extends BaseBusinessRequest {
	public enum VERIFYTYPE
	{
		VERIFYFORREG,
		VERIFYFORRESETPWD;
		public static int value(VERIFYTYPE type)
		{
			switch (type) {
			case VERIFYFORREG:
				return 1;
			case VERIFYFORRESETPWD:
				return 2;
			default:
				return 1;
			}
		}
	}
	private VerifyPhoneReq.Builder mVerifyPhoneReqBuilder = VerifyPhoneReq.newBuilder();
	
	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mVerifyPhoneReqBuilder.setClientType(clientType);
		mVerifyPhoneReqBuilder.setVersionNo(version);
		super.setBody(mVerifyPhoneReqBuilder.build().toByteString());
	}
	
	public void setVerifyTelWithType(String phoneno,VERIFYTYPE type)
	{
		mVerifyPhoneReqBuilder.setPhoneNo(phoneno);
		mVerifyPhoneReqBuilder.setVerifyType(VERIFYTYPE.value(type));
		super.setBody(mVerifyPhoneReqBuilder.build().toByteString());
	}
}
