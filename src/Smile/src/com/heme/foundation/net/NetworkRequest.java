
package com.heme.foundation.net;


/**
 * @author ottozheng
 *
 */
public class NetworkRequest
{
	
	protected String mHost = null;
	protected int mPort = 0;

	protected int mId;
	protected INetworkEngineListener mDelegate = null;

	//发送的数据
	
	protected byte[] mSendBytes = null;
	
    public NetworkRequest(String host, int port, byte[] sendBytes, INetworkEngineListener delegate)
    {
    	mHost = host;
    	mPort = port;
    	mSendBytes = sendBytes;
	    mDelegate = delegate;
	    
	    mId = NetworkRequestIdGenerator.generateRequestId();
    }
    
	public String getmHost()
	{
		return mHost;
	}

	public void setmHost(String mHost)
	{
		this.mHost = mHost;
	}

	public int getmPort()
	{
		return mPort;
	}

	public void setmPort(int mPort)
	{
		this.mPort = mPort;
	}

	public INetworkEngineListener getmDelegate()
	{
		return mDelegate;
	}

	public void setmDelegate(INetworkEngineListener mDelegate)
	{
		this.mDelegate = mDelegate;
	}

	public byte[] getmSendBytes()
	{
		return mSendBytes;
	}

	public void setmSendBytes(byte[] mSendBytes)
	{
		this.mSendBytes = mSendBytes;
	}

	public void setmId(int mId)
	{
		this.mId = mId;
	}

	/**
	 * @return the mId
	 */
	public int getmId()
	{
		return mId;
	}
	
}
