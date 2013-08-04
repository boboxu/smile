package com.heme.logic.httpprotocols.message.sendmsg.classassis;

import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;

public class SendClassMsgRequest extends BaseMessageRequest{
	
	//目前需求，不支持客户端发送
	protected SendClassMsgRequest(long srcId, String sessionId,
			List<Long> mTargetGid) {
		super(srcId, sessionId, null, mTargetGid, MSGTYPE.TYPECLASSASSIS);
	}
	
}
