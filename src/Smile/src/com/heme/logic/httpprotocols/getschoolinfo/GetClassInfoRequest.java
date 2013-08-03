package com.heme.logic.httpprotocols.getschoolinfo;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.RegGetClassReq;

public class GetClassInfoRequest extends BaseBusinessRequest {

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		((RegGetClassReq.Builder)mDataBuilder).setVersionNo(version);
		
	}

	public void setSchoolId(int schoolId)
	{
		((RegGetClassReq.Builder)mDataBuilder).setSchoolId(schoolId);
		super.buildAccessReq(((RegGetClassReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = RegGetClassReq.newBuilder();
	}
}
