package com.heme.foundation.net;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.HttpResponseBodyPart;
import com.ning.http.client.HttpResponseHeaders;
import com.ning.http.client.HttpResponseStatus;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;
import com.ning.http.util.UTF8UrlEncoder;

public class NetworkRequest 
{
	public static final int UNKNOWN_LENGTH = -1;

	public String mUrl = null;
	public String mMethod = null;
	
	public Request mRequest = null;
	public NetworkHandler mHandler = null;
	public Looper mCallerLooper = null;
	public INetworkManagerDelegate mDelegate = null;
	public AsyncHttpClient mConnectionClient = null;
	
	public int mReceivedLength = 0;
	public int mContentLength = UNKNOWN_LENGTH;
	
	public Map<String, String> mDataDict = null;
	public Dictionary<String, NetworkBinaryData> mBinaryDataArray = null;
	public Dictionary<String, NetworkFileData> mFileDataArray = null;
	public Dictionary<String, String> mCookieDict = null;
	public Dictionary<String, String> mHeaderDict = null;

	public class NetworkBinaryData
	{
		ByteBuffer data = null;
		String filename = null;
		String contentType = null;
		String key = null;
	}

	public class NetworkFileData
	{
		String filepath = null;
		String filename = null;
		String contentType = null;
		String key = null;
	}
	
	public static class NetworkHandler extends
	        AsyncCompletionHandler<NetworkResponse>
	{
		public NetworkRequest mRequest = null;
		public NetworkResponse mResponse = null;
		private static final String TAG = "NetworkHandler";

		enum AsynHttpMsgHandlerType
		{
			AHMHT_StatusReceived, AHMHT_HeadersReceived, AHMHT_BodyPartReceived, AHMHT_Completed, AHMHT_TimeOut, AHMHT_Throwable
		}

		protected static void onLooperStatusReceived(NetworkHandler hd,
		        final HttpResponseStatus status)
		{
		}

		protected static void onLooperHeadersReceived(NetworkHandler hd,
		        final HttpResponseHeaders headers)
		{
			try
			{
				Map<String, List<String>> headMap = headers.getHeaders();
				String len = headMap.get("Content-Length").get(0);
				if (len != null)
				{
					hd.mRequest.mReceivedLength = 0;
					hd.mRequest.mContentLength = Integer.parseInt(len);
				}
			}
			catch (Exception e)
			{
				hd.mRequest.mReceivedLength = 0;
				hd.mRequest.mContentLength = UNKNOWN_LENGTH;
			}
		}

		protected static void onLooperBodyPartReceived(NetworkHandler hd,
		        final HttpResponseBodyPart content)
		{
			//TODO
//			if (hd.mRequest != null && hd.mRequest.mDelegate != null)
//			{
//				INetworkManagerDelegate delegate = hd.mRequest.mDelegate;
//				if (delegate instanceof INetworkManagerDelegateOptional)
//				{
//					INetworkManagerDelegateOptional delegateOptional = (INetworkManagerDelegateOptional) delegate;
//					hd.mRequest.mReceivedLength += content.length();
//					delegateOptional
//					        .onRequestProgress(
//					                hd.mRequest,
//					                hd.mRequest.mContentLength == UNKNOWN_LENGTH ? 1000000
//					                        : hd.mRequest.mContentLength,
//					                hd.mRequest.mReceivedLength
//					                );
//				}
//			}
		}

		protected static void onLooperCompleted(NetworkHandler hd,
		        NetworkResponse response)
		{
			if (hd.mRequest != null && hd.mRequest.mDelegate != null)
			{
				// String string = new
				// String(response.getResponseBodyAsBytes());
				INetworkManagerDelegate delegate = hd.mRequest.mDelegate;
				try
				{
					if (hd.mResponse.mResponse == null)
					{
						delegate.onRequestFail(hd.mRequest, 400);
					}
					else if (hd.mResponse.mResponse.getStatusCode() >= 400
					        || hd.mResponse.mResponse.getStatusCode() < 0)
					{
						delegate.onRequestFail(hd.mRequest,
						        hd.mResponse.mResponse.getStatusCode());
					}
					else
					{
						delegate.onRequestSuccess(hd.mRequest, ByteBuffer
						        .wrap(hd.mResponse.mResponse
						                .getResponseBodyAsBytes()));
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		protected static void onLooperTimeOut(NetworkHandler hd,
		        NetworkRequest request)
		{
			if (request != null && request.mDelegate != null)
			{
				INetworkManagerDelegate delegate = request.mDelegate;
				// "408" : Request Time-out
				delegate.onRequestFail(request, 408);
			}
		}
		
		protected static void onLooperThrowable(NetworkHandler hd,
		        int errorCode)
		{
			if (hd.mRequest != null && hd.mRequest.mDelegate != null)
			{
				INetworkManagerDelegate delegate = hd.mRequest.mDelegate;
				if(delegate != null)
				{
					delegate.onRequestFail(hd.mRequest, errorCode);
				}
			}
		}

		@SuppressLint("HandlerLeak")
		protected class AsynHttpMsgHandler extends Handler
		{
			public AsynHttpMsgHandler(Looper looper)
			{
				super(looper);
			}

			public AsynHttpMsgHandlerType type = null;
			public HttpResponseStatus status = null;
			public HttpResponseHeaders headers = null;
			public HttpResponseBodyPart content = null;
			public NetworkResponse mResponse = null;
			public INetworkManagerDelegate mDelegate = null;
			public int mErrorCode = -1;

			@Override
			public void handleMessage(Message msg)
			{ // 处理消息
				if (msg.obj instanceof NetworkHandler)
				{
					NetworkHandler hd = (NetworkHandler) msg.obj;
					switch (type)
					{
					case AHMHT_StatusReceived:
						onLooperStatusReceived(hd, status);
						break;
					case AHMHT_HeadersReceived:
						onLooperHeadersReceived(hd, headers);
						break;
					case AHMHT_BodyPartReceived:
						onLooperBodyPartReceived(hd, content);
						break;
					case AHMHT_Completed:
						onLooperCompleted(hd, mResponse);
						break;
					case AHMHT_TimeOut:
						onLooperTimeOut(hd, mRequest);
						break;
					case AHMHT_Throwable:
						onLooperThrowable(hd, mErrorCode);
						break;

					default:
						break;
					}
				}
				
				msg.obj = null;
				msg = null;
			}
		}
		
		/**
		 * {@inheritDoc}
		 */
		public void onThrowable(Throwable t)
		{
			super.onThrowable(t);
			
			// 网络出错啦！
			Log.w(TAG, "Network Request onThrowable " + t.getLocalizedMessage());
			int errorCode = -1;
			
			//TODO
			/*
			if (t instanceof TimeoutException || t instanceof SocketTimeoutException ) 
			{
				errorCode = NetworkError.NETWORK_CONNECT_TIMEOUT;
            }
			//UnknownHostException,ConnectException,SSLHandshakeException
			else if (t instanceof ConnectionClosedException) 
			{
				errorCode = NetworkError.NETWORK_CONNECT_FAILED;
            }
			else 
			{
				errorCode = NetworkError.NETWORK_UNAVAILABLE;
			}*/
			
			if (mRequest.mCallerLooper != null)
			{
				AsynHttpMsgHandler handler = new AsynHttpMsgHandler(
				        mRequest.mCallerLooper);
				handler.type = AsynHttpMsgHandlerType.AHMHT_Throwable;
				handler.mErrorCode = errorCode;
	
				handler.removeMessages(0);
				Message m = handler.obtainMessage(1, 0, 0, this);
				handler.sendMessage(m);
			}
			else
			{
				onLooperThrowable(mRequest.mHandler, errorCode);
			}
		}

		/**
		 * {@inheritDoc}
		 */
		public STATE onStatusReceived(final HttpResponseStatus status)
		        throws Exception
		{
			STATE sts = super.onStatusReceived(status);
			if (mRequest.mCallerLooper != null)
			{
				AsynHttpMsgHandler handler = new AsynHttpMsgHandler(
				        mRequest.mCallerLooper);
				handler.type = AsynHttpMsgHandlerType.AHMHT_StatusReceived;
				handler.status = status;

				handler.removeMessages(0);
				Message m = handler.obtainMessage(1, 0, 0, this);
				handler.sendMessage(m);
			}
			else
			{
				onLooperStatusReceived(this, status);
			}
			return sts;
		}

		/**
		 * {@inheritDoc}
		 */
		public STATE onHeadersReceived(final HttpResponseHeaders headers)
		        throws Exception
		{
			STATE sts = super.onHeadersReceived(headers);
			if (mRequest.mCallerLooper != null)
			{
				AsynHttpMsgHandler handler = new AsynHttpMsgHandler(
				        mRequest.mCallerLooper);
				handler.type = AsynHttpMsgHandlerType.AHMHT_HeadersReceived;
				handler.headers = headers;

				handler.removeMessages(0);
				Message m = handler.obtainMessage(1, 0, 0, this);
				handler.sendMessage(m);
			}
			else
			{
				onLooperHeadersReceived(this, headers);
			}
			return sts;
		}

		public STATE onBodyPartReceived(final HttpResponseBodyPart content)
		        throws Exception
		{
			STATE sts = super.onBodyPartReceived(content);
			if (mRequest.mCallerLooper != null)
			{
				AsynHttpMsgHandler handler = new AsynHttpMsgHandler(
				        mRequest.mCallerLooper);
				handler.type = AsynHttpMsgHandlerType.AHMHT_BodyPartReceived;
				handler.content = content;

				handler.removeMessages(0);
				Message m = handler.obtainMessage(1, 0, 0, this);
				handler.sendMessage(m);
			}
			else
			{
				onLooperBodyPartReceived(this, content);
			}
			return sts;
		}

		@Override
		public NetworkResponse onCompleted(Response response) throws Exception
		{
			Dictionary<String, String> headers = new Hashtable<String, String>();
			if (response != null)
			{
				for (String key : response.getHeaders().keySet())
				{
					String valuesString = "";
					List<String> valueList = response.getHeaders().get(key);
					for (String value : valueList)
					{
						valuesString = String.format("%s %s", valuesString,
						        value);
					}
					headers.put(key, valuesString);
				}
				mResponse = new NetworkResponse(response.getStatusCode(),
				        headers);
			}
			else
			{
				mResponse = new NetworkResponse(400, headers);
			}

			mResponse.mRequest = mRequest;
			mResponse.mResponse = response;

			if (mRequest.mCallerLooper != null)
			{
				AsynHttpMsgHandler handler = new AsynHttpMsgHandler(
				        mRequest.mCallerLooper);
				handler.type = AsynHttpMsgHandlerType.AHMHT_Completed;
				handler.mResponse = mResponse;

				handler.removeMessages(0);
				Message m = handler.obtainMessage(1, 0, 0, this);
				handler.sendMessage(m);
			}
			else
			{
				onLooperCompleted(this, mResponse);
			}

			return mResponse;
		}

		/**
		 * 
		 * 会在外部调用
		 * @param request
		 */
		public void onTimeOut(NetworkRequest request)
		{
			if (mRequest.mCallerLooper != null)
			{
				AsynHttpMsgHandler handler = new AsynHttpMsgHandler(
				        mRequest.mCallerLooper);
				handler.type = AsynHttpMsgHandlerType.AHMHT_TimeOut;

				handler.removeMessages(0);
				Message m = handler.obtainMessage(1, 0, 0, this);
				handler.sendMessage(m);
			}
			else
			{
				onLooperTimeOut(this, request);
			}
		}
	}

	public NetworkRequest(String url, String method,
	        INetworkManagerDelegate delegate)
	{
		super();
		mUrl = new String(url);
		mMethod = new String(method);
		// Request r = new
		// RequestBuilder().setUrl(url).setMethod(method).build();
		mDelegate = delegate;
		// mRequest = r;
	}

	public NetworkRequest(String url, String method, Map<String, String> data,
			INetworkManagerDelegate delegate)
	{
		super();
		mUrl = new String(url);
		mMethod = new String(method);
		// Request r = new
		// RequestBuilder().setUrl(url).setMethod(method).build();
		mDelegate = delegate;
		// mRequest = r;
		mDataDict = data;
	}

	static byte[] newBytesByAppending(byte[] src, byte[] appended)
	{
		int len = 0;
		int pos = 0;
		if (src != null)
		{
			len += src.length;
		}
		if (appended != null)
		{
			len += appended.length;
		}
		if (len <= 0)
		{
			return null;
		}
		byte[] ret = new byte[len];
		if (src != null)
		{
			for (int i = 0; i < src.length; i++)
			{
				ret[pos + i] = src[i];
			}
			pos += src.length;
		}
		if (appended != null)
		{
			for (int i = 0; i < appended.length; i++)
			{
				ret[pos + i] = appended[i];
			}
			pos += appended.length;
		}
		return ret;
	}

	public AsyncHttpClient getConnectionClient()
	{
		return mConnectionClient;
	}

	public AsyncHttpClient makeURLConnectionConnection(
	        Object urlConnectionDelegate)
	{
		if (mConnectionClient != null)
		{
			mConnectionClient = null;
		}
		if (mRequest != null)
		{
			mRequest = null;
		}

		RequestBuilder builder = new RequestBuilder();
		// builder.setPerRequestConfig(perRequestConfig)
		if (mUrl != null)
		{
			builder = builder.setUrl(mUrl);
		}
		if (mMethod != null)
		{
			builder = builder.setMethod(mMethod);
		}
		if (mHeaderDict != null && !mHeaderDict.isEmpty())
		{
			Enumeration<String> keys = mHeaderDict.keys();
			while (keys.hasMoreElements())
			{
				String key = (String) keys.nextElement();
				String value = mHeaderDict.get(key);
				builder.setHeader(key, value);
			}
		}

		if (mCookieDict != null && !mCookieDict.isEmpty())
		{
			String cookie = null;
			Enumeration<String> keys = mCookieDict.keys();
			while (keys.hasMoreElements())
			{
				String key = (String) keys.nextElement();
				String value = mCookieDict.get(key);
				if (cookie == null)
				{
					cookie = String.format("%s=%s", key, value);
				}
				else
				{
					cookie = String.format("%s; %s=%s", cookie, key, value);
				}
			}
			builder.setHeader("Cookie", cookie);
		}

		boolean isOnlyStringKeyValue = (mBinaryDataArray == null || mBinaryDataArray
		        .isEmpty())
		        && (mFileDataArray == null || mFileDataArray.isEmpty());

		if (isOnlyStringKeyValue)
		{
			String dataString = null;
			if (mDataDict != null && !mDataDict.isEmpty())
			{
				Set<String> keySet = mDataDict.keySet();

				if ("GET".equalsIgnoreCase(mMethod))
				{
					Iterator<String> keyIterator = keySet.iterator();
					while (keyIterator.hasNext())
					{
						String key = (String) keyIterator.next();
						String value = mDataDict.get(key);
						key = UTF8UrlEncoder.encode(key);
						value = UTF8UrlEncoder.encode(value);
						/* 新服务器框架的参数采用 url/param1/value1/param2/value2 的方式 */
						if (dataString == null)
						{
							dataString = String.format("%s=%s", key, value);
						}
						else
						{
							dataString = String.format("%s&%s=%s", dataString,
							        key, value);
						}
					}
				}
				else
				{
					Iterator<String> keyIterator = keySet.iterator();
					while (keyIterator.hasNext())
					{
						String key = (String) keyIterator.next();
						String value = mDataDict.get(key);
						key = UTF8UrlEncoder.encode(key);
						value = UTF8UrlEncoder.encode(value);
						if (dataString == null)
						{
							dataString = String.format("%s=%s", key, value);
						}
						else
						{
							dataString = String.format("%s&%s=%s", dataString,
							        key, value);
						}
					}
				}
			}

			if (dataString != null)
			{
				if ("POST".equalsIgnoreCase(mMethod))
				{
					String charset = "utf-8";
					String header = String.format(
					        "application/x-www-form-urlencoded; charset=%s",
					        charset);
					builder.setHeader("Content-Type", header);
					builder.setBody(dataString);
					Log.i("NetworkRequest", "Post Request url = " + mUrl + ", data:" + dataString);
				}
				else
				{
					String newUrl = mUrl;
					
					if (newUrl.indexOf("?") >= 0)
					{
						newUrl = String.format("%s&%s", newUrl, dataString);
					} 
					else
					{

						newUrl = String.format("%s%s", newUrl, dataString);
					}
					builder = builder.setUrl(newUrl);
					Log.i("NetworkRequest", "Get Request url = " + newUrl);
				}
			}
		}
		else
		{
			// 有二进制数据，即使设置方法为HttpGet，也会强制修改为HttpPost
			builder.setMethod("POST");

			String charset = "utf-8";
			String stringBoundary = "0xKhTmLbOuNdArY";
			builder.setHeader("Content-Type", String.format(
			        "multipart/form-data; charset=%s; boundary=%s", charset,
			        stringBoundary));

			byte[] httpBodyData = null;
			String tmpString = String.format("--%s\r\n", stringBoundary);
			httpBodyData = newBytesByAppending(httpBodyData,
			        tmpString.getBytes());

			String endItemBoundary = String.format("\r\n--%s\r\n",
			        stringBoundary);
			String allItemsEndBoundary = String.format("\r\n--%s--\r\n",
			        stringBoundary);

			if (mDataDict != null && !mDataDict.isEmpty())
			{
				Set<String> keySet = mDataDict.keySet();
				Iterator<String> keyIterator = keySet.iterator();
				while (keyIterator.hasNext())
				{
					String key = (String) keyIterator.next();
					String value = mDataDict.get(key);
					String contentDipsotion = String
					        .format("Content-Disposition: form-data; name=\"%s\"\r\n\r\n",
					                key);
					httpBodyData = newBytesByAppending(httpBodyData,
					        contentDipsotion.getBytes());
					httpBodyData = newBytesByAppending(httpBodyData,
					        value.getBytes());
					if (keyIterator.hasNext() || mBinaryDataArray != null
					        || mFileDataArray != null)
					{
						// 还有数据要添加
						httpBodyData = newBytesByAppending(httpBodyData,
						        endItemBoundary.getBytes());
					}
				}

			}

			if (mBinaryDataArray != null && !mBinaryDataArray.isEmpty())
			{
				Enumeration<String> keys = mBinaryDataArray.keys();
				while (keys.hasMoreElements())
				{
					String key = (String) keys.nextElement();
					NetworkBinaryData value = mBinaryDataArray.get(key);
					tmpString = String
					        .format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\n",
					                key, value.filename);
					httpBodyData = newBytesByAppending(httpBodyData,
					        tmpString.getBytes());
					tmpString = String.format("Content-Type: %s\r\n\r\n",
					        value.contentType);
					httpBodyData = newBytesByAppending(httpBodyData,
					        tmpString.getBytes());
					httpBodyData = newBytesByAppending(httpBodyData,
					        value.data.array());
					if (keys.hasMoreElements() || mFileDataArray != null)
					{
						// 还有数据要添加
						httpBodyData = newBytesByAppending(httpBodyData,
						        endItemBoundary.getBytes());
					}
				}
			}

			if (mFileDataArray != null && !mFileDataArray.isEmpty())
			{
				Enumeration<String> keys = mFileDataArray.keys();
				while (keys.hasMoreElements())
				{
					String key = (String) keys.nextElement();
					NetworkFileData value = mFileDataArray.get(key);
					if (value.filepath != null && value.filepath.length() > 0)
					{
						ByteBuffer filedataBuffer = ByteBuffer.allocate(4096);
						byte[] fileBytes = null;
						{
							try
							{
								FileChannel fcin = null;
								fcin = new FileInputStream(value.filepath)
								        .getChannel();
								while (fcin.read(filedataBuffer) != -1)
								{
									// 读文件
									fileBytes = newBytesByAppending(fileBytes,
									        filedataBuffer.array());
								}
								if (fcin != null && fcin.isOpen())
								{
									fcin.close();
								}
								fcin = null;
							}
							catch (FileNotFoundException e)
							{
								e.printStackTrace();
							}
							catch (IOException e)
							{
								e.printStackTrace();
							}

						}
						tmpString = String
						        .format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\n",
						                key, value.filename);
						httpBodyData = newBytesByAppending(httpBodyData,
						        tmpString.getBytes());
						tmpString = String.format("Content-Type: %s\r\n\r\n",
						        value.contentType);
						httpBodyData = newBytesByAppending(httpBodyData,
						        tmpString.getBytes());
						httpBodyData = newBytesByAppending(httpBodyData,
						        fileBytes);
						fileBytes = null;
						if (keys.hasMoreElements())
						{
							// 还有数据要添加
							httpBodyData = newBytesByAppending(httpBodyData,
							        endItemBoundary.getBytes());
						}
					}
				}
			}

			httpBodyData = newBytesByAppending(httpBodyData,
			        allItemsEndBoundary.getBytes());
			builder.setBody(httpBodyData);

		}

		mRequest = builder.build();
		mHandler = new NetworkHandler();
		mHandler.mRequest = this;

		mConnectionClient = new AsyncHttpClient();
		return mConnectionClient;
	}

	public void setStringData(String data, String key)
	{
		if (mDataDict == null)
		{
			mDataDict = new HashMap<String, String>();
		}
		mDataDict.put(key, data);
	}

	public void setBinaryData(ByteBuffer data, String key)
	{
		this.setBinaryData(data, "file", null, key);
	}

	public void setBinaryData(ByteBuffer data, String fileName,
	        String contentType, String key)
	{
		NetworkBinaryData nd = new NetworkBinaryData();
		nd.data = data;
		nd.filename = fileName;
		nd.contentType = contentType;
		nd.key = key;
		if (mBinaryDataArray == null)
		{
			mBinaryDataArray = new Hashtable<String, NetworkRequest.NetworkBinaryData>();
		}
		mBinaryDataArray.put(key, nd);
	}

	public void setFileData(String filePath, String key)
	{
		this.setFileData(filePath, "file", null, key);
	}

	public void setFileData(String filePath, String fileName,
	        String contentType, String key)
	{
		NetworkFileData nd = new NetworkFileData();
		nd.filepath = filePath;
		nd.filename = fileName;
		nd.contentType = contentType;
		nd.key = key;
		if (mFileDataArray == null)
		{
			mFileDataArray = new Hashtable<String, NetworkRequest.NetworkFileData>();
		}
		mFileDataArray.put(key, nd);
	}

	public void setHeader(String value, String key)
	{
		if (mHeaderDict == null)
		{
			mHeaderDict = new Hashtable<String, String>();
		}
		mHeaderDict.put(key, value);
	}

	public void setCookie(String value, String key)
	{
		if (mCookieDict == null)
		{
			mCookieDict = new Hashtable<String, String>();
		}
		mCookieDict.put(key, value);
	}


}
