package com.heme.foundation.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkSocket
{
	protected static final String TAG = "NetworkTask";
	protected static final int CONTENT_READ_SIZE = 8192;
	protected static final int CONNECTTION_TIMEOUT = 10 * 1000;
	
	public static final String HOST = "202.96.170.123";
	public static final int PORT = 18080; 
	
	protected Socket gSocket = null;
	protected InputStream gInputStream = null;
	protected OutputStream gOutputStream = null;
	
	protected static NetworkSocket gNetworkSocket = null;
	
	public static NetworkSocket shareInstace()
	{
		if (gNetworkSocket == null)
		{
			gNetworkSocket = new NetworkSocket();
		}
		return gNetworkSocket;
	
	}
	
	public void initSocket(String host, int port)
	{
		try
		{
			gSocket = new Socket(host, port);
			
			//初始化网络参数
			gSocket.setKeepAlive(true);
			gSocket.setSoTimeout(CONNECTTION_TIMEOUT);
			
			gInputStream = gSocket.getInputStream();
			gOutputStream = gSocket.getOutputStream();
			
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 初始化网络参数
	 */
	protected void initSocketParam(Socket socket) throws IOException
    {
		socket.setKeepAlive(true);
		socket.setSoTimeout(CONNECTTION_TIMEOUT);
    }
	
	public void sendBuffer(byte[] bytes)
	{
		
	}
	
	
	
	
}
