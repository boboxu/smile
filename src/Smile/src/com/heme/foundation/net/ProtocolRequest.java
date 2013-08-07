package com.heme.foundation.net;

public class ProtocolRequest
{
	protected int mSeqId;
	protected byte[] mProBuf;
	
	public ProtocolRequest(byte[] proBuf)
	{
		mProBuf = proBuf;
	}

	public int getmSeqId()
	{
		return mSeqId;
	}

	public void setmSeqId(int mSeqId)
	{
		this.mSeqId = mSeqId;
	}

	public byte[] getmProBuf()
	{
		return mProBuf;
	}

	public void setmProBuf(byte[] mProBuf)
	{
		this.mProBuf = mProBuf;
	}
	
	
	
}
