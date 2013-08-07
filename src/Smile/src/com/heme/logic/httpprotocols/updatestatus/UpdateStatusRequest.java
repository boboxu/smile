package com.heme.logic.httpprotocols.updatestatus;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
//import com.heme.logic.module.Data.SetStatusReq;

public class UpdateStatusRequest extends BaseLoginedBusinessRequest {
	public UpdateStatusRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	public enum USERSTATUS {
		StatusOnLine, StatusOffLine, StatusHide;
		public static int value(USERSTATUS type) {
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


	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
//		((SetStatusReq.Builder)mDataBuilder).setSessionId(sessionId);
//		((SetStatusReq.Builder)mDataBuilder).setSystemId(systemId);	
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
//		((SetStatusReq.Builder)mDataBuilder).setVersionNo(version);
//		((SetStatusReq.Builder)mDataBuilder).setClientType(clientType);
	}

	public void setStatus(USERSTATUS status) {
//		((SetStatusReq.Builder)mDataBuilder).setStatus(USERSTATUS.value(status));
//		super.setBody(((SetStatusReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
//		mDataBuilder = SetStatusReq.newBuilder();
	}
}
