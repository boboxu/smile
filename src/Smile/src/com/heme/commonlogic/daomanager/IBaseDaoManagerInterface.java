package com.heme.commonlogic.daomanager;

import java.io.Serializable;
import java.util.List;

import com.heme.commonlogic.exception.DaoManagerException;

public interface IBaseDaoManagerInterface<T extends Serializable, PK extends Serializable>
{
	/**
	 * 创建或修改实体
	 * 保存到数据库
	 * 
	 * @param module
	 *            要创建的实体
	 * @return
	 * @throws DaoManagerException
	 */
	public T createOrUpdateModule(T module) throws DaoManagerException;

	/**
	 * 创建或修改实体
	 * 如果存在,则替换
	 * 
	 * @param module
	 *            要创建的实体
	 * @param ifSaveInDB
	 *            是否保存到数据库
	 * @return
	 * @throws DaoManagerException
	 */
	public T createOrUpdateModule(T module, boolean ifSaveInDB)
	        throws DaoManagerException;

	/**
	 * 创建或修改实体
	 * 
	 * @param module
	 *            要创建的实体
	 * @param ifSaveInDB
	 *            是否保存到数据库
	 * @param ifExistReplace 如果存在,是否替换
	 * @return
	 * @throws DaoManagerException
	 */
	public T createOrUpdateModule(T module, boolean ifSaveInDB, boolean ifExistReplace)
	        throws DaoManagerException;
	
	/**
	 * 创建或修改实体
	 * 
	 * @param module
	 *            要创建的实体
	 * @param ifSaveInDB
	 *            是否保存到数据库
	 * @param ifExistReplace 如果存在,是否替换
	 * @param ifAsync 是否异步
	 * @return
	 * @throws DaoManagerException
	 */
	public T createOrUpdateModule(T module, boolean ifSaveInDB, boolean ifExistReplace, boolean ifAsync)
	        throws DaoManagerException;
	
	/**
	 * 修改或创建多个实体
	 * @param modules 要创建的实体
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> createOrUpdateModules(List<T> modules) throws DaoManagerException;
	
	/**
	 * 修改或创建多个实体
	 * @param modules 要创建的实体
	 * @param ifSaveInDB 是否保存到数据库
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> createOrUpdateModules(List<T> modules, boolean ifSaveInDB) throws DaoManagerException;
	
	/**
	 * 修改或创建多个实体
	 * @param modules 要创建的实体
	 * @param ifSaveInDB 是否保存到数据库
	 * @param ifExistReplace 如果存在,是否替换
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> createOrUpdateModules(List<T> modules, boolean ifSaveInDB, boolean ifExistReplace) throws DaoManagerException;

	/**
	 * 修改或创建多个实体
	 * @param modules 要创建的实体
	 * @param ifSaveInDB 是否保存到数据库
	 * @param ifExistReplace 如果存在,是否替换
	 * @param ifAsync 是否延时写数据库(写完缓存就返回)
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> createOrUpdateModules(List<T> modules, boolean ifSaveInDB, boolean ifExistReplace, boolean ifAsync) throws DaoManagerException;
	
	/**
	 * 根据PK查找实体
	 * 
	 * @param id
	 * @return
	 * @throws DaoManagerException
	 */
	public T findByPK(PK id) throws DaoManagerException;

	/**
	 * 根据PK列表查找实体
	 * 
	 * @param ids
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> findByPKs(List<?> ids) throws DaoManagerException;

	/**
	 * 查找实体所有对象
	 * 
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> findAll() throws DaoManagerException;

	/**
	 * 查询一个字段满足多个值的多条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValues
	 *            字段值列表
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> findByColumn(String columnName, List<?> columnValues)
	        throws DaoManagerException;

	/**
	 * 查询一个字段满足多个值的多条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValues
	 *            字段值列表
	 * @param orderColumnName
	 *            排序字段
	 * @param orderAscending
	 *            排序方式
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> findByColumn(String columnName, List<?> columnValues,
	        String orderColumnName, boolean orderAscending)
	        throws DaoManagerException;

	/**
	 * 查询一个字段满足一个值的多条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValue
	 *            字段值
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> findByColumn(String columnName, Object columnValue)
	        throws DaoManagerException;

	/**
	 * 查询一个字段满足一个值的多条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValue
	 *            字段值
	 * @param orderColumnName
	 *            排序字段
	 * @param orderAscending
	 *            排序方式
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> findByColumn(String columnName, Object columnValue,
	        String orderColumnName, boolean orderAscending)
	        throws DaoManagerException;

	/**
	 * 查询一个字段满足一个值的多条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValue
	 *            字段值
	 * @param orderColumnName
	 *            排序字段
	 * @param orderAscending
	 *            排序方式
	 * @param maxRow
	 * 			  maxRow
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> findByColumn(String columnName, Object columnValue,
	        String orderColumnName, boolean orderAscending, long maxRow)
	        throws DaoManagerException;
	
	/**
	 * 查询一个字段满足一个值的多条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValue
	 *            字段值
	 * @param orderColumnName
	 *            排序字段
	 * @param orderAscending
	 *            排序方式
	 * @param startRow
	 * 			  开始行
	 * @param maxRow
	 * 			  最多结果行
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> findByColumn(String columnName, Object columnValue,
	        String orderColumnName, boolean orderAscending, long startRow, long maxRow)
	        throws DaoManagerException;
	
	
	/**
	 * 查询一个字段满足多个值的第一条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValue
	 *            字段值
	 * @return
	 * @throws DaoManagerException
	 */
	public T findFirstByColumn(String columnName, Object columnValue)
	        throws DaoManagerException;

	/**
	 * 查询一个字段满足多个值的第一条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValue
	 *            字段值
	 * @param orderColumnName
	 *            排序字段
	 * @param orderAscending
	 *            排序方式
	 * @return
	 * @throws DaoManagerException
	 */
	public T findFirstByColumn(String columnName, Object columnValue,
	        String orderColumnName, boolean orderAscending)
	        throws DaoManagerException;

	/**
	 * 查询按某字段排序的列表
	 * 
	 * @param orderColumnName
	 *            排序字段名称
	 * @param orderAscending
	 *            排序方式
	 * @param maxRow
	 *            条数
	 * @param minValue
	 *            字段最小值,>minValue,为null则不限制
	 * @param maxValue
	 *            字段最大值,<maxValue,为null则不限制
	 * @return
	 * @throws DaoManagerException
	 */
	public List<T> findTop(String orderColumnName, boolean orderAscending,
	        long maxRow, Object minValue, Object maxValue)
	        throws DaoManagerException;

	/**
	 * 根据主键删除对象
	 * 
	 * @param pk
	 *            单个主键
	 * @return 删除条数
	 * @throws DaoManagerException
	 */
	public int deleteByPK(PK pk) throws DaoManagerException;

	/**
	 * 根据主键删除对象
	 * 
	 * @param pk
	 *            单个主键
	 * @param isAsync 是否异步
	 * @return 删除条数
	 * @throws DaoManagerException
	 */
	public int deleteByPK(PK pk, boolean isAsync) throws DaoManagerException;
	
	/**
	 * 根据主键删除对象
	 * 
	 * @param ids
	 *            主键列表
	 * @return 删除条数
	 * @throws DaoManagerException
	 */
	public int deleteByPKs(List<?> ids) throws DaoManagerException;

	/**
	 * 根据主键删除对象
	 * 
	 * @param ids
	 *            主键列表
	 * @param isAsync 是否异步
	 * @return 删除条数
	 * @throws DaoManagerException
	 */
	public int deleteByPKs(List<?> ids, boolean isAsync) throws DaoManagerException;
	
	/**
	 * 根据字段删除主键
	 * 
	 * @param columnName
	 *            字段名
	 * @param columnValue
	 *            字段值
	 * @return 删除条数
	 * @throws DaoManagerException
	 */
	public int deleteByColumn(String columnName, Object columnValue)
	        throws DaoManagerException;

	/**
	 * 根据字段删除主键
	 * 
	 * @param columnName
	 *            字段名
	 * @param columnValue
	 *            字段值
	 * @param isAsync 是否异步
	 * @return 删除条数
	 * @throws DaoManagerException
	 */
	public int deleteByColumn(String columnName, Object columnValue, boolean isAsync)
	        throws DaoManagerException;
	
	/**
	 * 根据字段删除主键
	 * 
	 * @param columnName
	 *            字段名
	 * @param columnValues
	 *            字段值list
	 * @return 删除条数
	 * @throws DaoManagerException
	 */
	public int deleteByColumn(String columnName, List<?> columnValues)
	        throws DaoManagerException;

	/**
	 * 根据字段删除主键
	 * 
	 * @param columnName
	 *            字段名
	 * @param columnValues
	 *            字段值list
	 * @param isAsync 是否异步
	 * @return 删除条数
	 * @throws DaoManagerException
	 */
	public int deleteByColumn(String columnName, List<?> columnValues, boolean isAsync)
	        throws DaoManagerException;
	
	/**
	 * 删除实体所有对象
	 * 
	 * @return 删除条数
	 * @throws DaoManagerException
	 */
	public int deleteAll() throws DaoManagerException;
	
	/**
	 * 删除实体所有对象
	 * 
	 * @param isAsync 是否异步
	 * @return 删除条数
	 * @throws DaoManagerException
	 */
	public int deleteAll(boolean isAsync) throws DaoManagerException;
	
	/**
	 * 缓存
	 * @param module
	 * @return
	 */
	public T cache(T module);

}
