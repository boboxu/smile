package com.heme.commonlogic.exception;


public class DaoException extends BaseException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3162302339994406422L;

	/**
	 * @param tableName
	 *            : 数据表名称
	 * @param opCode
	 *            ：操作代号/英文名
	 * @param message
	 *            ：业务逻辑描述
	 * @param cause
	 * @param paramters
	 *            : 执行参数
	 */
	public DaoException(String tableName, String opCode, String message,
	        Throwable cause, Object... params)
	{
		super(message + ":" + tableName + "(" + opCode + ")", cause, params);
	}

	/**
	 * @param tableName
	 *            : 数据表名称
	 * @param opCode
	 *            ：操作代号/英文名
	 * @param message
	 *            ：业务逻辑描述
	 * @param paramters
	 *            : 执行参数
	 */
	public DaoException(String tableName, String opCode, String message,
	        Object... params)
	{
		super(message + ":" + tableName + "(" + opCode + ")", params);
	}

}
