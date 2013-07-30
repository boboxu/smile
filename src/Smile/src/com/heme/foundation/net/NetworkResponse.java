
package com.heme.foundation.net;


/**
 * @author ottozheng
 *
 */
public class NetworkResponse
{
	protected NetworkRequest mRequest;	
	protected int mStatusCode;
	protected byte[] mDataBytes;
	
	/**
	 * 
	 */
    public NetworkResponse(NetworkRequest request, int status)
    {
    	mRequest = request;
    	mStatusCode = status;
    }

	/**
	 * @return the mRequest
	 */
	public NetworkRequest getmRequest()
	{
		return mRequest;
	}

	/**
	 * @param mRequest the mRequest to set
	 */
	public void setmRequest(NetworkRequest mRequest)
	{
		this.mRequest = mRequest;
	}

	/**
	 * @return the mStatusCode
	 */
	public int getmStatusCode()
	{
		return mStatusCode;
	}

	/**
	 * @param mStatusCode the mStatusCode to set
	 */
	public void setmStatusCode(int mStatusCode)
	{
		this.mStatusCode = mStatusCode;
	}

	public byte[] getmDataBytes()
	{
		return mDataBytes;
	}

	public void setmDataBytes(byte[] mDataBytes)
	{
		this.mDataBytes = mDataBytes;
	}
	
	
	
}
