package com.heme.logic.managers.statusreportmanager;

import android.os.Handler;

import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;
import com.heme.logic.module.Status.EStatus;

public interface IStatusReportManagerInterface extends IBaseBusinessLogicManagerInterface{
	public void setStatus(EStatus status,Handler handler);
}
