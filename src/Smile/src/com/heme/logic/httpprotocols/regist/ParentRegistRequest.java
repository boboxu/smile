package com.heme.logic.httpprotocols.regist;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.RegParentReq;

public class ParentRegistRequest extends BaseBusinessRequest {
	RegParentReq.Builder mRegParentReqBuilder = RegParentReq.newBuilder();
	
	public void setRegProfile(String phoneNo,String realName,String idCardNo,String password,List<Long> childIdList,String verifyCode)
	{
		mRegParentReqBuilder.setPhoneNo(phoneNo);
		mRegParentReqBuilder.setRealName(realName);
		mRegParentReqBuilder.setIdCardNo(idCardNo);
		mRegParentReqBuilder.setPassword(password);
		for (int i = 0; i < childIdList.size(); i++) {
			mRegParentReqBuilder.addChildSystemId(childIdList.get(i));	
		}
		mRegParentReqBuilder.setVerifyCode(verifyCode);
		super.setBody(mRegParentReqBuilder.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mRegParentReqBuilder.setVersionNo(version);
		mRegParentReqBuilder.setClientType(clientType);
		super.setBody(mRegParentReqBuilder.build().toByteString());
	}

}
