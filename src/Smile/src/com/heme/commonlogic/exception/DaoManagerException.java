package com.heme.commonlogic.exception;


public class DaoManagerException extends BaseException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -539498737386185743L;

	/**
	 * @param message
	 * @param cause
	 */
    public DaoManagerException(String message, Throwable cause)
    {
	    super(message, cause);
    }
}
