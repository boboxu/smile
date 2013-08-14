package com.heme.logic.httpprotocols.message.sendmsg.notice;

import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.MessageType;

public class NoticeRequest extends BaseMessageRequest {

	//客户端不需要实现
	protected NoticeRequest(long systemId, String sessionId,List<Long> mTargetId,List<Long> mTargetGid) {
		super(systemId,sessionId,mTargetId,mTargetGid,MessageType.MT_Broadcast);
	}
}
