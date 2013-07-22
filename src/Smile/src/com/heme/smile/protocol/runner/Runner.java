package com.heme.smile.protocol.runner;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


import android.util.Log;

public class Runner implements IRunner {
	private static final String TAG = "BpRunner";
	private RunnerInfo mRunnerInfo;
	private IRunnerStatusChangedListener mRunnerStatusChangedListener;
	private Future mBpFuture;
	
	//�̳߳�
	private static final int CORE_POOL_SIZE = 5;
	private static final int MAXIMUM_POOL_SIZE = 128;
	private static final int KEEP_ALIVE = 10;
	
    private static final BlockingQueue<Runnable> sWorkQueue =
            new LinkedBlockingQueue<Runnable>(10);
    
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    };
    
	private static final ThreadPoolExecutor sExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
			MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, sWorkQueue, sThreadFactory);
	
	public Runner() {
		mRunnerInfo = new RunnerInfo();
		mRunnerInfo.mStatus = RunnerInfo.STATUS_WAITING;
	}
	
	public void start()	{
		Log.i(TAG, "start------------");
		if(null != mBpFuture){
			sExecutor.execute(mBpFuture);
			setStatus(RunnerInfo.STATUS_RUNNING);
		}
	}

	public void stop() {
		Log.i(TAG, "BpRunner stop()");
		if(null != mBpFuture) {
			mBpFuture.cancel();
			setStatus(RunnerInfo.STATUS_STOPED);
		}
		
	}

	public void pause()	{

	}

	public void resume() {

	}

	public int getStatus() {
		return mRunnerInfo.mStatus;
	}

	public RunnerInfo getRunnerInfo() {
		return mRunnerInfo;
	}
	
	public int getRunnerId() {
		return mRunnerInfo.mId;
	}
	void setRunnerId(int id) {
		mRunnerInfo.mId = id;
	}
	
	public void setStatus(int curStatus) {
		int temp = mRunnerInfo.mStatus;
		if(mRunnerInfo.mStatus != curStatus) {
			mRunnerInfo.mStatus = curStatus;
			if(null != mRunnerStatusChangedListener) {
				mRunnerStatusChangedListener.onChange(mRunnerInfo.mStatus, temp, this);
			}
		}
	}
	
	public void setOnStatusChangedListener(IRunnerStatusChangedListener listener){
		mRunnerStatusChangedListener = listener;
	}

	public void setFuture(Future future){
		mBpFuture = future;
	}
}
