package com.heme.logic.httpprotocols.base;

import com.google.protobuf.ByteString;
import com.heme.commonlogic.servermanager.BaseRequest;
import com.heme.logic.module.Access.AccessReq;

public class BasePbRequest extends BaseRequest {
	// 实现AccessReq数据的组合
	protected AccessReq.Builder mAccessReqDataBuilder = AccessReq.newBuilder();

	private void setBody(ByteString body) {
		mAccessReqDataBuilder.setBytesBody(body);
	}

	private void setUid(long uid) {
		mAccessReqDataBuilder.setUint64Uid(uid);
	}

	private void setSeqId(int seq) {
		mAccessReqDataBuilder.setUint32Seq(seq);
	}

	private void setCmd(String cmd) {
		mAccessReqDataBuilder.setStrCmd(cmd);
	}

	protected void buildAccessReq(ByteString body) {
		setBody(body);
		setUid(0);
		setCmd("");
		setSeqId(0);
		setRequestData(mAccessReqDataBuilder.build().toByteArray());
	}
}
