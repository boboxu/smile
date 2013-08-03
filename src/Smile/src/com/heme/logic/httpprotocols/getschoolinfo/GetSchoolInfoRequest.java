package com.heme.logic.httpprotocols.getschoolinfo;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.RegGetSchoolReq;

public class GetSchoolInfoRequest extends BaseBusinessRequest {
	
	@Override
	public void setVersionAndClientType(String version, int clientType) {
		((RegGetSchoolReq.Builder)mDataBuilder).setVersionNo(version);
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = RegGetSchoolReq.newBuilder();
		
	}
	
	public void setArea(String area)
	{
		((RegGetSchoolReq.Builder)mDataBuilder).setArea(area);
		super.buildAccessReq(((RegGetSchoolReq.Builder)mDataBuilder).build().toByteString());
	}
}
