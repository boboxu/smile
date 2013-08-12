package com.heme.logic.managers.statusreportmanager;

import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.LogicManager;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.status.GetStatusRequest;
import com.heme.logic.httpprotocols.status.GetStatusResponse;
import com.heme.logic.httpprotocols.status.UpdateStatusRequest;
import com.heme.logic.httpprotocols.status.UpdateStatusResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Status.EStatus;

public class StatusReportManager extends BaseBusinessLogicManager implements IStatusReportManagerInterface{

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		if (response instanceof UpdateStatusResponse) 
		{
			UpdateStatusResponse usResponse = (UpdateStatusResponse)response;
			if (usResponse.getmSetStatusRsp().getUint32Result() == 0) 
			{
				handleresponse(Constans.SET_STATUS_SUCCESS, usResponse.getmSetStatusRsp(), handler);
			}
			else
			{
				handleresponse(Constans.SET_STATUS_FAILED, usResponse.getmSetStatusRsp(), handler);
			}
		}
		else if (response instanceof GetStatusResponse) 
		{
			GetStatusResponse gsResponse = (GetStatusResponse)response;
			handleresponse(Constans.GET_STATUS_SUCCESS, gsResponse.getmGetStatusRsp(), handler);
		}
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response, Handler handler) {
		if (response instanceof UpdateStatusResponse) 
		{
			
		}
		else if (response instanceof GetStatusResponse) 
		{
			
		}
		return super.onFailedResponse(response, handler);
	}
	
	@Override
	public void setStatus(EStatus status, Handler handler) {
		UpdateStatusRequest request = new UpdateStatusRequest(LogicManager.accountManager().getCurrentAccoutSystemId());
		request.setStatus(status);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void getStatus(List<Long> systemIdList, Handler handler) {
		GetStatusRequest request = new GetStatusRequest();
		request.setTargetIdList(systemIdList);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}
}
