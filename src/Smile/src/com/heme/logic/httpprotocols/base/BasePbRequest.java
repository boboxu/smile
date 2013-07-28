package com.heme.logic.httpprotocols.base;

import com.google.protobuf.ByteString;
import com.heme.commonlogic.servermanager.BaseRequest;
import com.heme.logic.module.Access.AccessReq;

public class BasePbRequest extends BaseRequest {
	//实现AccessReq数据的组合
	protected AccessReq.Builder mAccessReqDataBuilder;
	
	protected void setBody(ByteString body) {
		mAccessReqDataBuilder.setBytesBody(body);
		 mAccessReqDataBuilder.build().toByteArray();
	}
	
	protected void setUid(long uid) {
		mAccessReqDataBuilder.setUint64Uid(uid);
	}
	
	protected void setSeqId(int seq) {
		mAccessReqDataBuilder.setUint32Seq(seq);
	}
	
	protected void setCmd(String cmd) {
		mAccessReqDataBuilder.setStrCmd(cmd);
	}
	
	
}
