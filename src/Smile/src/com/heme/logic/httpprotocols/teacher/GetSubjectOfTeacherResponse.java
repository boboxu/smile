package com.heme.logic.httpprotocols.teacher;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.BusiGetSubjectOfTeacherRsp;

public class GetSubjectOfTeacherResponse extends BaseBusinessResponse{
	private BusiGetSubjectOfTeacherRsp mBusiGetClassOfTeacherRsp;
	
	public BusiGetSubjectOfTeacherRsp getmBusiGetSubjectOfTeacherRsp() {
		return mBusiGetClassOfTeacherRsp;
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
		mBusiGetClassOfTeacherRsp = mDataSvrProto.getBusiGetSubjectOfTeacherRspInfo();
	}
}
