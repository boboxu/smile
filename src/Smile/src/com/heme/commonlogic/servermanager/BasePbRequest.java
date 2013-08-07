package com.heme.commonlogic.servermanager;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.module.Trans.TransProto;

public class BasePbRequest extends BaseRequest {
	// 实现AccessReq数据的组合
	//构造数据的builder
	private TransProto.Builder mTransDataBuilder = TransProto.newBuilder();
	//服务器来的推送数据
	protected TransProto mTransData;
	private void setBody(ByteString body) {
		mTransDataBuilder.setBytesBody(body);
	}

	private void setUid(long uid) {
		mTransDataBuilder.setUint64Uid(uid);
	}

	private void setSeqId(int seq) {
		mTransDataBuilder.setUint32Seq(seq);
	}

	protected void setCmd(String cmd) {
		mTransDataBuilder.setStrCmd(cmd);
	}

	protected void buildAccessReq(ByteString body) {
		setBody(body);
		setUid(0);
		setSeqId(0);
		setRequestData(mTransDataBuilder.build().toByteArray());
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException
	{
		super.parseData();
		mTransData = TransProto.parseFrom(mReqData);
	}
}
