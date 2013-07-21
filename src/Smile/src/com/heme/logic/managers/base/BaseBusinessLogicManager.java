package com.heme.logic.managers.base;

import com.heme.commonlogic.logicmanager.BaseLogicManager;
import com.heme.commonlogic.logicmanager.IBaseLogicManagerListener;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;

public abstract class BaseBusinessLogicManager extends BaseLogicManager{
	@Override
	protected BaseError onFailedResponse(BaseResponse response,
	        IBaseLogicManagerListener delegate)
	{
		BaseError error = response.getmError();
		return error;
	}
}
