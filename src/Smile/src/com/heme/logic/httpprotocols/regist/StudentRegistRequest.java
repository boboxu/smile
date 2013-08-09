package com.heme.logic.httpprotocols.regist;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.module.Data.RegStudentReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class StudentRegistRequest extends BaseBusinessRequest {

	RegStudentReq.Builder mRegStudentReqBuilder;
	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mRegStudentReqBuilder.setClientType(clientType);
		mRegStudentReqBuilder.setVersionNo(version);
	}
	
	public void setRegProfile(String phoneNo,String realName,String studentId,String password,String area,int schoolId,int classId,String verifyCode)
	{
		mRegStudentReqBuilder.setPhoneNo(phoneNo);
		mRegStudentReqBuilder.setRealName(realName);
		mRegStudentReqBuilder.setStudentId(studentId);
		mRegStudentReqBuilder.setPassword(password);
		mRegStudentReqBuilder.setArea(area);
		mRegStudentReqBuilder.setSchoolId(schoolId);
		mRegStudentReqBuilder.setClassId(classId);
		mRegStudentReqBuilder.setVerifyCode(verifyCode);
		
		mDataSvrProtoBuilder.setRegStudentReqInfo(mRegStudentReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.RegStudent);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mRegStudentReqBuilder = RegStudentReq.newBuilder();
	}

}
