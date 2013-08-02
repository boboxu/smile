package com.heme.logic.managers.registmanager;

import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
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
	
	private String mPhoneNo = null;
	private String mRealName = null;
	private String mStudentId = null;
	private String mPassword = null;
	private String mIdCardNo = null;
	private AreaInfo mAreaInfo = null;
	private com.heme.logic.module.Data.SchoolCombine mSchoolInfo;
	private ClassCombine mClassInfo;
	private List<Long> mChildIdList;
	
	private BaseBusinessRequest request = null;
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
	public void setStuRegInfo(String phoneNo, String realName, String studentId,
			String password, AreaInfo areainfo, SchoolCombine schoolInfo,
			ClassCombine classinfo) {
		request = new StudentRegistRequest();


	}

	@Override
	public void setParRegInfo(String phoneNo, String realName, String idCardNo,
			String password, List<Long> childIdList) {
		request = new ParentRegistRequest();
		
	}

	@Override
	public void startReg(String verifyCode, Handler handler) {
		if (request instanceof ParentRegistRequest) 
		{
			//家长
			((ParentRegistRequest)request).setRegProfile(mPhoneNo, mRealName, mIdCardNo, mPassword, mChildIdList, verifyCode);
		}
		else
		{
			//学生
			((StudentRegistRequest)request).setRegProfile(mPhoneNo, mRealName, mStudentId, mPassword,
					mAreaInfo.getmAreaName(), mSchoolInfo.getSchoolId(),
					mClassInfo.getClassId());
		}
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}
}
