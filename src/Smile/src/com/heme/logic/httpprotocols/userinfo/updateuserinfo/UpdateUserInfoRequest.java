package com.heme.logic.httpprotocols.userinfo.updateuserinfo;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetSelfInfoReq;

public class UpdateUserInfoRequest extends BaseLoginedBusinessRequest {
	public UpdateUserInfoRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	public enum SEXTYPE {
		FEMALE, // 女
		MALE, // 男
		NONE;// 未知
		public static int value(SEXTYPE type) {
			switch (type) {
			case FEMALE:
				return 1;
			case MALE:
				return 2;
			case NONE:
				return 3;
			default:
				return 3;
			}
		}
	};

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((SetSelfInfoReq.Builder)mDataBuilder).setSessionId(sessionId);
		((SetSelfInfoReq.Builder)mDataBuilder).setSystemId(systemId);

	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {

		((SetSelfInfoReq.Builder)mDataBuilder).setVersionNo(version);
		((SetSelfInfoReq.Builder)mDataBuilder).setClientType(clientType);

	}

	public void setIconName(SEXTYPE type,String birthday,String occupation,String location,String email,String interest) {
		((SetSelfInfoReq.Builder)mDataBuilder).setGender(SEXTYPE.value(type));
		((SetSelfInfoReq.Builder)mDataBuilder).setBirthday(birthday);
		((SetSelfInfoReq.Builder)mDataBuilder).setOccupation(occupation);
		((SetSelfInfoReq.Builder)mDataBuilder).setLocation(location);
		((SetSelfInfoReq.Builder)mDataBuilder).setEmail(email);
		((SetSelfInfoReq.Builder)mDataBuilder).setInterest(interest);
		super.buildAccessReq(((SetSelfInfoReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = SetSelfInfoReq.newBuilder();
	}

}
