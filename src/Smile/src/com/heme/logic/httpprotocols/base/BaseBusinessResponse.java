package com.heme.logic.httpprotocols.base;

import com.heme.commonlogic.servermanager.BaseResponse;

public class BaseBusinessResponse extends BaseResponse{
	protected void parseData() 
	{
		//回来的数据格式化
		String dataString = new String(this.mDataBuffer.array());
		
	}
}
