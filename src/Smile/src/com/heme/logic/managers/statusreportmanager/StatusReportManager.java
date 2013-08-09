package com.heme.logic.managers.statusreportmanager;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Status.EStatus;

public class StatusReportManager extends BaseBusinessLogicManager implements IStatusReportManagerInterface{

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		
	}

	@Override
	public void setStatus(EStatus status, Handler handler) {
		
	}


}
