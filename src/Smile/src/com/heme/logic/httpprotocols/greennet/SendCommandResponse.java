package com.heme.logic.httpprotocols.greennet;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;

public class SendCommandResponse extends BaseBusinessResponse {
//	private PcCtrlRsp mPcCtrlRsp;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
//		mPcCtrlRsp = PcCtrlRsp.parseFrom(mAccessRespData.getBytesBody());
	}
	
//	public PcCtrlRsp getmPcCtrlRsp() {
////		return mPcCtrlRsp;
//	}
	
	
}
