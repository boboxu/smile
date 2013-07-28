package com.heme.logic.managers.schoolinfomanager;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.common.Constans;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.notpbmessage.AreaInfo;

public class AreaInfoManager extends BaseBusinessLogicManager implements IAreaInfoManagerInterface{
	List<AreaInfo> mAreaInfoList;
	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		
	}

	public AreaInfoManager()
	{
		mAreaInfoList = new ArrayList<AreaInfo>();
		//本地读取地区配置信息
		mAreaInfoList.add(new AreaInfo("长沙", "0731"));
	}
	
	@Override
	public void getAllAreaInfo(Handler handler) 
	{
		handleresponse(Constans.GET_AREAINFO_SUCCESS, mAreaInfoList, handler);
	}
}
