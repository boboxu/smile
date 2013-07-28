package com.heme.logic.managers.schoolinfomanager;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;
import com.heme.logic.module.notpbmessage.AreaInfo;

public interface ISchoolInfoManagerInterface extends IBaseBusinessLogicManagerInterface{
	public void getSchoolInfo(AreaInfo areainfo,Handler handler);
}
