package com.heme.logic.httpprotocols.message.sendmsg.voicetest;

import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.MessageType;

public class SendVoiceTestMsgRequest extends BaseMessageRequest{

	public SendVoiceTestMsgRequest(long srcId, String sessionId,
			List<Long> mTargetId, List<Long> mTargetGid) {
		super(srcId, sessionId, mTargetId, mTargetGid, MessageType.MT_VoiceTest);
	}

}
