package com.heme.logic.httpprotocols.getschoolinfo;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.module.Data.RegGetClassReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class GetClassInfoRequest extends BaseBusinessRequest {

	RegGetClassReq.Builder mRegGetClassReqBuilder;
	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mRegGetClassReqBuilder.setVersionNo(version);
		
	}

	public void setSchoolId(String schoolId)
	{
		mRegGetClassReqBuilder.setSchoolId(schoolId);
		mDataSvrProtoBuilder.setEnumCmd(Cmd.RegGetClass);
		mDataSvrProtoBuilder.setRegGetClassReqInfo(mRegGetClassReqBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mRegGetClassReqBuilder = RegGetClassReq.newBuilder();
	}
}
