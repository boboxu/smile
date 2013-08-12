package com.heme.logic.managers.registmanager;

import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.common.Constans;
import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.httpprotocols.regist.ParentRegistRequest;
import com.heme.logic.httpprotocols.regist.ParentRegistResponse;
import com.heme.logic.httpprotocols.regist.StudentRegistRequest;
import com.heme.logic.httpprotocols.regist.StudentRegistResponse;
import com.heme.logic.httpprotocols.userinfo.updateuserinfo.UpdateUserInfoRequest.SEXTYPE;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.Data.ClassCombine;
import com.heme.logic.module.Data.SchoolCombine;
import com.heme.logic.module.notpbmessage.AreaInfo;
import com.heme.utils.StringUtil;

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
	private List<String> mChildIdList;
	private SEXTYPE mSexType;
	private BaseBusinessRequest request = null;

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		if (response instanceof StudentRegistResponse) {
			StudentRegistResponse stuResponse = (StudentRegistResponse) response;
			if (stuResponse.getmRegStudentRsp().getErrCode() == 0) {
				handleresponse(Constans.STUDENT_REG_SUCCESS,
						stuResponse.getmRegStudentRsp(), handler);
			} else {
				handleresponse(Constans.STUDENT_REG_FAILED,
						stuResponse.getmRegStudentRsp(),
						handler);
			}

		} else if (response instanceof ParentRegistResponse) {
			ParentRegistResponse prResponse = (ParentRegistResponse) response;
			if (prResponse.getmRegParentRsp().getErrCode() == 0) {
				handleresponse(Constans.ADULT_REG_SUCCESS,
						prResponse.getmRegParentRsp(),
						handler);
			} else {
				handleresponse(Constans.ADULT_REG_FAILED,
						prResponse.getmRegParentRsp(),
						handler);
			}

		}
	}

	@Override
	protected BaseError onFailedResponse(BaseResponse response, Handler handler) {
		if (response instanceof StudentRegistResponse) {
			handleresponse(Constans.STUDENT_REG_FAILED,
					((StudentRegistResponse) response).getmRegStudentRsp(),
					handler);
		} else if (response instanceof ParentRegistResponse) {
			handleresponse(Constans.ADULT_REG_FAILED,
					((ParentRegistResponse) response).getmRegParentRsp(),
					handler);
		}
		return super.onFailedResponse(response, handler);
	}

	@Override
	public void setStuRegInfo(String realName, String studentId,
			String password,SEXTYPE sex,AreaInfo areainfo, SchoolCombine schoolInfo,
			ClassCombine classinfo) {
		request = new StudentRegistRequest();
		mRealName = realName;
		mStudentId = studentId;
		mPassword = password;
		mAreaInfo = areainfo;
		mSchoolInfo = schoolInfo;
		mClassInfo = classinfo;
		mSexType = sex;
	}

	@Override
	public void setParRegInfo(String realName, String idCardNo,
			String password, SEXTYPE sex,List<String> childIdList) {
		request = new ParentRegistRequest();
		mRealName = realName;
		mIdCardNo = idCardNo;
		mPassword = password;
		mChildIdList = childIdList;
		mSexType = sex;
	}

	@Override
	public void startReg(String verifyCode, Handler handler) {
		if (request instanceof ParentRegistRequest) {
			// 家长
			((ParentRegistRequest) request).setRegProfile(mPhoneNo, mRealName,
					mIdCardNo, StringUtil.MD5Encode(mPassword),mSexType, mChildIdList, verifyCode);
		} else {
			// 学生
			((StudentRegistRequest) request).setRegProfile(mPhoneNo, mRealName,
					mStudentId, StringUtil.MD5Encode(mPassword),mSexType, mAreaInfo,
					mSchoolInfo.getSchoolId(), mClassInfo.getClassId(),verifyCode);
		}
		sendRequest(request, handler, getClass().getName(), _FUNC_());
	}

	@Override
	public void setPhoneNum(String phoneNo) {
		mPhoneNo = phoneNo;
	}
}
