package com.heme.logic.httpprotocols.getschoolinfo;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.RegGetSchoolReq;

public class GetSchoolInfoRequest extends BaseBusinessRequest {
	RegGetSchoolReq.Builder mRegGetSchoolRegBuilder;
	
	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mRegGetSchoolRegBuilder.setVersionNo(version);
		super.setBody(mRegGetSchoolRegBuilder.build().toByteString());
	}
	
	public void setArea(String area)
	{
		mRegGetSchoolRegBuilder.setArea(area);
		super.setBody(mRegGetSchoolRegBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}

}
