package com.heme.logic.httpprotocols.verifytel;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.VerifyPhoneRsp;

public class SendTelResponse extends BaseBusinessResponse {
	public static int ERRCODE_TEL_EXIST = 10000;
	public static int ERRCODE_TEL_NOEXIST = 10001;
	public static int ERRCODE_ILLEGALTE_TEL = 10002;
	
	private VerifyPhoneRsp mVerifyPhoneRsp;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mVerifyPhoneRsp = VerifyPhoneRsp.parseFrom(mTransData.getBytesBody());
	}
	
	public VerifyPhoneRsp getLoginRsp()
	{
		return mVerifyPhoneRsp;
	}
}
