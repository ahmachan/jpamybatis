package com.kl.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

/**
 * @desc
 **/
@Repository
public class BaseAppDaoImpl<T, ID extends Serializable> implements BaseAppDao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;


    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public Object findObjectById(Object o, Long id) {
        return entityManager.find(o.getClass(), id);
    }

    @Override
    public Object findObjectByHql(String tablename, String filed, Object o) {
        String sql = "From " + tablename + " u WHERE u." + filed + "=?";
        System.out.println(sql + "--------sql语句-------------");
        Query query = entityManager.createQuery(sql);
        query.setParameter(1, o);

        entityManager.close();
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public List<T> queryListByHql(String tableName, String filed, Object o) {
        String sql = "from " + tableName + " u WHERE u." + filed + "=?";
        System.out.println(sql + "--------sql语句-------------");
        Query query = entityManager.createQuery(sql);
        query.setParameter(1, o);
        List<T> list = query.getResultList();
        entityManager.close();
        return list;
    }

    @Override
    public Object findCountByFields(String tableName, LinkedHashMap<String, Object> map) {
        //在LinkedHashMap<String,Object>赋值给Object时，要具体标识各值的数据类型
        List<String> sqlList = new ArrayList<>();
        List<Object> filedList = new ArrayList<>();

        //获取Map中的所有key
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            //sqlList.add(String.format("u.%s=?%d",key.toString(),++fieldIndex));//此方式(?或?123)已被遗弃
            sqlList.add(String.format("u.%s=:%s", key, key));
            filedList.add(key.toString());
        }

        String sql = "select count(u) from " + tableName + " u WHERE ";
        sql = sql.concat(String.join(" and ", sqlList));
        System.out.println(sql + "--------sql语句-------------");
        Query query = entityManager.createQuery(sql);
        for (int i = 0; i < filedList.size(); i++) {
            //query.setParameter(i+1,map.get(filedList.get(i)));
            String key = filedList.get(i).toString();
            query.setParameter(key, map.get(key));
        }

        return query.getSingleResult();
    }


    @Transactional
    @Override
    public List<T> queryListByMoreFiled(String tableName, LinkedHashMap<String, Object> map) {
        String sql = "From " + tableName + " u WHERE ";
        Set<String> set = null;
        set = map.keySet();
        List<String> list = new ArrayList<>(set);
        List<Object> filedList = new ArrayList<>();
        for (String filed : list) {
            sql += "u." + filed + "=? and ";
            filedList.add(filed);
        }
        sql = sql.substring(0, sql.length() - 4);
        System.out.println(sql + "--------sql语句-------------");
        Query query = entityManager.createQuery(sql);
        for (int i = 0; i < filedList.size(); i++) {
            query.setParameter(i + 1, map.get(filedList.get(i)));
        }
        List<T> listRe = query.getResultList();
        entityManager.close();
        return listRe;
    }


    @Transactional
    @Override
    public List<T> queryPagesByMoreFileds(String tableName, LinkedHashMap<String, Object> map, int pageNo, int pageSize) {
        //在LinkedHashMap<String,Object>赋值给Object时，要具体标识各值的数据类型
        List<String> sqlList = new ArrayList<>();
        List<Object> filedList = new ArrayList<>();

        //获取Map中的所有key
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        //int fieldIndex=0;
        while (it.hasNext()) {
            String key = it.next();
            //sqlList.add(String.format("u.%s=?%d",key.toString(),++fieldIndex));
            sqlList.add(String.format("u.%s=?", key.toString()));
            filedList.add(key.toString());
            //sqlList.add(String.format("u.%s=%s",key.toString(),map.get(key).toString()));
        }

        String sql = "from " + tableName + " u WHERE ";
        sql = sql.concat(String.join(" and ", sqlList));
        Query query = entityManager.createQuery(sql);
        for (int i = 0; i < filedList.size(); i++) {
            query.setParameter(i + 1, map.get(filedList.get(i)));
        }
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        @SuppressWarnings("unchecked")
        List<T> lastList = query.getResultList();
        entityManager.close();
        return lastList;
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<T> queryPages(String tableName, String filed, Object o, int pageNo, int pageSize) {
        String sql = "From " + tableName + " u WHERE u." + filed + "=?";

        List<T> list = new ArrayList<>();
        try {
            Query query = entityManager.createQuery(sql);
            query.setParameter(1, o);
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
            list = query.getResultList();
            entityManager.close();
        } catch (Exception e) {
            System.out.println("------------分页错误---------------");
        }

        return list;
    }

    @Transactional
    @Override
    public boolean save(T entity) {
        try {
            entityManager.persist(entity);
            return true;
        } catch (Exception e) {
            System.out.println("---------------保存出错---------------");
            throw e;
        }
    }

    @Transactional
    @Override
    public boolean update(T entity) {
        boolean flag = false;
        try {
            entityManager.merge(entity);
            flag = true;
        } catch (Exception e) {
            System.out.println("---------------更新出错---------------");
        }
        return flag;
    }


    @Transactional
    @Override
    public Integer updateMoreFiled(String tableName, LinkedHashMap<String, Object> map) {
        String sql = "UPDATE " + tableName + " AS u SET ";
        Set<String> set = null;
        set = map.keySet();
        List<String> list = new ArrayList<>(set);
        for (int i = 0; i < list.size() - 1; i++) {
            if (map.get(list.get(i)).getClass().getTypeName() == "java.lang.String") {
                System.out.println("-*****" + map.get(list.get(i)) + "------------" + list.get(i));
                sql += "u." + list.get(i) + "='" + map.get(list.get(i)) + "' , ";
            } else {
                sql += "u." + list.get(i) + "=" + map.get(list.get(i)) + " , ";
            }
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += "where u.id=? ";
        System.out.println(sql + "--------sql语句-------------");
        int resurlt = 0;
        try {
            Query query = entityManager.createQuery(sql);
            query.setParameter(1, map.get("id"));
            resurlt = query.executeUpdate();
        } catch (Exception e) {
            System.out.println("更新出错-----------------------");
            e.printStackTrace();

        }
        return resurlt;
    }

    @Transactional
    @Override
    public Integer updateByFields(String tableName, LinkedHashMap<String, Object> map, LinkedHashMap<String, Object> pukMap) {
        List<String> sqlList = makeFieldSql(map);

        String sql = "UPDATE " + tableName + " AS u SET ";
        sql = sql.concat(String.join(" , ", sqlList));
        sql += " WHERE ";

        List<String> pukSqlList = makeFieldSql(pukMap);
        sql = sql.concat(String.join(" and ", pukSqlList));

        System.out.println(sql + "--------sql语句-------------");

        int resurlt = 0;
        try {
            Query query = entityManager.createQuery(sql);
            resurlt = query.executeUpdate();
        } catch (Exception e) {
            System.out.println("更新出错-----------------------");
            e.printStackTrace();

        }
        return resurlt;
    }

    private List<String> makeFieldSql(LinkedHashMap<String, Object> map) {
        //在LinkedHashMap<String,Object>赋值给Object时，要具体标识各值的数据类型
        List<String> sqlList = new ArrayList<>();
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (map.get(key).getClass().getTypeName() == "java.lang.String") {
                sqlList.add(String.format("u.%s='%s'", key, map.get(key).toString()));
            } else {
                sqlList.add(String.format("u.%s=%s", key, map.get(key).toString()));
            }
        }

        return sqlList;
    }

    @Transactional
    @Override
    public boolean delete(T entity) {
        boolean flag = false;
        try {
            entityManager.remove(entityManager.merge(entity));
            flag = true;
        } catch (Exception e) {
            System.out.println("---------------删除出错---------------");
        }
        return flag;
    }

}
