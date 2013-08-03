package com.heme.logic.httpprotocols.message.sendmsg.classassis;

import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;

public class SendClassMsgRequest extends BaseMessageRequest{

	//需要给targetid或者targetgid么，还要待定
	//内容类别，算是文本信息呢？还是别的
	protected SendClassMsgRequest(long srcId, int sessionId,
			List<Long> mTargetGid) {
		super(srcId, sessionId, null, mTargetGid, MSGTYPE.TYPECLASSASSIS);
	}
	
}
