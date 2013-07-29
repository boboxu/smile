package com.heme.logic.httpprotocols.regist;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.RegStudentReq;

public class StudentRegistRequest extends BaseBusinessRequest {

	RegStudentReq.Builder mRegStudentReqBuilder = RegStudentReq.newBuilder();
	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mRegStudentReqBuilder.setClientType(clientType);
		mRegStudentReqBuilder.setVersionNo(version);
		super.setBody(mRegStudentReqBuilder.build().toByteString());
	}
	
	public void setRegProfile(String phoneNo,String realName,String studentId,String password,String area,int schoolId,int classId)
	{
		mRegStudentReqBuilder.setPhoneNo(phoneNo);
		mRegStudentReqBuilder.setRealName(realName);
		mRegStudentReqBuilder.setStudentId(studentId);
		mRegStudentReqBuilder.setPassword(password);
		mRegStudentReqBuilder.setArea(area);
		mRegStudentReqBuilder.setSchoolId(schoolId);
		mRegStudentReqBuilder.setClassId(classId);
		super.setBody(mRegStudentReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}

}
