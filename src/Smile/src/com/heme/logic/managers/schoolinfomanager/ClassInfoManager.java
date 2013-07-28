package com.heme.logic.managers.schoolinfomanager;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.getschoolinfo.GetClassInfoRequest;
import com.heme.logic.httpprotocols.getschoolinfo.GetClassInfoResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Data.SchoolCombine;

public class ClassInfoManager extends BaseBusinessLogicManager implements IClassInfoManagerInterface{

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		handleresponse(Constans.GET_CLASSINFO_SUCCESS, ((GetClassInfoResponse)response).getmRegGetClassRsp(), handler);
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response, Handler handler) {
		handleresponse(Constans.GET_CLASSINFO_FAILED, ((GetClassInfoResponse)response).getmRegGetClassRsp(), handler);		
		return super.onFailedResponse(response, handler);
	}
	@Override
	public void getClassInfo(SchoolCombine schoolInfo, Handler handler) {
		GetClassInfoRequest request = new GetClassInfoRequest();
		request.setSchoolId(schoolInfo.getSchoolId());
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

}
