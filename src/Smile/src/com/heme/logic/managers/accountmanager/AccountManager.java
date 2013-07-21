package com.heme.logic.managers.accountmanager;
import com.heme.commonlogic.logicmanager.IBaseLogicManagerListener;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.foundation.error.BaseError;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.CurrentAccount;
import com.heme.logic.module.SmileProtos;

public class AccountManager extends BaseBusinessLogicManager{
	public CurrentAccount getCurrentAccount()
	{
		//todo 从数据库读取
		return new CurrentAccount();
	}

	public void setCurrentAccount(CurrentAccount account)
	{
		
	}
	
	public void removeCurrentAccount()
	{
		
	}
	
	@Override
	protected void onSuccessResponse(BaseResponse response,
			IBaseLogicManagerListener listener) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected BaseError onFailedResponse(BaseResponse response,
			IBaseLogicManagerListener listener) {
		// TODO Auto-generated method stub
		return super.onFailedResponse(response, listener);
	}
}
