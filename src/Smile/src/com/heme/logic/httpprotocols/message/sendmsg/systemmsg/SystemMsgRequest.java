package com.heme.logic.httpprotocols.message.sendmsg.systemmsg;

import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.MessageType;

public class SystemMsgRequest extends BaseMessageRequest {

	//客户端不发系统消息
	protected SystemMsgRequest(long systemId, String sessionId,List<Long> mTargetId,List<Long> mTargetGid) {
		super(systemId,sessionId,mTargetId,mTargetGid,MessageType.MT_System);
	}
	
}
