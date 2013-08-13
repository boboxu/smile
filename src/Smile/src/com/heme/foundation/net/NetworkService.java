package com.heme.foundation.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

import com.heme.logic.httpprotocols.heartbeat.HeartBeatRequest;
import com.heme.smile.StartActivity;
import com.heme.utils.ByteUtil;
import com.heme.utils.StringUtil;

/**
 * 
 * 负责长连接的PushService
 *
 */
public class NetworkService extends Service
{
	private static final String TAG = "NetworkService";
	
	public static final String HOST = "202.96.170.123";
	public static final int PORT = 18080;
	
	private static final String CLIENT_ID = "smile";
	private static final String ACTION_START = CLIENT_ID + ".START";
	private static final String ACTION_STOP = CLIENT_ID + ".STOP";
	private static final String ACTION_KEEPALIVE = CLIENT_ID + ".KEEP_ALIVE";
	private static final String ACTION_RECONNECT = CLIENT_ID + ".RECONNECT";
	
	private static final String PREF_STARTED = "isStarted";
	private static final String PREF_RETRY = "retryInterval";
	
	public static final String NOTIF_TITLE = "Smile"; 	

	private ConnectivityManager mConnManager;
	
	private NotificationManager mNotifManager;
	
	private boolean mStarted;
	
	private ConnectionThread mConnectionThread;
	
	//让AlarmManager定时唤醒，5分钟
	private static final long KEEP_ALIVE_INTERVAL = 1000 * 60 * 5;
	
	//重试间隔
	private static final long INITIAL_RETRY_INTERVAL = 1000 * 5;
	private static final long MAX_RETRY_INTERVAL = 1000 * 60 * 30;
	
	private long mStartTime;
	private SharedPreferences mPrefs;
	
    private static final int NOTIF_CONNECTED = 0;
    
    public interface INetworkServiceListener 
    {
    	//连接上了
    	public void onConnceted(ConnectionThread thread);
    	
    	//收到数据
    	public void onRecvData(byte[] recvBuffer);
    	
    	//需要发心跳包
    	public void onNeedBeatHeart();
    	
    	//连接超时
    	public void onConnectTimeout();
    	
    	//连接失败
    	public void onConnectFailed();
    }

	//开启service的静态方法
	public static void actionStart(Context context)
	{
		Intent intent = new Intent(context, NetworkService.class);
		intent.setAction(ACTION_START);
		context.startService(intent);
	}
	
	//stop service
	public static void actionStop(Context context)
	{
		Intent intent = new Intent(context, NetworkService.class);
		intent.setAction(ACTION_STOP);
		context.startService(intent);
	}
	
	public static void actionPing(Context context)
	{
		Intent intent = new Intent(context, NetworkService.class);
		intent.setAction(ACTION_KEEPALIVE);
		context.startService(intent);
	}
	
	@Override
	public void onCreate()
	{
		Log.d(TAG, "onCreate");
		super.onCreate();
		
		mStartTime = System.currentTimeMillis();
		mPrefs = getSharedPreferences(TAG, MODE_PRIVATE);
		
		mConnManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		mNotifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		//if reaped, restore it
		handleCrashedService();
		
	}
	
	//要被系统杀掉后恢复
	private void handleCrashedService()
	{
		if (wasStarted() == true)
		{
			Log.d(TAG, "Handling crashed service...");
			
			hideNotification();
			
			stopKeepAlives();
			
			start();
		}
	}
	
	@Override
	public void onDestroy()
	{
		Log.d(TAG, "onDestroy");
		
		if (mStarted == true)
		{
			stop();
		}
			
	}
	
	@Override
	public void onStart(Intent intent, int startId)
	{
		Log.d(TAG, "onStart");
		super.onStart(intent, startId);
		
		if (StringUtil.isEmptyOrNull(intent.getAction()))
		{
			Log.d(TAG, "intent action:" + intent.getAction());
		}
		
		if (intent.getAction().equals(ACTION_STOP) == true)
		{
			stop();
			stopSelf();
		}
		else if (intent.getAction().equals(ACTION_START) == true)
		{
			start();
		}
		else if (intent.getAction().equals(ACTION_KEEPALIVE) == true)
		{
			keepAlive();
		}
		else if (intent.getAction().equals(ACTION_RECONNECT) == true)
		{
			if (isNetworkAvailable())
			{
				reconnectIfNecessary();
			}
		}
	}
	

	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}

	private boolean wasStarted()
	{
		return mPrefs.getBoolean(PREF_STARTED, false);
	}
	
	private void setStarted(boolean started)
	{
		mPrefs.edit().putBoolean(PREF_STARTED, started).commit();
		mStarted = started;
	}
	
	//启动心跳线程
	private synchronized void start()
	{
		Log.d(TAG, "start service");
		
		if (mStarted == true)
		{
			Log.d(TAG, "service already started");
			return;
		}
		
		setStarted(true);
		
		//建立连接
		mConnectionThread = new ConnectionThread(HOST, PORT);
		mConnectionThread.start();
		
		//注册网络变化
		registerReceiver(mConnectivityChanged, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	}
	
	//终止心跳线程
	private synchronized void stop()
	{
		Log.d(TAG, "stop");
		
		setStarted(false);
		
		unregisterReceiver(mConnectivityChanged);
		
		//删除重连的timer
		cancelReconncet();
		
		//结束连接线程
		if (mConnectionThread != null)
		{
			mConnectionThread.abort();
			mConnectionThread = null;
		}
	}
	
	//调用connectThread发送心跳包
	private synchronized void keepAlive()
	{
		Log.d(TAG, "keepAlive");
		
		NetworkEngine.getEngine().onNeedBeatHeart();
//		try
//		{
//			if (mStarted == true && mConnectionThread != null)
//			{
//				mConnectionThread.sendHeartBeat();
//			}
//		} catch (Exception e)
//		{
//		}
	}
	
	// 通过AlarmManager定时唤醒
	private void startKeepAlives()
	{
		Intent intent = new Intent();
		intent.setClass(this, NetworkService.class);
		intent.setAction(ACTION_KEEPALIVE);
		
		PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + KEEP_ALIVE_INTERVAL,
				KEEP_ALIVE_INTERVAL, pendingIntent);	
	}
	
	// 停止AlarmManager唤醒
	private void stopKeepAlives()
	{
		Intent intent = new Intent();
		intent.setClass(this, NetworkService.class);
		intent.setAction(ACTION_KEEPALIVE);
		
		PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);
	}
	
	private void scheduleReconnect(long startTime)
	{
		long interval = mPrefs.getLong(PREF_RETRY, INITIAL_RETRY_INTERVAL);
		
		long now = System.currentTimeMillis();
		long elapsed = now - startTime;
		
		// Set an appropriate interval based on the elapsed time since start 
		if (elapsed <  interval)
		{
			interval = Math.min(interval * 4, MAX_RETRY_INTERVAL);
		}
		else 
		{
			interval = 	INITIAL_RETRY_INTERVAL;
		}
		
		Log.d(TAG, "Rescheduling connection in " + interval + "ms");
		
		mPrefs.edit().putLong(PREF_RETRY, interval).commit();
		
		Intent intent = new Intent();
		intent.setClass(this, NetworkService.class);
		intent.setAction(ACTION_RECONNECT);
		
		PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, now + interval, pendingIntent);
		
	}	
	
	private void cancelReconncet()
	{
		Intent intent = new Intent();
		intent.setClass(this, NetworkService.class);
		intent.setAction(ACTION_RECONNECT);
		
		PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);		
	}
	
	private synchronized void reconnectIfNecessary()
	{
		if (mStarted == true && mConnectionThread == null)
		{
			Log.d(TAG, "reconnecting...");
			
			mConnectionThread = new ConnectionThread(HOST, PORT);
			mConnectionThread.start();
		}
	}
	
	private BroadcastReceiver mConnectivityChanged = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent)
		{
			NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
			
			boolean hasConnectivity = (info != null && info.isConnected()) ? true : false;
			
			Log.d(TAG, "Connectivity changed: connected = " + hasConnectivity);
			
			if (hasConnectivity)
			{
				reconnectIfNecessary();
			}
			else if (mConnectionThread != null)
			{
				mConnectionThread.abort();
				cancelReconncet();
				mConnectionThread = null;
				
			}
			
		}
	};
	
	private void showNotification(String text)
	{
		Notification notification = new Notification();
		
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
				
		//notification.icon
		notification.when = System.currentTimeMillis();
		
		PendingIntent pendingIntent = PendingIntent.getActivity(this,
				0, new Intent(this, StartActivity.class), 0);
		
		notification.setLatestEventInfo(this, NOTIF_TITLE, text, pendingIntent);
		
		mNotifManager.notify(NOTIF_CONNECTED, notification);
		
	}
	
	private void hideNotification()
	{
		mNotifManager.cancel(NOTIF_CONNECTED);
	}
	
	private Boolean isNetworkAvailable()
	{
		NetworkInfo info = mConnManager.getActiveNetworkInfo();
		if (info == null)
		{
			return false;
		}
		return info.isConnected();
	}
	
	public class ConnectionThread extends Thread
	{
		private Socket mSocket = null;
		private InputStream mInputStream = null;
		private OutputStream mOutputStream = null;
		private final String mHost;
		private final int mPort;
		
		private volatile boolean mAbort = false;
		
		public ConnectionThread(String host, int port)
		{
			mHost = host;
			mPort = port;
		}
		
		public void run()
		{
						
			try
			{
				mSocket = new Socket();

				mSocket.connect(new InetSocketAddress(mHost, mPort), 20000);
				
				//初始化网络参数
				mSocket.setKeepAlive(true);
				mSocket.setSoTimeout((int)KEEP_ALIVE_INTERVAL + 120000);
				
				mInputStream = mSocket.getInputStream();
				mOutputStream = mSocket.getOutputStream();
				
				Log.d(TAG, "Connection established to " + mSocket.getInetAddress()
						+ ":" + mPort);
				
				startKeepAlives();
				showNotification("测试心跳");
				
				NetworkEngine.getEngine().onConnceted(this);
			}
			catch (SocketTimeoutException e) 
			{
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			long startTime = System.currentTimeMillis();
			
			try
			{
				while (isConnected())
				{
					byte[] recvBuf = recvBuffer();
					
					if (recvBuf != null)
					{
						Log.d(TAG, "recv buffer:" + recvBuf.toString());
						NetworkEngine.getEngine().onRecvData(recvBuf);
					}	
					else 
					{
						sleep(200);
					}
				}
				
			} catch (IOException e)
			{
				Log.e(TAG, "Unexpected I/O error: " + e.toString());
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				stopKeepAlives();
				hideNotification();
				
				if (mAbort == true)
					Log.d(TAG, "Connection aborted, shutting down.");
				else 
				{
					try
					{
						mSocket.close();
					} catch (IOException e){}
					
					synchronized (NetworkService.this)
					{
						mConnectionThread = null;
					}
					
					if (isNetworkAvailable() == true)
					{
						scheduleReconnect(startTime);
					}
				}
			}
		}
		
		public boolean isConnected()
		{
			if (mSocket == null)
			{
				return false;
			}
			return mSocket.isConnected();
		}
		
		public synchronized void sendHeartBeat() throws IOException
		{
//			Date date = new Date();
//			long currentTime = System.currentTimeMillis();
//			HeartBeatRequest heartbeatRequest = new HeartBeatRequest();
//			mOutputStream.write(heartbeatRequest.getmDataBuffer());
//			mOutputStream.flush();
			
//			Log.d(TAG, "send heartbeat:" + heartbeatRequest.getmDataBuffer().toString());
			//调用HeartManager
		}
		
		public synchronized void sendBuffer(byte[] buffer)
		{
			if (!isConnected())
			{
				reconnectIfNecessary();
			}
			
			Log.d(TAG, "send buffer:" + buffer.toString());

			try
			{
				mOutputStream.write(buffer);
				mOutputStream.flush();

			}
			catch (SocketTimeoutException e) 
			{
//				NetworkEngine.getEngine().
//				NetworkEngine.getEngine();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		protected byte[] recvBuffer() throws IOException
		{
			int count = mInputStream.available();
//			if (count > 0)
//			{
//				byte[] buffer = new byte[count];
//				mInputStream.read(buffer, 0, count);
//				return buffer;
//			}
			if (count > 0)
			{
				byte[] lengthBuf = new byte[4];
				mInputStream.read(lengthBuf, 0, 4);
				int recvLen = ByteUtil.byteArrayToInt(lengthBuf, 0);
				
				if (recvLen > 0)
				{
					byte[] recvBuf = new byte[recvLen];
					int readLen = 0;
					while (readLen < recvLen)
					{
						readLen += mInputStream.read(recvBuf, readLen, recvLen - readLen);
					}
					return recvBuf;
				}
			}
			return null;
		}
		
		public synchronized void abort()
		{
			Log.d(TAG, "Conncetion abort");
			mAbort = true;
			
			try
			{
				mSocket.shutdownOutput();
				
			} catch (Exception e){}
			
			try
			{
				mSocket.shutdownInput();

			} catch (Exception e){}
			
			try
			{
				mSocket.close();
			} catch (Exception e){}
			
			while (true)
			{
				try
				{
					join();
					break;
				} catch (Exception e){}
				
			}
		}
		
	}
	
}
