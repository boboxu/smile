package com.heme.logic.httpprotocols.feedback;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SendFeedbackReq;

public class FeedBackRequest extends BaseLoginedBusinessRequest {

	public FeedBackRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((SendFeedbackReq.Builder) mDataBuilder).setClientType(clientType);
		((SendFeedbackReq.Builder) mDataBuilder).setVersionNo(version);
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((SendFeedbackReq.Builder) mDataBuilder).setSessionId(sessionId);
		((SendFeedbackReq.Builder) mDataBuilder).setSystemId(systemId);
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = SendFeedbackReq.newBuilder();
	}

	public void setContent(String feedback) {
		((SendFeedbackReq.Builder) mDataBuilder).setFeedback(feedback);
		super.buildAccessReq(((SendFeedbackReq.Builder) mDataBuilder).build().toByteString());
	}
}
