package com.heme.logic.httpprotocols.base;

public class BaseLoginedBusinessRequest extends BaseBusinessRequest {

	private static final String PARAMNAME_SESSIONID = "sessionId";
	private static final String PARAMNAME_SYSTEMID = "systemId";
	
	public BaseLoginedBusinessRequest() {
		super();
		//if logined
		addDefaultParam();
	}
	
	private void addDefaultParam()
	{
		addStringParam(PARAMNAME_SESSIONID, "1234");
		addIntParam(PARAMNAME_SYSTEMID, 4321);
	}
}
