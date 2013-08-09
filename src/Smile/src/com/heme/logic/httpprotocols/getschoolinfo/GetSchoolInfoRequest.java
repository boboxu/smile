package com.heme.logic.httpprotocols.getschoolinfo;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.module.Data.RegGetSchoolReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class GetSchoolInfoRequest extends BaseBusinessRequest {
	
	RegGetSchoolReq.Builder mRegGetSchoolReqBuilder;
	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mRegGetSchoolReqBuilder.setVersionNo(version);
	}

	@Override
	public void initmDataBuilder() {
		mRegGetSchoolReqBuilder = RegGetSchoolReq.newBuilder();
		
	}
	
	public void setArea(String area)
	{
		mRegGetSchoolReqBuilder.setArea(area);
		mDataSvrProtoBuilder.setRegGetSchoolReqInfo(mRegGetSchoolReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.RegGetSchool);
		super.setBody(mRegGetSchoolReqBuilder.build().toByteString());
	}
}
