package com.heme.smile.protocol.runner;

import java.util.List;


public interface IRunnerManager{
	
	public int startRunner(Runner bpRunenr);
	public void stopRunner(int runnerId);
	public void pauseRunner(int runnerId);
	public void resumeRunner(int runnerId);
	public void pauseAll();
	public void resumeAll();
	public void stopAll();
	public int getRunnerStatus(int runnerId);
	public List<RunnerInfo> getRunnerInfoList(List<Integer> runnerIdList);
	
}
