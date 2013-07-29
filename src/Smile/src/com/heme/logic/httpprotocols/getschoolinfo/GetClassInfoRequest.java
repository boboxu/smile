package com.heme.logic.httpprotocols.getschoolinfo;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.RegGetClassReq;

public class GetClassInfoRequest extends BaseBusinessRequest {

	private RegGetClassReq.Builder mRegGetClassReqBuilder = RegGetClassReq.newBuilder();


	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mRegGetClassReqBuilder.setVersionNo(version);
		super.setBody(mRegGetClassReqBuilder.build().toByteString());
	}

	public void setSchoolId(int schoolId)
	{
		mRegGetClassReqBuilder.setSchoolId(schoolId);
		super.setBody(mRegGetClassReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}
}
