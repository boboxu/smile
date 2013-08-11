package com.heme.logic.httpprotocols.message.sendmsg.socialgroup;

import java.util.ArrayList;
import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.MessageType;

public class SocialGroupRequest extends BaseMessageRequest {

	protected SocialGroupRequest(long systemId, String sessionId,List<Long> mTargetGid) {
		super(systemId,sessionId,new ArrayList<Long>(),mTargetGid,MessageType.MT_Community);
	}
	
}
