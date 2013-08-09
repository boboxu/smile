package com.heme.logic.httpprotocols.feedback;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SendFeedbackReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class FeedBackRequest extends BaseLoginedBusinessRequest {

	private SendFeedbackReq.Builder mFeedBackReqBuilder;
	public FeedBackRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mFeedBackReqBuilder.setClientType(clientType);
		mFeedBackReqBuilder.setVersionNo(version);
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mFeedBackReqBuilder.setSessionId(sessionId);
		mFeedBackReqBuilder.setSystemId(systemId);
	}

	@Override
	public void initmDataBuilder() {
		mFeedBackReqBuilder = SendFeedbackReq.newBuilder();
	}

	public void setContent(String feedback) {
		mFeedBackReqBuilder.setFeedback(feedback);
		
		mDataSvrProtoBuilder.setEnumCmd(Cmd.SendFeedback);
		mDataSvrProtoBuilder.setSendFeedbackReqInfo(mFeedBackReqBuilder.build());
		super.setBody(mFeedBackReqBuilder.build().toByteString());
	}
}
