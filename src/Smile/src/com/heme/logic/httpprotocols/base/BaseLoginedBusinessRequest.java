package com.heme.logic.httpprotocols.base;

public abstract class BaseLoginedBusinessRequest extends BaseBusinessRequest {
	protected abstract void setLoginedInfo(String sessionId,long systemId);
	public BaseLoginedBusinessRequest(String sessionId,long systemId) {
		super();
		setLoginedInfo(sessionId, systemId);
		setCmd("DataSvr");
	}
}
