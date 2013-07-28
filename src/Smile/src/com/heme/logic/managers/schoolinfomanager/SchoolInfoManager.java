package com.heme.logic.managers.schoolinfomanager;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.getschoolinfo.GetSchoolInfoRequest;
import com.heme.logic.httpprotocols.getschoolinfo.GetSchoolInfoResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.notpbmessage.AreaInfo;

public class SchoolInfoManager extends BaseBusinessLogicManager implements ISchoolInfoManagerInterface{

	@Override
	protected void onSuccessResponse(BaseResponse response,
			Handler handler) {
		//取出数据来写数据库
		handleresponse(Constans.GET_SCHOOLINFO_SUCCESS, ((GetSchoolInfoResponse)response).getmRegGetSchoolRsp(), handler);
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response,
			Handler handler) {
		handleresponse(Constans.GET_SCHOOLINFO_FAILED, ((GetSchoolInfoResponse)response).getmRegGetSchoolRsp(), handler);
		return super.onFailedResponse(response, handler);
	}

	@Override
	public void getSchoolInfo(AreaInfo areainfo,Handler handler) {
		GetSchoolInfoRequest request = new GetSchoolInfoRequest();
		request.setArea(areainfo.getmAreaName());
		sendRequest(request, handler,  getClass().getName(), _FUNC_());
	}

}
