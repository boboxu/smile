package com.heme.logic.httpprotocols.groupinfo.getgroup;

import java.util.List;

import android.R.integer;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.GetGroupInfoReq;

public class GetGroupInfoRequest extends BaseLoginedBusinessRequest {
	//获取群信息
	GetGroupInfoReq.Builder mGetGroupInfoReqBuilder = GetGroupInfoReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mGetGroupInfoReqBuilder.setSessionId(sessionId);
		mGetGroupInfoReqBuilder.setSystemId(systemId);
		super.setBody(mGetGroupInfoReqBuilder.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mGetGroupInfoReqBuilder.setVersionNo(version);
		mGetGroupInfoReqBuilder.setClientType(clientType);
		super.setBody(mGetGroupInfoReqBuilder.build().toByteString());
	}
	
	public void setGroupId(List<Integer> groupIdList)
	{
		for (int i = 0; i < groupIdList.size(); i++) {
			mGetGroupInfoReqBuilder.addGroupId(groupIdList.get(i));
		}
		super.setBody(mGetGroupInfoReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}
}
