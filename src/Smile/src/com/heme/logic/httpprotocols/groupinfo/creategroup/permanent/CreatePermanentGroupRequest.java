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
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((CreateFixedGroupReq.Builder)mDataBuilder).setSessionId(sessionId);
		((CreateFixedGroupReq.Builder)mDataBuilder).setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((CreateFixedGroupReq.Builder)mDataBuilder).setVersionNo(version);
		((CreateFixedGroupReq.Builder)mDataBuilder).setClientType(clientType);
	}
	
	public void setGroupInfo(String area,String school,String groupName,VERIFYTYPE verifytype,List<Long> memberSystemId)
	{
		((CreateFixedGroupReq.Builder)mDataBuilder).setArea(area);
		((CreateFixedGroupReq.Builder)mDataBuilder).setGroupName(groupName);
		((CreateFixedGroupReq.Builder)mDataBuilder).setVerifyType(VERIFYTYPE.value(verifytype));
		for (int i = 0; i < memberSystemId.size(); i++) 
		{
			((CreateFixedGroupReq.Builder)mDataBuilder).addMemberSystemId(memberSystemId.get(i));
		}
		super.buildAccessReq(((CreateFixedGroupReq.Builder)mDataBuilder).build().toByteString());
		
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = CreateFixedGroupReq.newBuilder();
	}
}
