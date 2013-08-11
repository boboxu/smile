package com.heme.logic.httpprotocols.groupinfo.creategroup.permanent;

import java.util.List;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.CreateFixedGroupReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;
import com.heme.logic.module.notpbmessage.AreaInfo;

public class CreatePermanentGroupRequest extends BaseLoginedBusinessRequest {

	CreateFixedGroupReq.Builder mCreateFixedGroupReqBuilder;

	public CreatePermanentGroupRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
	}

	// 创建群的验证类型
	public enum VERIFYTYPE {
		ANYONEPERMITTED, // 允许任何人
		NEEDVERIFY, // 验证
		NOONEPERMITTED;// 不允许任何人
		public static int value(VERIFYTYPE version) {
			switch (version) {
			case ANYONEPERMITTED:
				return 1;
			case NEEDVERIFY:
				return 2;
			case NOONEPERMITTED:
				return 3;
			default:
				return 1;
			}
		}
	}

	// 永久群
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mCreateFixedGroupReqBuilder.setSessionId(sessionId);
		mCreateFixedGroupReqBuilder.setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mCreateFixedGroupReqBuilder.setVersionNo(version);
		mCreateFixedGroupReqBuilder.setClientType(clientType);
	}

	public void setGroupInfo(AreaInfo area, String school, String groupName,
			VERIFYTYPE verifytype, List<Long> memberSystemId) {
		mCreateFixedGroupReqBuilder.setProvinceCode(area.getmProvinceCode());
		mCreateFixedGroupReqBuilder.setProvinceName(area.getmProvinceName());
		mCreateFixedGroupReqBuilder.setCityCode(area.getmCityCode());
		mCreateFixedGroupReqBuilder.setCityName(area.getmCityName());
		mCreateFixedGroupReqBuilder.setCountyCode(area.getmCountryCode());
		mCreateFixedGroupReqBuilder.setCountyName(area.getmCountryName());
		mCreateFixedGroupReqBuilder.setGroupName(groupName);
		mCreateFixedGroupReqBuilder.setVerifyType(VERIFYTYPE.value(verifytype));
		mCreateFixedGroupReqBuilder.addAllMemberSystemId(memberSystemId);
		mDataSvrProtoBuilder.setEnumCmd(Cmd.CreateFixedGroup);
		mDataSvrProtoBuilder.setCreateFixedGroupReqInfo(mCreateFixedGroupReqBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());

	}

	@Override
	public void initmDataBuilder() {
		mCreateFixedGroupReqBuilder = CreateFixedGroupReq.newBuilder();
	}
}
