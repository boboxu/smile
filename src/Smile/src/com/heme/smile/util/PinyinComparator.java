package com.heme.smile.util;

import java.util.Comparator;

import com.heme.smile.testdata.Contacter;

public class PinyinComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		 String str1 = PingYinUtil.getPingYin(((Contacter)o1).name);
	     String str2 = PingYinUtil.getPingYin(((Contacter)o2).name);
	     return str1.compareTo(str2);
	}

}
