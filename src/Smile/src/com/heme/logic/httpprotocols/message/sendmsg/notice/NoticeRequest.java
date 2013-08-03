package com.heme.logic.httpprotocols.message.sendmsg.notice;

import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;

public class NoticeRequest extends BaseMessageRequest {

	//客户端不需要实现
	protected NoticeRequest(long systemId, int sessionId,List<Long> mTargetId,List<Long> mTargetGid) {
		super(systemId,sessionId,mTargetId,mTargetGid,MSGTYPE.TYPENOTICE);
	}
	
	
}
