package com.heme.commonlogic.servermanager;

import android.util.Log;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.module.Trans.TransProto;
import com.heme.utils.ByteUtil;

public class BasePbResponse extends BaseResponse {
	private static final String TAG = "BasePbResponse";
	protected TransProto mTransData;
	protected TransProto.Builder mTransDataBuilder;
	
	public BasePbResponse()
	{
		mTransDataBuilder = TransProto.newBuilder();
		mTransData = null;
	}
	
	public void setmTransData(TransProto mTransData) {
		this.mTransData = mTransData;
	}

	@Override
	public void parseData() throws InvalidProtocolBufferException
	{
		if (mTransData == null) 
		{
			super.parseData();
			mTransData = TransProto.parseFrom(mRespData);
		}
	}
	
	public static TransProto parseByteToTransProto(byte[] buffer) throws InvalidProtocolBufferException
	{
//		int length = ByteUtil.byteArrayToInt(buffer, 0);
//		if (length + 4 != buffer.length) {
//			Log.e(TAG, "网络回包的长度，数据不正确");
//			return null;
//		}
//		// Length占四个字节，后面的都是数据
//		byte[] _respData = new byte[length];
//		for (int i = 0; i < _respData.length; i++) {
//			_respData[i] = buffer[4 + i];
//		}
		
		TransProto ret;
		ret = TransProto.parseFrom(buffer);
		return ret;
	}
}
