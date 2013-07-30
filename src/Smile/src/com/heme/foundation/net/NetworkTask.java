
package com.heme.foundation.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
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
	protected static final int CONNECTTION_TIMEOUT = 20 * 1000;
	protected static final int SOCKET_TIMEOUT = 20 * 1000;
	protected static final int SOCKET_BUF_SIZE = 8192;
	
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
		initSocketParam();
	
        int requestId = mRequest.getmId();
        int statusCode = -1;
        boolean isSuccess = false;
        String resultData = null;
        		
		Log.d(TAG, "start tcp request:" + mRequest.getmHost() + ":" + mRequest.getmPort()
				+ ",data:{" + mRequest.getmSendBytes().toString() + "}");
        
        try
		{
			Socket socket = new Socket(mRequest.getmHost(), mRequest.getmPort());
			
			//发送数据
			
			OutputStream ops = socket.getOutputStream();
			
			byte[] sendBytes = mRequest.getmSendBytes();

			ops.write(sendBytes);
			
			ops.flush();
			
			//接收数据
			
			InputStream ips = socket.getInputStream();
			
			byte[] recvBytes;

			recvBytes = readStream(ips);
			
			Log.d(TAG, recvBytes.toString());
			
	        Bundle bundle = new Bundle();
	        bundle.putInt(NetworkHandler.BUNDLE_KEY_REQUEST_ID, requestId);
	        bundle.putInt(NetworkHandler.BUNDLE_KEY_STATUS_CODE, 0);
	        bundle.putBoolean(NetworkHandler.BUNDLE_KEY_SUCCESS, true);
	        bundle.putByteArray(NetworkHandler.BUNDLE_KEY_DATA, recvBytes);
			
	        notifyRusultData(bundle);

	        socket.close();
	        return;
	        
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
	        statusCode = -1;
	        isSuccess = false;
	        e.printStackTrace();
		}catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		//回调通知
        Bundle bundle = new Bundle();
        bundle.putInt(NetworkHandler.BUNDLE_KEY_REQUEST_ID, requestId);
        bundle.putInt(NetworkHandler.BUNDLE_KEY_STATUS_CODE, statusCode);
        bundle.putBoolean(NetworkHandler.BUNDLE_KEY_SUCCESS, isSuccess);
        bundle.putString(NetworkHandler.BUNDLE_KEY_DATA, resultData);

        notifyRusultData(bundle);

        //关闭连接:TODO
	}
	
	/**
	 * 初始化网络参数
	 */
	protected void initSocketParam()
    {

		//TODO
		
    }
	
	/**
	 * 发送请求
	 * @param request
	 * @return
	 * @throws IOException
	 */
	//TODO
	
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
