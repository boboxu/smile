package com.heme.smile.protocol.runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.heme.smile.protocol.runner.IRunner.IRunnerStatusChangedListener;


import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class RunnerManager implements IRunnerManager, IRunnerStatusChangedListener {
	private static final String TAG = "BpRunnerManager";
	
	private static final int RUNNER_STATUS_CHANGED = 100;
	
	public static final int MAX_RUNNING_COUNT = 5;
	private static final int MIN_RUNNER_ID = 10000;
	private static final int MAX_RUNNER_ID = 100000;
	
	private List<Runner> mRunnerList;
	private Map<Integer, Runner> mHashMap;
	
	private int mCurRunningCnt;
	private int mRunnerIdCreator;
	
	private boolean mIsNeedNextForSuccess;
	private boolean mIsNeedNextForFailed;
	private boolean mIsNeedNextForCancel;
	private boolean mIsNeedNextOnExit;
	
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case RUNNER_STATUS_CHANGED:
				mCurRunningCnt--;
				IRunner runner = (IRunner)msg.obj;
				removeRunner(runner);
				startNextRunnerIfNecessary();
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	public RunnerManager(){
		mRunnerList = new ArrayList<Runner>();
		mHashMap = new HashMap<Integer, Runner>();
		mCurRunningCnt = 0;
		mRunnerIdCreator = MIN_RUNNER_ID;
		mIsNeedNextForSuccess = true;
		mIsNeedNextForFailed = true;
		mIsNeedNextForCancel = true;
		mIsNeedNextOnExit = true;
	}
	public int startRunner(Runner bpRunner){
		Log.i(TAG, "startRunner-------------");
		int id = getRunnerId();
		bpRunner.setRunnerId(id);
		bpRunner.setOnStatusChangedListener(this);
		
		synchronized (this){
			mRunnerList.add(bpRunner);
			mHashMap.put(id, bpRunner);
		}
		startNextRunnerIfNecessary();
		return id;
	}
	
	private void startNextRunnerIfNecessary() {
		synchronized (this)	{
			if(mCurRunningCnt < MAX_RUNNING_COUNT){
				for(Runner runner : mRunnerList){
					if(runner.getStatus() == RunnerInfo.STATUS_WAITING){
						mCurRunningCnt++;
						runner.start();
						break;
					}
				}
			}
		}
	}
	public void stopRunner(int runnerId) {
		Log.i(TAG, "stopRunner " + runnerId);
		synchronized (this){
			Runner runner = mHashMap.get(runnerId);
			if(null != runner){
				if(runner.getStatus() == RunnerInfo.STATUS_RUNNING ||
						runner.getStatus() == RunnerInfo.STATUS_WAITING){
					if(runner.getStatus() == RunnerInfo.STATUS_RUNNING){
						mCurRunningCnt--;
					}
					removeRunner(runner);
					runner.stop();
				}
			}
		}
	}

	public void pauseRunner(int runnerId) {
		
	}

	public void resumeRunner(int runnerId) {
		
	}

	public void pauseAll() {
		
	}
	
	public void resumeAll() {
		
	}

	public void stopAll(){
		synchronized (this){
			for(Runner runner : mRunnerList) {
				runner.stop();
			}
			mRunnerList.clear();
			mHashMap.clear();
		}
	}

	public int getRunnerStatus(int runnerId) {
		Runner runner = mHashMap.get(runnerId);
		if(null != runner){
			return runner.getStatus();
		}
		return RunnerInfo.STATUS_UNSPCIFIED;
	}

	public List<RunnerInfo> getAllRunnerInfoList() {
		Log.i(TAG, "Current All RunnerInfo : \n");
		List<RunnerInfo> res = new ArrayList<RunnerInfo>();
		synchronized (this) {
			for(Runner runner : mRunnerList){
				Log.i(TAG, "runnerId = " + runner.getRunnerInfo().mId + 
						" runnerStatus = " + runner.getRunnerInfo().mStatus + "\n");
				res.add(runner.getRunnerInfo());
			}
		}
		return res;
	}
	
	public List<RunnerInfo> getRunnerInfoList(List<Integer> runnerIdList) {
		List<RunnerInfo> res = new ArrayList<RunnerInfo>();
		for(Integer id : runnerIdList){
			Runner runner = mHashMap.get(id);
			if(null != runner){
				Log.i(TAG, "runnerId = " + id + " runnerStatus = " + runner.getRunnerInfo().mStatus);
				res.add(runner.getRunnerInfo());
			}
		}
		return res;
	}

	protected void removeRunner(IRunner runner) {
		if(null != runner){
			synchronized (this){
				mRunnerList.remove(runner);
				mHashMap.remove(runner);
			}
		}
	}

	private int getRunnerId(){
		if(mRunnerIdCreator > MAX_RUNNER_ID){
			mRunnerIdCreator = MIN_RUNNER_ID;
		} else{
			mRunnerIdCreator++;
		}
		return mRunnerIdCreator;
	}
	
	public void onChange(int curStatus, int regionStatus, IRunner runner) {
		//�¼���������
		//��ʱĬ�ϴ���
		Log.i(TAG, "status onChange curStatus " + curStatus + " preStatus " + regionStatus);
		getAllRunnerInfoList();
		if((!mIsNeedNextForCancel && curStatus == RunnerInfo.STATUS_STOPED) ||
				(!mIsNeedNextForFailed && curStatus == RunnerInfo.STATUS_FAILED) ||
				(!mIsNeedNextForSuccess && curStatus == RunnerInfo.STATUS_SUCCEED)){
			return;
		}
		if(curStatus == RunnerInfo.STATUS_FAILED 
				|| curStatus == RunnerInfo.STATUS_STOPED
				|| curStatus == RunnerInfo.STATUS_SUCCEED){
//			mCurRunningCnt--;
//			removeRunner(runner);
//			startNextRunnerIfNecessary();
			Message msg = mHandler.obtainMessage(RUNNER_STATUS_CHANGED, runner);
			msg.sendToTarget();
		}
	}
}
