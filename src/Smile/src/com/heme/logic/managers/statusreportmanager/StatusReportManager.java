package com.heme.logic.managers.statusreportmanager;

import android.os.Handler;

import com.heme.commonlogic.logicmanager.IBaseLogicManagerListener;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.httpprotocols.updatestatus.UpdateStatusRequest.USERSTATUS;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class StatusReportManager extends BaseBusinessLogicManager implements IStatusReportManagerInterface{

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		
	}

	@Override
	public void setStatus(USERSTATUS status, Handler handler) {
		
	}


}
