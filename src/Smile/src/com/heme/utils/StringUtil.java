package com.heme.utils;

public class StringUtil
{
	/**
	 * 字符串是否空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyOrNull(String str)
	{
		if (str == null || str.length() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
