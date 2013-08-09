package com.heme.logic.httpprotocols.verifytel;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.module.Data.VerifyPhoneReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

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
	
	VerifyPhoneReq.Builder mVerifyPhoneReqBuilder;
	
	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mVerifyPhoneReqBuilder.setClientType(clientType);
		mVerifyPhoneReqBuilder.setVersionNo(version);
	}
	
	public void setVerifyTelWithType(String phoneno,VERIFYTYPE type)
	{
		mVerifyPhoneReqBuilder.setPhoneNo(phoneno);
		mVerifyPhoneReqBuilder.setVerifyType(VERIFYTYPE.value(type));
		mDataSvrProtoBuilder.setVerifyPhoneReqInfo(mVerifyPhoneReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.VerifyPhone);
		super.setBody(mVerifyPhoneReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mVerifyPhoneReqBuilder = VerifyPhoneReq.newBuilder();
	}
}
