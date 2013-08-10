package com.heme.logic.httpprotocols.userinfo.updateuserinfo;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetSelfInfoReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

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

	SetSelfInfoReq.Builder mSetSelfInfoReqBuilder;
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetSelfInfoReqBuilder.setSessionId(sessionId);
		mSetSelfInfoReqBuilder.setSystemId(systemId);

	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {

		mSetSelfInfoReqBuilder.setVersionNo(version);
		mSetSelfInfoReqBuilder.setClientType(clientType);

	}

	public void setSelfInfo(SEXTYPE type,String birthday,String occupation,String location,String email,String interest) {
		mSetSelfInfoReqBuilder.setGender(SEXTYPE.value(type));
		mSetSelfInfoReqBuilder.setBirthday(birthday);
		mSetSelfInfoReqBuilder.setOccupation(occupation);
		mSetSelfInfoReqBuilder.setLocation(location);
		mSetSelfInfoReqBuilder.setEmail(email);
		mSetSelfInfoReqBuilder.setInterest(interest);
		mDataSvrProtoBuilder.setSetSelfInfoReqInfo(mSetSelfInfoReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.SetSelfInfo);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mSetSelfInfoReqBuilder = SetSelfInfoReq.newBuilder();
	}

}
