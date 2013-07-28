package com.heme.logic.managers.schoolinfomanager;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;
import com.heme.logic.module.Data.SchoolCombine;

public interface IClassInfoManagerInterface extends IBaseBusinessLogicManagerInterface {
	public void getClassInfo(SchoolCombine schoolInfo,Handler handler);
}
