package com.heme.logic.managers.pushmanager;

import android.os.Handler;

import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.managers.base.BaseBusinessLogicManager;

public class PushManager extends BaseBusinessLogicManager {

	@Override
	protected void onSuccessResponse(BaseResponse response, Handler handler) {
		
	}

	//收到MessageOpr，根据uint32_command命令号来判断交给哪个request来解析数据。
	//命令号 1.发送请求 2.发送确认 3.推送请求 4.推送确认 5.拉取请求 6.拉取结果 7.拉取未读列表请求 8.拉取未读列表结果
//	BaseMessageOprRequest.COMMANDTYPE
}
