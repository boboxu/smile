package com.heme.logic.httpprotocols.regist;

import java.util.List;

import android.R.integer;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.httpprotocols.userinfo.updateuserinfo.UpdateUserInfoRequest.SEXTYPE;
import com.heme.logic.module.Data.DataSvrProto.Cmd;
import com.heme.logic.module.Data.RegParentReq;

public class ParentRegistRequest extends BaseBusinessRequest {
	
	RegParentReq.Builder mRegParentReqBuilder;
	public void setRegProfile(String phoneNo,String realName,String idCardNo,String password,SEXTYPE sex,List<String> childIdList,String verifyCode)
	{
		mRegParentReqBuilder.setPhoneNo(phoneNo);
		mRegParentReqBuilder.setRealName(realName);
		mRegParentReqBuilder.setIdCardNo(idCardNo);
		mRegParentReqBuilder.setPassword(password);
		mRegParentReqBuilder.addAllChildStudentId(childIdList);
		mRegParentReqBuilder.setVerifyCode(verifyCode);
		mRegParentReqBuilder.setGender(SEXTYPE.value(sex));
		mDataSvrProtoBuilder.setRegParentReqInfo(mRegParentReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.RegParent);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mRegParentReqBuilder.setVersionNo(version);
		mRegParentReqBuilder.setClientType(clientType);
	}

	@Override
	public void initmDataBuilder() {
		mRegParentReqBuilder = RegParentReq.newBuilder();
	}

}
