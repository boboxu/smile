package com.heme.logic.httpprotocols.base;

public abstract class BaseLoginedBusinessRequest extends BaseBusinessRequest {
	public abstract void setLoginedInfo(String sessionId,long systemId);
}
