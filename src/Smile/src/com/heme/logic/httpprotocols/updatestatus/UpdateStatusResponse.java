package com.heme.logic.httpprotocols.updatestatus;

import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.SetSignatureRsp;

public class UpdateStatusResponse extends BaseBusinessResponse {
	SetSignatureRsp mSetSignatureRsp;

	public SetSignatureRsp getmGetGroupInfoRsp() {
		return mSetSignatureRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mSetSignatureRsp = SetSignatureRsp.parseFrom(mTransData
				.getBytesBody());
	}
}
