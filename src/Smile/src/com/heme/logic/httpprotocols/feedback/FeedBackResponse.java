package com.heme.logic.httpprotocols.feedback;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.SendFeedbackRsp;

public class FeedBackResponse extends BaseBusinessResponse {
	private SendFeedbackRsp mSendFeedbackRsp;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mSendFeedbackRsp = mDataSvrProto.getSendFeedbackRspInfo();
	}
	
	public SendFeedbackRsp getFeedBackRsp()
	{
		return mSendFeedbackRsp;
	}
}
