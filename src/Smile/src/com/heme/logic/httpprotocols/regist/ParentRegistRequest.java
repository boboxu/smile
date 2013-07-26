package com.heme.logic.httpprotocols.regist;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;

public class ParentRegistRequest extends BaseBusinessRequest {
	private static final String PARAMNAME_PHONENO = "phoneNo";
	private static final String PARAMNAME_REALNAME	= "realName";
	private static final String PARAMNAME_IDCARDNO = "idCardNo";
	private static final String PARAMNAME_PASSWORD = "password";
	private static final String PARAMNAME_CHILDID = "childId";
	
	public void setRegProfile(String phoneNo,String realName,String idCardNo,String password,String childId)
	{
		addStringParam(PARAMNAME_PHONENO, phoneNo);
		addStringParam(PARAMNAME_REALNAME, realName);
		addStringParam(PARAMNAME_IDCARDNO, idCardNo);
		addStringParam(PARAMNAME_PASSWORD, password);
		addStringParam(PARAMNAME_CHILDID, childId);
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		// TODO Auto-generated method stub
		
	}

}
