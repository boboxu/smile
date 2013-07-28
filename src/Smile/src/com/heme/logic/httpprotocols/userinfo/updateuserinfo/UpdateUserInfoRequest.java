package com.heme.logic.httpprotocols.userinfo.updateuserinfo;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetSelfInfoReq;

public class UpdateUserInfoRequest extends BaseLoginedBusinessRequest {
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

	SetSelfInfoReq.Builder mSetSelfInfoReqBuilder = SetSelfInfoReq.newBuilder();

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetSelfInfoReqBuilder.setSessionId(sessionId);
		mSetSelfInfoReqBuilder.setSystemId(systemId);
		super.setBody(mSetSelfInfoReqBuilder.build().toByteString());

	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {

		mSetSelfInfoReqBuilder.setVersionNo(version);
		mSetSelfInfoReqBuilder.setClientType(clientType);
		super.setBody(mSetSelfInfoReqBuilder.build().toByteString());

	}

	public void setIconName(SEXTYPE type,String birthday,String occupation,String location,String email,String interest) {
		mSetSelfInfoReqBuilder.setGender(SEXTYPE.value(type));
		mSetSelfInfoReqBuilder.setBirthday(birthday);
		mSetSelfInfoReqBuilder.setOccupation(occupation);
		mSetSelfInfoReqBuilder.setLocation(location);
		mSetSelfInfoReqBuilder.setEmail(email);
		mSetSelfInfoReqBuilder.setInterest(interest);
		super.setBody(mSetSelfInfoReqBuilder.build().toByteString());
	}

}
