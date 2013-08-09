package com.heme.logic.httpprotocols.teacher;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.module.Data.BusiGetSubjectOfTeacherReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class GetSubjectOfTeacherRequest extends BaseBusinessRequest{
	private BusiGetSubjectOfTeacherReq.Builder mBusiGetSubjectOfTeacherReqBuilder;
	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mBusiGetSubjectOfTeacherReqBuilder.setClientType(clientType);
		mBusiGetSubjectOfTeacherReqBuilder.setVersionNo(version);
	}

	@Override
	public void initmDataBuilder() {
		mBusiGetSubjectOfTeacherReqBuilder = BusiGetSubjectOfTeacherReq.newBuilder();
	}

	public void setTargetSystemId(long systemId)
	{
		mBusiGetSubjectOfTeacherReqBuilder.setTargetSystemId(systemId);
		mDataSvrProtoBuilder.setBusiGetSubjectOfTeacherReqInfo(mBusiGetSubjectOfTeacherReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.BusiGetSubjectOfTeacher);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}
}
