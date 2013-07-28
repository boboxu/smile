package com.heme.logic.managers.registmanager;

import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.regist.ParentRegistRequest;
import com.heme.logic.httpprotocols.regist.ParentRegistResponse;
import com.heme.logic.httpprotocols.regist.StudentRegistRequest;
import com.heme.logic.httpprotocols.regist.StudentRegistResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Data.ClassCombine;
import com.heme.logic.module.Data.SchoolCombine;
import com.heme.logic.module.notpbmessage.AreaInfo;

public class RegistManager extends BaseBusinessLogicManager implements
		IRegistManagerInterface {

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		if (response instanceof StudentRegistResponse) 
		{
			handleresponse(Constans.STUDENT_REG_SUCCESS,
					((StudentRegistResponse) response).getmRegStudentRsp(), handler);
		}
		else if (response instanceof ParentRegistResponse) 
		{
			handleresponse(Constans.ADULT_REG_SUCCESS,
					((ParentRegistResponse) response).getmRegParentRsp(), handler);
		}
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response, Handler handler) {
		if (response instanceof StudentRegistResponse) 
		{
			handleresponse(Constans.STUDENT_REG_FAILED,
					((StudentRegistResponse) response).getmRegStudentRsp(), handler);
		}
		else if (response instanceof ParentRegistResponse) 
		{
			handleresponse(Constans.ADULT_REG_FAILED,
					((ParentRegistResponse) response).getmRegParentRsp(), handler);
		}
		return super.onFailedResponse(response, handler);
	}

	@Override
	public void stuRegist(String phoneNo, String realName, String studentId,
			String password, AreaInfo areainfo, SchoolCombine schoolInfo,
			ClassCombine classinfo, Handler handler) {
		StudentRegistRequest request = new StudentRegistRequest();
		request.setRegProfile(phoneNo, realName, studentId, password,
				areainfo.getmAreaName(), schoolInfo.getSchoolId(),
				classinfo.getClassId());

		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void parRegist(String phoneNo, String realName, String idCardNo,
			String password, List<Long> childIdList, String verifyCode,
			Handler handler) {
		ParentRegistRequest request = new ParentRegistRequest();
		request.setRegProfile(phoneNo, realName, idCardNo, password, childIdList, verifyCode);
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

}
