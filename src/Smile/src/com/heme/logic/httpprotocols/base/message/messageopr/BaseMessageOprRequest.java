package com.heme.logic.httpprotocols.base.message.messageopr;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.commonlogic.servermanager.BasePbRequest;
import com.heme.logic.common.Configuration;
import com.heme.logic.module.Message.MessageOpr;
import com.heme.logic.module.Message.PollMsgReq;
import com.heme.logic.module.Message.PollUnreadInfoReq;
import com.heme.logic.module.Message.PushMsgReq;
import com.heme.logic.module.Message.SendMsgReq;

public class BaseMessageOprRequest extends BasePbRequest{
	private MessageOpr.Builder mMessageOprBuilder;
	protected MessageOpr mMessageOpr;
	private static final String PROTO_VERSION = "1";
	protected static int CLIENT_TYPE = Configuration.APP_VERSION;
	
	public enum MSGTYPE {
		TYPEALL,	//全部，拉取未读信息的时候有用到
		TYPEC2C, // 点对点消息
		TYPEGROUP, // 群消息
		TYPESYSTEM, // 系统消息
		TYPESOCIALGROUP, // 社区群组
		TYPENOTICE, // 公告
		TYPECLASSASSIS, // 课堂信息助手
		TYPEVOICETEST, // 语音测评
		TYPENETGUARD;// 绿色上网助手
		public static int value(MSGTYPE type) {
			switch (type) {
			case TYPEALL:
				return 0;
			case TYPEC2C:
				return 1;
			case TYPEGROUP:
				return 2;
			case TYPESYSTEM:
				return 3;
			case TYPESOCIALGROUP:
				return 4;
			case TYPENOTICE:
				return 5;
			case TYPECLASSASSIS:
				return 6;
			case TYPEVOICETEST:
				return 7;
			case TYPENETGUARD:
				return 8;
			default:
				return 1;
			}
		}
	}
	
	public enum COMMANDTYPE
	{
		TYPESENDREQ,
		TYPESENDRES,
		TYPEPUSHREQ,
		TYPEPUSHRES,
		TYPEPOLLREQ,
		TYPEPOLLRES,
		TYPEPOLLUNREADREQ,
		TYPEPOLLUNREADRES;
		public static int value(COMMANDTYPE type)
		{
			switch (type) {
			case TYPESENDREQ:
				return 1;
			case TYPESENDRES:
				return 2;
			case TYPEPUSHREQ:
				return 3;
			case TYPEPUSHRES:
				return 4;
			case TYPEPOLLREQ:
				return 5;
			case TYPEPOLLRES:
				return 6;
			case TYPEPOLLUNREADREQ:
				return 7;
			case TYPEPOLLUNREADRES:
				return 8;
			default:
				return 1;
			}
		}
	}
	
	protected BaseMessageOprRequest(long systemId,String sessionId) {
		mMessageOprBuilder = MessageOpr.newBuilder();
		mMessageOprBuilder.setUint32ClientType(CLIENT_TYPE);
		mMessageOprBuilder.setStrVersion(PROTO_VERSION);
		mMessageOprBuilder.setUint64Uid(systemId);
		mMessageOprBuilder.setStringSessionId(sessionId);
	}
	
	protected void setSendMsgReq(SendMsgReq msg,ByteString context) 
	{
		mMessageOprBuilder.setMsgSendReq(msg);
		mMessageOprBuilder.setBytesContext(context);
		mMessageOprBuilder.setUint32Command(COMMANDTYPE.value(COMMANDTYPE.TYPESENDREQ));
		super.setBody(mMessageOprBuilder.build().toByteString());
	}
	
	protected void setPushMsgReq(PushMsgReq msg,ByteString context) 
	{
		mMessageOprBuilder.setMsgPushReq(msg);
		mMessageOprBuilder.setBytesContext(context);
		mMessageOprBuilder.setUint32Command(COMMANDTYPE.value(COMMANDTYPE.TYPEPUSHREQ));
		super.setBody(mMessageOprBuilder.build().toByteString());
	}
	
	protected void setPollMsgReq(PollMsgReq msg,ByteString context) 
	{
		mMessageOprBuilder.setMsgPollReq(msg);
		mMessageOprBuilder.setBytesContext(context);
		mMessageOprBuilder.setUint32Command(COMMANDTYPE.value(COMMANDTYPE.TYPEPOLLREQ));
		super.setBody(mMessageOprBuilder.build().toByteString());
	}
	
	protected void setPollUnreadMsgReq(PollUnreadInfoReq msg,ByteString context) 
	{
		mMessageOprBuilder.setMsgPollUnreadReq(msg);
		mMessageOprBuilder.setBytesContext(context);
		mMessageOprBuilder.setUint32Command(COMMANDTYPE.value(COMMANDTYPE.TYPEPOLLUNREADREQ));
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
