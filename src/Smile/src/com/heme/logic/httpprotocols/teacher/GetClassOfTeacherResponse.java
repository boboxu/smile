package com.heme.logic.httpprotocols.teacher;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.BusiGetClassOfTeacherRsp;

public class GetClassOfTeacherResponse extends BaseBusinessResponse{
	private BusiGetClassOfTeacherRsp mBusiGetClassOfTeacherRsp;
	
	public BusiGetClassOfTeacherRsp getmBusiGetClassOfTeacherRsp() {
		return mBusiGetClassOfTeacherRsp;
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
		mBusiGetClassOfTeacherRsp = mDataSvrProto.getBusiGetClassOfTeacherRspInfo();
	}
}
