package com.heme.commonlogic.dao;

import java.io.Serializable;
import java.util.List;

import com.heme.commonlogic.exception.DaoException;

public interface IBaseDaoInterface<T extends Serializable, PK extends Serializable>
{

	/**
	 * 创建或修改实体 保存到数据库 如果存在,则替换更新
	 * 
	 * @param module
	 *            要创建的实体
	 * @return
	 * @throws DaoException
	 */
	public T createOrUpdateModule(T module) throws DaoException;

	/**
	 * 创建或修改实体 如果存在,则替换更新
	 * 
	 * @param module
	 *            要创建的实体
	 * @param ifSaveInDB
	 *            是否保存到数据库
	 * @return
	 * @throws DaoException
	 */
	public T createOrUpdateModule(T module, boolean ifSaveInDB)
	        throws DaoException;

	/**
	 * 创建或修改实体
	 * 
	 * @param module
	 *            要创建的实体
	 * @param ifSaveInDB
	 *            是否保存到数据库
	 * @param ifExistReplace
	 *            如果存在,是否替换
	 * @return
	 * @throws DaoException
	 */
	public T createOrUpdateModule(T module, boolean ifSaveInDB,
	        boolean ifExistReplace) throws DaoException;

	/**
	 * 创建或修改实体
	 * 
	 * @param modules
	 *            要创建的实体列表
	 * @return
	 * @throws DaoException
	 */
	public List<T> createOrUpdateModules(List<T> modules) throws DaoException;

	/**
	 * 创建或修改实体
	 * 
	 * @param modules
	 *            要创建的实体列表
	 * @param ifSaveInDB
	 *            是否保存到数据库
	 * @return
	 * @throws DaoException
	 */
	public List<T> createOrUpdateModules(List<T> modules, boolean ifSaveInDB)
	        throws DaoException;

	/**
	 * 创建或修改实体
	 * 
	 * @param modules
	 *            要创建的实体列表
	 * @param ifSaveInDB
	 *            是否保存到数据库
	 * @param ifExistReplace
	 *            如果存在,是否替换
	 * @return
	 * @throws DaoException
	 */
	public List<T> createOrUpdateModules(List<T> modules, boolean ifSaveInDB, boolean ifExistReplace)
	        throws DaoException;

	
	
	/**
	 * 根据PK查找实体
	 * 不忽略缓存
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public T findByPK(PK id) throws DaoException;
	
	/**
	 * 根据PK查找实体
	 * 
	 * @param id
	 * @param ifIgnoreCache 是否忽略缓存
	 * @return
	 * @throws DaoException
	 */
	public T findByPK(PK id, boolean ifIgnoreCache) throws DaoException;

	/**
	 * 根据PK列表查找实体
	 * 
	 * @param ids
	 * @return
	 * @throws DaoException
	 */
	public List<T> findByPKs(List<?> ids) throws DaoException;

	/**
	 * 查找实体所有对象
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<T> findAll() throws DaoException;

	/**
	 * 查询一个字段满足多个值的多条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValues
	 *            字段值列表
	 * @return
	 * @throws DaoException
	 */
	public List<T> findByColumn(String columnName, List<?> columnValues)
	        throws DaoException;

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
	 * @throws DaoException
	 */
	public List<T> findByColumn(String columnName, List<?> columnValues,
	        String orderColumnName, boolean orderAscending) throws DaoException;

	/**
	 * 查询一个字段满足一个值的多条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValue
	 *            字段值
	 * @return
	 * @throws DaoException
	 */
	public List<T> findByColumn(String columnName, Object columnValue)
	        throws DaoException;

	/**
	 * 查询一个字段满足一个值的多条记录
	 * 返回符合所有记录
	 * @param columnName
	 *            字段名称
	 * @param columnValue
	 *            字段值
	 * @param orderColumnName
	 *            排序字段
	 * @param orderAscending
	 *            排序方式
	 * @return
	 * @throws DaoException
	 */
	public List<T> findByColumn(String columnName, Object columnValue,
	        String orderColumnName, boolean orderAscending) throws DaoException;

	
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
	 *            起始条数,小于0表示从第0条开始
	 * @param maxRow
	 *            返回的最大条数，小于0表示不限制
	 * @return
	 * @throws DaoException
	 */
	public List<T> findByColumn(String columnName, Object columnValue,
	        String orderColumnName, boolean orderAscending, long startRow, long maxRow) throws DaoException;
	
	/**
	 * 查询一个字段满足多个值的第一条记录
	 * 
	 * @param columnName
	 *            字段名称
	 * @param columnValue
	 *            字段值
	 * @return
	 * @throws DaoException
	 */
	public T findFirstByColumn(String columnName, Object columnValue)
	        throws DaoException;

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
	 * @throws DaoException
	 */
	public T findFirstByColumn(String columnName, Object columnValue,
	        String orderColumnName, boolean orderAscending) throws DaoException;
	
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
	 * @throws DaoException
	 */
	public List<T> findTop(String orderColumnName, boolean orderAscending,
	        long maxRow, Object minValue, Object maxValue) throws DaoException;

	/**
	 * 根据主键删除对象
	 * 
	 * @param pk
	 *            单个主键
	 * @return 删除条数
	 * @throws DaoException
	 */
	public int deleteByPK(PK pk) throws DaoException;

	/**
	 * 根据主键删除对象
	 * 
	 * @param ids
	 *            主键列表
	 * @return 删除条数
	 * @throws DaoException
	 */
	public int deleteByPKs(List<?> ids) throws DaoException;

	/**
	 * 根据字段删除主键
	 * 
	 * @param columnName
	 *            字段名
	 * @param columnValue
	 *            字段值
	 * @return 删除条数
	 * @throws DaoException
	 */
	public int deleteByColumn(String columnName, Object columnValue)
	        throws DaoException;

	/**
	 * 根据字段删除主键
	 * 
	 * @param columnName
	 *            字段名
	 * @param columnValues
	 *            字段值list
	 * @return 删除条数
	 * @throws DaoException
	 */
	public int deleteByColumn(String columnName, List<?> columnValues)
	        throws DaoException;

	/**
	 * 删除实体所有对象
	 * 
	 * @return 删除条数
	 * @throws DaoException
	 */
	public int deleteAll() throws DaoException;
	
	/**
	 * 缓存数据
	 * @param module
	 * @return
	 */
	public T cache(T module);

}
