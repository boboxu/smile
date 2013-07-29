package com.heme.logic.httpprotocols.groupinfo.creategroup.permanent;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.CreateFixedGroupReq;

public class CreatePermanentGroupRequest extends BaseLoginedBusinessRequest {
	//创建群的验证类型
	public enum VERIFYTYPE
	{
		ANYONEPERMITTED,//允许任何人
		NEEDVERIFY,//验证
		NOONEPERMITTED;//不允许任何人
		public static int value(VERIFYTYPE version)
		{
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
	//永久群
	public CreateFixedGroupReq.Builder mCreateFixedGroupReqBuilder = CreateFixedGroupReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mCreateFixedGroupReqBuilder.setSessionId(sessionId);
		mCreateFixedGroupReqBuilder.setSystemId(systemId);
		super.setBody(mCreateFixedGroupReqBuilder.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mCreateFixedGroupReqBuilder.setVersionNo(version);
		mCreateFixedGroupReqBuilder.setClientType(clientType);
		super.setBody(mCreateFixedGroupReqBuilder.build().toByteString());
	}
	
	public void setGroupInfo(String area,String school,String groupName,VERIFYTYPE verifytype,List<Long> memberSystemId)
	{
		mCreateFixedGroupReqBuilder.setArea(area);
		mCreateFixedGroupReqBuilder.setGroupName(groupName);
		mCreateFixedGroupReqBuilder.setVerifyType(VERIFYTYPE.value(verifytype));
		for (int i = 0; i < memberSystemId.size(); i++) 
		{
			mCreateFixedGroupReqBuilder.addMemberSystemId(memberSystemId.get(i));
		}
		super.setBody(mCreateFixedGroupReqBuilder.build().toByteString());
		
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}
}
