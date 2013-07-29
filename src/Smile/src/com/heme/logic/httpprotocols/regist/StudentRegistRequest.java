package com.heme.logic.httpprotocols.regist;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.RegStudentReq;

public class StudentRegistRequest extends BaseBusinessRequest {

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((RegStudentReq.Builder)mDataBuilder).setClientType(clientType);
		((RegStudentReq.Builder)mDataBuilder).setVersionNo(version);
	}
	
	public void setRegProfile(String phoneNo,String realName,String studentId,String password,String area,int schoolId,int classId)
	{
		((RegStudentReq.Builder)mDataBuilder).setPhoneNo(phoneNo);
		((RegStudentReq.Builder)mDataBuilder).setRealName(realName);
		((RegStudentReq.Builder)mDataBuilder).setStudentId(studentId);
		((RegStudentReq.Builder)mDataBuilder).setPassword(password);
		((RegStudentReq.Builder)mDataBuilder).setArea(area);
		((RegStudentReq.Builder)mDataBuilder).setSchoolId(schoolId);
		((RegStudentReq.Builder)mDataBuilder).setClassId(classId);
		super.buildAccessReq(((RegStudentReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = RegStudentReq.newBuilder();
	}

}
