package com.heme.foundation.net;

import java.io.IOException;

import android.os.Looper;

import com.ning.http.client.ListenableFuture;

public class NetworkManager implements INetworkManagerInterface
{
	protected static NetworkManager gNetworkManager = null;
	
	public static INetworkManagerInterface shareManager()
	{
		if (gNetworkManager == null) 
		{
			gNetworkManager = new NetworkManager();
		}
		
		return gNetworkManager;
	}

	@Override
	public Boolean sentNetworkRequest(NetworkRequest request) 
	{
		try
        {
			ListenableFuture<NetworkResponse> returnVal = null;
			request.mCallerLooper = Looper.myLooper();
			returnVal = request.makeURLConnectionConnection(null).prepareRequest(request.mRequest).execute(request.mHandler);
			if (returnVal == null)
            {
	            request.mHandler.onTimeOut(request);
            }
        }
        catch (IOException e)
        {
        	request.mHandler.onTimeOut(request);
        	e.printStackTrace();

        	return false;
        }
		catch (Exception e) {
			request.mHandler.onThrowable(e);
        	e.printStackTrace();

			return false;
		}
		return true;
    
	}

	@Override
	public void cancelNetworkRequest(NetworkRequest request) {
	    try
        {
	    	if (request.mConnectionClient != null && !request.mConnectionClient.isClosed())
            {
	    		request.mConnectionClient.close();
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }		
	}
}
