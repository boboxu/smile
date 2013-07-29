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
	
	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((VerifyPhoneReq.Builder)mDataBuilder).setClientType(clientType);
		((VerifyPhoneReq.Builder)mDataBuilder).setVersionNo(version);
	}
	
	public void setVerifyTelWithType(String phoneno,VERIFYTYPE type)
	{
		((VerifyPhoneReq.Builder)mDataBuilder).setPhoneNo(phoneno);
		((VerifyPhoneReq.Builder)mDataBuilder).setVerifyType(VERIFYTYPE.value(type));
		super.buildAccessReq(((VerifyPhoneReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = VerifyPhoneReq.newBuilder();
	}
}
