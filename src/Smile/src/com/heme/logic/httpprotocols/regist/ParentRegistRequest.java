package com.heme.logic.httpprotocols.regist;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.RegParentReq;

public class ParentRegistRequest extends BaseBusinessRequest {
	public void setRegProfile(String phoneNo,String realName,String idCardNo,String password,List<Long> childIdList,String verifyCode)
	{
		((RegParentReq.Builder)mDataBuilder).setPhoneNo(phoneNo);
		((RegParentReq.Builder)mDataBuilder).setRealName(realName);
		((RegParentReq.Builder)mDataBuilder).setIdCardNo(idCardNo);
		((RegParentReq.Builder)mDataBuilder).setPassword(password);
		for (int i = 0; i < childIdList.size(); i++) {
			((RegParentReq.Builder)mDataBuilder).addChildSystemId(childIdList.get(i));	
		}
		((RegParentReq.Builder)mDataBuilder).setVerifyCode(verifyCode);
		super.buildAccessReq(((RegParentReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((RegParentReq.Builder)mDataBuilder).setVersionNo(version);
		((RegParentReq.Builder)mDataBuilder).setClientType(clientType);
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = RegParentReq.newBuilder();
	}

}
