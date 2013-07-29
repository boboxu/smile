package com.heme.logic.httpprotocols.feedback;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.httpprotocols.verifytel.SendTelRequest.VERIFYTYPE;
import com.heme.logic.module.Data.SendFeedbackReq;

public class FeedBackRequest extends BaseLoginedBusinessRequest {

	private SendFeedbackReq.Builder mSendFeedbackReqBuilder = SendFeedbackReq
			.newBuilder();

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mSendFeedbackReqBuilder.setClientType(clientType);
		mSendFeedbackReqBuilder.setVersionNo(version);
		super.setBody(mSendFeedbackReqBuilder.build().toByteString());
	}

	public void setContent(String feedback) {
		mSendFeedbackReqBuilder.setFeedback(feedback);
		super.setBody(mSendFeedbackReqBuilder.build().toByteString());
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSendFeedbackReqBuilder.setSessionId(sessionId);
		mSendFeedbackReqBuilder.setSystemId(systemId);
		super.setBody(mSendFeedbackReqBuilder.build().toByteString());
		
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}

}
