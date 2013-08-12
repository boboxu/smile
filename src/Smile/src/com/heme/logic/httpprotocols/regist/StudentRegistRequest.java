package com.heme.logic.httpprotocols.regist;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.httpprotocols.userinfo.updateuserinfo.UpdateUserInfoRequest.SEXTYPE;
import com.heme.logic.module.Data.DataSvrProto.Cmd;
import com.heme.logic.module.Data.RegStudentReq;
import com.heme.logic.module.notpbmessage.AreaInfo;

public class StudentRegistRequest extends BaseBusinessRequest {

	RegStudentReq.Builder mRegStudentReqBuilder;
	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mRegStudentReqBuilder.setClientType(clientType);
		mRegStudentReqBuilder.setVersionNo(version);
	}
	
	public void setRegProfile(String phoneNo,String realName,String studentId,String password,SEXTYPE sex,AreaInfo area,String schoolId,String classId,String verifyCode)
	{
		mRegStudentReqBuilder.setPhoneNo(phoneNo);
		mRegStudentReqBuilder.setRealName(realName);
		mRegStudentReqBuilder.setStudentId(studentId);
		mRegStudentReqBuilder.setPassword(password);
		mRegStudentReqBuilder.setProvinceCode(area.getmProvinceCode());
		mRegStudentReqBuilder.setProvinceName(area.getmProvinceName());
		mRegStudentReqBuilder.setCityCode(area.getmCityCode());
		mRegStudentReqBuilder.setCityName(area.getmCityName());
		mRegStudentReqBuilder.setCountyCode(area.getmCountryCode());
		mRegStudentReqBuilder.setCountyName(area.getmCountryName());
		mRegStudentReqBuilder.setSchoolId(schoolId);
		mRegStudentReqBuilder.setClassId(classId);
		mRegStudentReqBuilder.setVerifyCode(verifyCode);
		mRegStudentReqBuilder.setGender(SEXTYPE.value(sex));
		
		mDataSvrProtoBuilder.setRegStudentReqInfo(mRegStudentReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.RegStudent);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mRegStudentReqBuilder = RegStudentReq.newBuilder();
	}

}
