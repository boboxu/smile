package com.heme.logic.httpprotocols.message.resptosvr;

import com.google.protobuf.ByteString;
import com.heme.logic.httpprotocols.base.message.BaseMessageOprRequest;

public class RespToSvrRequest extends BaseMessageOprRequest {

	protected RespToSvrRequest(long systemId, String sessionId) {
		super(systemId, sessionId);
	}
	
	public RespToSvrRequest(long systemId,String sessionId,ByteString context)
	{
		super(systemId,sessionId);
		super.setRespMsgToSvr(context);
	}

}
