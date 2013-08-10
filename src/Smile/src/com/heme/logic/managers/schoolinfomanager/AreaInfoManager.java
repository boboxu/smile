package com.heme.logic.managers.schoolinfomanager;

import java.util.ArrayList;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.module.notpbmessage.AreaInfo;

public class AreaInfoManager extends BaseBusinessLogicManager implements IAreaInfoManagerInterface{
	ArrayList<AreaInfo> mAreaInfoList;
	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		
	}

	public AreaInfoManager()
	{
		mAreaInfoList = new ArrayList<AreaInfo>();
		//本地读取地区配置信息
		mAreaInfoList.add(new AreaInfo("湖南", "1","长沙","11","长沙县","111"));
	}
	
	@Override
	public ArrayList<AreaInfo> getAllAreaInfo() 
	{
		return mAreaInfoList;
	}
}
