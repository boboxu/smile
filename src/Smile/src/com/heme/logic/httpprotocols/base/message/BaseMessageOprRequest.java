package com.heme.logic.httpprotocols.base.message;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.commonlogic.servermanager.BasePbRequest;
import com.heme.logic.common.Configuration;
import com.heme.logic.module.Message.MessageOpr;
import com.heme.logic.module.Message.MsgCommand;
import com.heme.logic.module.Message.PollMsgReq;
import com.heme.logic.module.Message.PollUnreadInfoReq;
import com.heme.logic.module.Message.PushMsgReq;
import com.heme.logic.module.Message.SendMsgReq;

public class BaseMessageOprRequest extends BasePbRequest{
	private MessageOpr.Builder mMessageOprBuilder;
	protected MessageOpr mMessageOpr;
	protected static int CLIENT_TYPE = Configuration.APP_VERSION;
	
	protected BaseMessageOprRequest(long systemId,String sessionId) {
		mMessageOprBuilder = MessageOpr.newBuilder();
		mMessageOprBuilder.setUint32ClientType(CLIENT_TYPE);
		mMessageOprBuilder.setStrVersion(Configuration.PROTO_VERSION);
		mMessageOprBuilder.setUint64Uid(systemId);
		mMessageOprBuilder.setStringSessionId(sessionId);
	}
	
	protected void setSendMsgReq(SendMsgReq msg,ByteString context) 
	{
		mMessageOprBuilder.setMsgSendReq(msg);
		mMessageOprBuilder.setBytesContext(context);
		mMessageOprBuilder.setUint32Command(MsgCommand.MC_SendMsgReq);
		super.setBody(mMessageOprBuilder.build().toByteString());
	}
	
	protected void setPushMsgReq(PushMsgReq msg,ByteString context) 
	{
		mMessageOprBuilder.setMsgPushReq(msg);
		mMessageOprBuilder.setBytesContext(context);
		mMessageOprBuilder.setUint32Command(MsgCommand.MC_PushMsgReq);
		super.setBody(mMessageOprBuilder.build().toByteString());
	}
	
	protected void setPollMsgReq(PollMsgReq msg,ByteString context) 
	{
		mMessageOprBuilder.setMsgPollReq(msg);
		mMessageOprBuilder.setBytesContext(context);
		mMessageOprBuilder.setUint32Command(MsgCommand.MC_PollMsgReq);
		super.setBody(mMessageOprBuilder.build().toByteString());
	}
	
	protected void setPollUnreadMsgReq(PollUnreadInfoReq msg,ByteString context) 
	{
		mMessageOprBuilder.setMsgPollUnreadReq(msg);
		mMessageOprBuilder.setBytesContext(context);
		mMessageOprBuilder.setUint32Command(MsgCommand.MC_PollUnreadInfoReq);
		super.setBody(mMessageOprBuilder.build().toByteString());
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mMessageOpr = MessageOpr.parseFrom(mTransData.getBytesBody());
	}

	@Override
	protected void setCmd() {
		mTransDataBuilder.setStrCmd("MsgSvr");
	}
}
