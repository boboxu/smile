
package com.heme.foundation.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.heme.foundation.net.NetworkEngine.NetworkHandler;


/**
 * @author ottozheng
 *
 */
public class NetworkTask extends Thread
{
	protected static final String TAG = "NetworkTask";
	protected static final int CONTENT_READ_SIZE = 8192;
	protected static final int CONNECTTION_TIMEOUT = 10 * 1000;

	protected static final int SOCKET_RECV_TIMEOUT = 3 * 1000;
	protected static final int SOCKET_RECV_LOOP_INTERVAL = 200;
	
	protected NetworkRequest mRequest;
	protected NetworkHandler mHandler;
		
	protected boolean isCancel = false;
	
	/**
     * 
     */
    public NetworkTask(NetworkRequest request, NetworkHandler handler)
    {
    	mRequest = request;
    	mHandler = handler;
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		
        int requestId = mRequest.getmId();
        int statusCode = -1;
        String resultData = null;
        		
		Log.d(TAG, "start tcp request:" + mRequest.getmHost() + ":" + mRequest.getmPort()
				+ ",data:{" + mRequest.getmSendBytes().toString() + "}");
        
        try
		{
			Socket socket = new Socket(mRequest.getmHost(), mRequest.getmPort());
			
			initSocketParam(socket);
			//发送数据
			
			OutputStream ops = socket.getOutputStream();
			
			byte[] sendBytes = mRequest.getmSendBytes();
			ops.write(sendBytes);
			ops.flush();
			
			//接收数据
			
			InputStream ips = socket.getInputStream();
			
			byte[] recvBytes;
			recvBytes = recvData(ips);
					
			socket.close();

			if (recvBytes != null)
			{
				Log.d(TAG, recvBytes.toString());
				
		        Bundle bundle = new Bundle();
		        bundle.putInt(NetworkHandler.BUNDLE_KEY_REQUEST_ID, requestId);
		        bundle.putInt(NetworkHandler.BUNDLE_KEY_STATUS_CODE, 0);
		        bundle.putBoolean(NetworkHandler.BUNDLE_KEY_SUCCESS, true);
		        bundle.putByteArray(NetworkHandler.BUNDLE_KEY_DATA, recvBytes);
				
		        notifyRusultData(bundle);
		        
		        return;
			}
			else 
			{
				statusCode = NetworkResponse.STATUS_CODE_RECV_TIMEOUT;
			}
	        
			
		} catch (UnknownHostException e)
		{
        	statusCode = NetworkResponse.STATUS_CODE_UNKNOW_HOST;
			e.printStackTrace();
		} catch (SocketTimeoutException e) 
		{
        	statusCode = NetworkResponse.STATUS_CODE_CONNECT_TIMEOUT;
			e.printStackTrace();
		} catch (IOException e)
		{
			statusCode = NetworkResponse.STATUS_CODE_CONNECT_FAILED;
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
        
		//回调通知错误
        Bundle bundle = new Bundle();
        bundle.putInt(NetworkHandler.BUNDLE_KEY_REQUEST_ID, requestId);
        bundle.putInt(NetworkHandler.BUNDLE_KEY_STATUS_CODE, statusCode);
        bundle.putBoolean(NetworkHandler.BUNDLE_KEY_SUCCESS, false);
        bundle.putString(NetworkHandler.BUNDLE_KEY_DATA, resultData);

        notifyRusultData(bundle);

	}
	
	
	
	/**
	 * 初始化网络参数
	 */
	protected void initSocketParam(Socket socket) throws Exception
    {
		socket.setKeepAlive(true);
		socket.setSoTimeout(CONNECTTION_TIMEOUT);
    }
	
	/**
	 * 循环读取请求返回数据
	 * @param response
	 * @return
	 */
	protected String readContent(InputStream inputStream)
	{
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
	        StringBuffer contentBuffer = new StringBuffer();
	        	        
			char[] buffer = new char[CONTENT_READ_SIZE];
			
			while ((in.read(buffer)) != -1)
	        {
	            if (checkCancel())
	            {
	                break;
	            }
	            
	            contentBuffer.append(buffer);
	        }
			
			in.close();
			
			return contentBuffer.toString();
        }
        catch (IllegalStateException e)
        {
	        e.printStackTrace();
        }
        catch (IOException e)
        {
	        e.printStackTrace();
        }
        
        return null;
	}
	
	/**
	 * 循环读取数据流
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inStream) throws Exception
	{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[CONTENT_READ_SIZE];
			
		int len = -1;
		if ((len = inStream.read(buffer)) != -1)
		{
			outStream.write(buffer, 0, len);
		} 
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}
	
	public static byte[] recvData(InputStream inputStream) throws Exception
	{
		
		int count = 0;
		int times = 0;
		while (count == 0)
		{
			count = inputStream.available();
			sleep(SOCKET_RECV_LOOP_INTERVAL);
			if (times >= SOCKET_RECV_TIMEOUT / SOCKET_RECV_LOOP_INTERVAL)
			{
				return null;
			}
		}
		
		byte[] buffer = new byte[count];	
		inputStream.read(buffer);
		
		return buffer;
		
	}

	/**
	 * 通知handler
	 * @param bundle
	 */
    protected void notifyRusultData(Bundle bundle)
    {
    	
    	
        if (mHandler != null)
        {
            Message msg = new Message();
            msg.setData(bundle);
            mHandler.sendMessage(msg);
        }
        else
        {
        	Log.w(TAG, "the handler is null");
        }
    }
	
	/**
	 * 检查是否被取消
	 * @return
	 */
	protected boolean checkCancel() {
		if(isCancel)
		{
			//关闭连接 TODO
		}
		return isCancel;
	}

	/**
	 * @return the isCancel
	 */
	public boolean isCancel()
	{
		return isCancel;
	}

	/**
	 * @param isCancel the isCancel to set
	 */
	public void setCancel(boolean isCancel)
	{
		this.isCancel = isCancel;
	}
}
