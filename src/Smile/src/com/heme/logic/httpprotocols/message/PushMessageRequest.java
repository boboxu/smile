package com.heme.logic.httpprotocols.message;

import java.util.List;

import com.google.protobuf.ByteString;
import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprRequest;
import com.heme.logic.module.Message.PushMsgReq;
import com.heme.logic.module.Message.UnReadInfo;

public class PushMessageRequest extends BaseMessageOprRequest {
	private PushMsgReq.Builder mPushMsgReqBuilder;
	protected PushMessageRequest(long systemId, int sessionId) {
		super(systemId, sessionId);
		mPushMsgReqBuilder = PushMsgReq.newBuilder();
	}
	
	protected void buildPushReq(List<UnReadInfo> unreadinfoList,ByteString context) {
		mPushMsgReqBuilder.addAllMsgUnreadInfo(unreadinfoList);
		super.setPushMsgReq(mPushMsgReqBuilder.build(), context);
	}
	

}
