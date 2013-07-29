package com.heme.logic.httpprotocols.updatestatus;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetStatusReq;

public class UpdateStatusRequest extends BaseLoginedBusinessRequest {
	public enum USERSTATUS
	{
		StatusOnLine,
		StatusOffLine,
		StatusHide;
		public static int value(USERSTATUS type)
		{
			switch (type) {
			case StatusOnLine:
				return 1;
			case StatusOffLine:
				return 2;
			case StatusHide:
				return 3;
			default:
				return 1;
			}
		}
	}
	SetStatusReq.Builder mSetStatusReqBuilder = SetStatusReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetStatusReqBuilder.setSessionId(sessionId);
		mSetStatusReqBuilder.setSystemId(systemId);
		super.setBody(mSetStatusReqBuilder.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mSetStatusReqBuilder.setVersionNo(version);
		mSetStatusReqBuilder.setClientType(clientType);
		super.setBody(mSetStatusReqBuilder.build().toByteString());
	}

	public void setStatus(USERSTATUS status)
	{
		mSetStatusReqBuilder.setStatus(USERSTATUS.value(status));
		super.setBody(mSetStatusReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}
}
