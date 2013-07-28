package com.heme.logic.managers.statusreportmanager;

import android.os.Handler;

import com.heme.logic.httpprotocols.updatestatus.UpdateStatusRequest.USERSTATUS;
import com.heme.logic.managers.base.IBaseBusinessLogicManagerInterface;

public interface IStatusReportManagerInterface extends IBaseBusinessLogicManagerInterface{
	public void setStatus(USERSTATUS status,Handler handler);
}
