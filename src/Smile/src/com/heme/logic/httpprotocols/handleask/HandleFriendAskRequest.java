package com.heme.logic.httpprotocols.handleask;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;

public class HandleFriendAskRequest extends BaseLoginedBusinessRequest {

	public HandleFriendAskRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}

	public void setTargetId(long systemId)
	{
		
	}
}
