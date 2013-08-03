package com.heme.logic.httpprotocols.message.sendmsg.c2g;

import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;

public class SendGroupMsgRequest extends BaseMessageRequest {

	protected SendGroupMsgRequest(long srcId, int sessionId,
			List<Long> mTargetGid,
			CONTENTTYPE contentType) {
		super(srcId, sessionId, null, mTargetGid, MSGTYPE.TYPEGROUP);
	}

}
