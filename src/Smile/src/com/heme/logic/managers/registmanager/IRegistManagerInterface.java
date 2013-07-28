package com.heme.logic.managers.registmanager;

import java.util.List;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;
import com.heme.logic.module.Data.ClassCombine;
import com.heme.logic.module.Data.SchoolCombine;
import com.heme.logic.module.notpbmessage.AreaInfo;

public interface IRegistManagerInterface extends
		IBaseBusinessLogicManagerInterface {
	
	public void stuRegist(String phoneNo,String realName,String studentId,String password,AreaInfo areainfo,SchoolCombine schoolInfo,ClassCombine classinfo,Handler handler);
	public void parRegist(String phoneNo,String realName,String idCardNo,String password,List<Long> childIdList,String verifyCode,Handler handler);
}
