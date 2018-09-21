package com.kl.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

public interface BaseAppDao<T, ID extends Serializable> {

    /**
     * 根据id查询详细
     *
     * @param id
     * @param t
     * @return
     */
    T findObjectById(T t, Long id);

    /**
     * 根据条件查询详细
     *
     * @param tablename
     * @param filed
     * @param o
     * @return
     */
    Object findObjectByHql(String tablename, String filed, Object o);

    /**
     * 根据条件查询总条数返回object类型
     *
     * @param tablename 表名
     * @param map       传入参数放入map中
     * @return
     */
    Object findCountByFields(String tablename, LinkedHashMap<String, Object> map);


    /**
     * 根据条件(表名，字段，参数查询)获取列表
     *
     * @param tablename 表名
     * @param filed     字段名
     * @param o         字段参数
     * @return
     */
    List<T> queryListByHql(String tablename, String filed, Object o);


    /**
     * 根据条件(多个字段构成的多条件)获取列表
     *
     * @param tablename 表名
     * @param map       将你的字段传入map中
     * @return
     */
    List<T> queryListByMoreFiled(String tablename, LinkedHashMap<String, Object> map);

    /**
     * 多字段查询分页
     *
     * @param tablename 表名
     * @param map       以map存储key,value
     * @param pageNo    第几页
     * @param pageSize  一个页面的条数
     * @return
     */
    List<T> queryPagesByMoreFileds(String tablename, LinkedHashMap<String, Object> map, int pageNo, int pageSize);

    /**
     * 一个字段的分页
     *
     * @param tablename 表名
     * @param filed     字段名
     * @param o         字段参数
     * @param pageNo    第几页
     * @param pageSize  一个页面多少条数据
     * @return
     */
    List<T> queryPages(String tablename, String filed, Object o, int pageNo, int pageSize);

    /**
     * 保存数据对象
     *
     * @param entity
     * @return
     */
    boolean save(T entity);

    /**
     * 根据表的id删除数据
     *
     * @param entity
     */
    boolean delete(T entity);

    /**
     * 更新对象
     *
     * @param e
     * @return
     */
    boolean update(T e);

    /**
     * 根据传入的map遍历key,value拼接字符串，以id为条件更新
     *
     * @param tablename 表名
     * @param map       传入参数放入map中
     * @return
     */
    Integer updateMoreFiled(String tablename, LinkedHashMap<String, Object> map);

    /**
     * 根据主键或唯一或联合唯一主键，进行更新
     *
     * @param tablename 表名
     * @param puMap     传入参数放入map中,这些参数必须是主键或唯一或联合唯一主键
     * @return
     */
    Integer updateByFields(String tablename, LinkedHashMap<String, Object> map, LinkedHashMap<String, Object> pukMap);
}