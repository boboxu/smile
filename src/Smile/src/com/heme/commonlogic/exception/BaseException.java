package com.heme.commonlogic.exception;

import java.util.Arrays;

public class BaseException extends Exception
{
	private static final long serialVersionUID = -1842124024296535960L;

	public BaseException(String message, Throwable cause, Object... params)
	{
		super(cause == null ? null : cause.getMessage() + ":" + message + "@"
		        + params != null ? Arrays.toString(params) : null, cause);
	}

	public BaseException(String message, Object... params)
	{
		super(message + "@" + Arrays.toString(params));
	}

	public BaseException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public BaseException(String message)
	{
		super(message);
	}

}
