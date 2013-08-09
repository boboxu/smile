package com.heme.logic.httpprotocols.teacher;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.module.Data.BusiGetClassOfTeacherReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class GetClassOfTeacherRequest extends BaseBusinessRequest{

	private BusiGetClassOfTeacherReq.Builder mBusiGetClassOfTeacherReqBuilder;
	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mBusiGetClassOfTeacherReqBuilder.setClientType(clientType);
		mBusiGetClassOfTeacherReqBuilder.setVersionNo(version);
	}

	@Override
	public void initmDataBuilder() {
		mBusiGetClassOfTeacherReqBuilder = BusiGetClassOfTeacherReq.newBuilder();
	}

	public void setTargetSystemId(long systemId)
	{
		mBusiGetClassOfTeacherReqBuilder.setTargetSystemId(systemId);
		mDataSvrProtoBuilder.setBusiGetClassOfTeacherReqInfo(mBusiGetClassOfTeacherReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.BusiGetClassOfTeacher);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}
}
