package com.heme.logic.managers.registmanager;

import java.util.List;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;
import com.heme.logic.module.Data.ClassCombine;
import com.heme.logic.module.Data.SchoolCombine;
import com.heme.logic.module.notpbmessage.AreaInfo;

public interface IRegistManagerInterface extends
		IBaseBusinessLogicManagerInterface {
	
	public void startReg(String verifyCode,Handler handler);
	public void setParRegInfo(String realName,String idCardNo,String password,List<String> childIdList);
	public void setStuRegInfo(String realName,String studentId,String password,AreaInfo areainfo,SchoolCombine schoolInfo,ClassCombine classinfo);
	public void setPhoneNum(String phoneNo);
}
