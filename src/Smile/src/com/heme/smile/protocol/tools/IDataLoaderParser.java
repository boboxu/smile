package com.heme.smile.protocol.tools;

public abstract class IDataLoaderParser {
	protected int mErrCode;

	public int getError() {
		return mErrCode;
	}
	
	public abstract Object parse(byte []content);
}
