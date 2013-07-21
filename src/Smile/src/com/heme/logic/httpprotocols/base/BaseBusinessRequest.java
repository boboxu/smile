package com.heme.logic.httpprotocols.base;

import com.heme.commonlogic.servermanager.BaseRequest;

public class BaseBusinessRequest extends BaseRequest{
	
	private static final String PARAMNAME_VERSIONNUM = "versionNo";
	private static final String PARAMNAME_CLIENTTYPE = "clientType";
	private static final String PROTO_VERSION = "1.0";
	private static final int ClientType = 1;//1：家长；2：学生；3：老师,从外部读取
	public BaseBusinessRequest()
	{
		super();
		addDefaultParam();
	}
	
	private void addDefaultParam()
	{
		addStringParam(PROTO_VERSION, PARAMNAME_VERSIONNUM);
		addIntParam(PARAMNAME_CLIENTTYPE, ClientType);
	}
}
