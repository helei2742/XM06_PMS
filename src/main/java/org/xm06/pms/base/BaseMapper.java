package org.xm06.pms.base;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BaseMapper<T, ID> {

    /**
     * 添加记录返回行数
     * @param entity
     * @return
     * @throws DataAccessException
     */
    public Integer insertSelective(T entity) throws DataAccessException;

    /**
     * 添加记录返回主键
     * @param entity
     * @return
     * @throws DataAccessException
     */
    public Integer insertHasKey(T entity) throws DataAccessException;

    /**
     * 批量添加
     * @param entities
     * @return
     * @throws DataAccessException
     */
    public Integer insertBatch(List<T> entities) throws DataAccessException;

    /**
     * 根据id查询详情
     * @param id
     * @return
     * @throws DataAccessException
     */
    public T selectByPrimaryKey(ID id) throws DataAccessException;

    /**
     * 多 条件查询
     * @param baseQuery
     * @return
     * @throws DataAccessException
     */
    public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException;

    /**
     * 跟新单条记录
     * @param entity
     * @return
     * @throws DataAccessException
     */
    public Integer updateByPrimaryKeySelective(T entity) throws  DataAccessException;

    /**
     * 批量更新
     * @param entities
     * @return
     * @throws DataAccessException
     */
    public Integer updateBatch(List<T> entities) throws DataAccessException;

    /**
     * 删除单条记录
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Integer deleteByPrimaryKey(ID id) throws DataAccessException;


    /**
     * 批量删除
     * @param ids
     * @return
     * @throws DataAccessException
     */
    public Integer deleteBatch(ID[] ids) throws DataAccessException;
}
