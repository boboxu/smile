package com.heme.logic.httpprotocols.push;

import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprResponse;
import com.heme.logic.module.Message.PushMsgRes;

public class PushMsgResponse extends BaseMessageOprResponse
{
	private PushMsgRes.Builder mPushMsgResBuilder;
	
	public PushMsgResponse()
	{
		super();
		mPushMsgResBuilder = PushMsgRes.newBuilder();
		mPushMsgResBuilder.setUint32Result(0);
		mMessageOprBuilder.setMsgPushRes(mPushMsgResBuilder.build());
		mAccessRespBuilder.setBytesBody(mMessageOprBuilder.build().toByteString());
		setResponseData(mAccessRespBuilder.build().toByteArray());
	}
}
