package com.heme.logic.httpprotocols.message.sendmsg.notice;

import java.util.List;

import com.google.protobuf.ByteString;
import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.NetGuardInfo;

public class NoticeRequest extends BaseMessageRequest {
	
	//应该也不需要targetid和targetgid
	//内容类型又是神马？
	protected NoticeRequest(long systemId, int sessionId,List<Long> mTargetId,List<Long> mTargetGid) {
		super(systemId,sessionId,mTargetId,mTargetGid,MSGTYPE.TYPENETGUARD);
	}
	
	public void setCommandInfo(COMMANDTYPE type,int eventId,String reportInfo,ByteString context)
	{
		NetGuardInfo.Builder msgBuilder = NetGuardInfo.newBuilder();
		msgBuilder.setUint32Action(COMMANDTYPE.value(type));
		msgBuilder.setUint32EventId(eventId);
		msgBuilder.setStrReportInfo(reportInfo);
		super.setNetGuardMsgInfo(msgBuilder.build(), context);		
	}
	
}
