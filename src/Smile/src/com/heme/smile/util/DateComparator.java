package com.heme.smile.util;

import java.util.Comparator;

import com.heme.smile.NotificationActivity.NotificationData;
import com.heme.smile.testdata.Contacter;

public class DateComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		 String str1 = ((NotificationData)o1).date+"";
	     String str2 = ((NotificationData)o2).date+"";
	     return str2.compareTo(str1);
	}

}
