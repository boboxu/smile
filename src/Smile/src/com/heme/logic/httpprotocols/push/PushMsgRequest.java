package com.heme.logic.httpprotocols.push;

import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprRequest;
import com.heme.logic.module.Message.CommonMsg;
import com.heme.logic.module.Message.PushMsgReq;

public class PushMsgRequest extends BaseMessageOprRequest {
	
	//解析服务器过来的推送请求
	protected PushMsgRequest(long systemId, String sessionId) {
		super(systemId, sessionId);
		// TODO Auto-generated constructor stub
	}
	
	private PushMsgReq mPushMsgReq;
	private List<CommonMsg> mCommonMsgList;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mPushMsgReq = mMessageOpr.getMsgPushReq();
		mCommonMsgList = mPushMsgReq.getRptMsgPushmsgList();
	}
	
	public PushMsgReq getMsgReq()
	{
		return mPushMsgReq;
	}
	
	public List<CommonMsg> getMsgList()
	{
		return mCommonMsgList;
	}
}
