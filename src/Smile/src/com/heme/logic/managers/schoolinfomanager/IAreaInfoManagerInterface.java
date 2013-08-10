package com.heme.logic.managers.schoolinfomanager;

import java.util.ArrayList;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;
import com.heme.logic.module.notpbmessage.AreaInfo;

public interface IAreaInfoManagerInterface extends IBaseBusinessLogicManagerInterface{
	public ArrayList<AreaInfo> getAllAreaInfo();
}
