
package com.heme.foundation.net;

import java.nio.ByteBuffer;

/**
 * @author ottozheng
 *
 */
public class NetworkResponse
{
	protected NetworkRequest mRequest;	
	protected int mStatusCode;
	protected ByteBuffer mDataBuffer;
	
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

	public ByteBuffer getmDataBuffer()
	{
		return mDataBuffer;
	}

	public void setmDataBuffer(ByteBuffer mDataBuffer)
	{
		this.mDataBuffer = mDataBuffer;
	}
	
	
	
}
