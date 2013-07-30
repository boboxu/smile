/*
 * NetworkRequestIdGenerator.java
 * TCMobileAnalyticsLibrary
 * 
 * Description: 
 * 
 * Created by ottozheng on 2013-4-16.  
 * Copyright (c) 2012 Tencent. All rights reserved.
 *
 * Change Logs:
 * 1.2013-4-16	ottozheng		create this file
 *
 */
package com.heme.foundation.net;

/**
 * @author ottozheng
 *
 */
public class NetworkRequestIdGenerator
{
	private static int counter = 1;
	private static int MAX_ID = 10000;
	
	/**
	 * 获得requestId
	 * @return
	 */
	public static int generateRequestId(){
		return (++counter)%MAX_ID+1;
	}
}
