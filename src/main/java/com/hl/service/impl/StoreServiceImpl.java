package com.hl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hl.dao.BaseAppDao;
import com.hl.dao.BaseAppDaoImpl;
import com.hl.model.Store;
import com.hl.repository.StoreRepository;
import com.hl.service.StoreService;
import com.hl.utils.JpaTransformer;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private BaseAppDao storeDao;
	
	@PersistenceContext
    private EntityManager em;
 
	
	//使用mybatis
    //@TODO
	
	//使用JPA
	public Store findOneById(Long id) {
		return storeRepository.findOneById(id);
	}

	public List<Store> findListByType(Byte type){
		return storeRepository.findListByType(type);
	}
	
	public Store findDetailInfoById(Long id) {
		return storeRepository.findStoreDetailById(id);
	}
	
	public Store findDetailInfo2ById(Long id) {
		return storeRepository.getStoreDetail3ById(id);
	}
	
	public Store findDetailInfo3ById(Long id) {
		return storeRepository.getStoreDetail3ById(id);
	}

	public Store findDetailInfo4ById(Long id) {
		javax.persistence.Query query = em.createNativeQuery(
				"SELECT id,login_name,store_name,type,time_diamond,address,status,add_time "
				+ "FROM t_store "
				+ "WHERE id=?1"
				);
		query.setParameter(1, id);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//必须有此转换才能使用"row.get(key)"
		Object stObj = query.getSingleResult();//字符数组的形式：[160105, 40, 35, 5] --ALIAS_TO_ENTITY_MAP--> [id=160105,mm=40,pp=35,type=5]
		
		Map<?, ?> row=(Map<?, ?>)stObj;//Map<?, ?> Map<String, Object>
		Store entity = new Store();
		entity.setId(Long.valueOf(String.valueOf(row.get("id"))));
		entity.setLoginName((String)row.get("login_name"));
		entity.setStoreName((String)row.get("store_name"));
		entity.setType((Byte)row.get("type"));		
		entity.setTimeDiamond(BigDecimal.valueOf(Double.valueOf(row.get("time_diamond").toString())));
		entity.setAddress(row.get("address").toString());
		entity.setStatus(Byte.valueOf(row.get("status").toString()));
		entity.setAddTime((Integer)row.get("add_time"));
		
		em.close();
		return entity;		
	}
	
	public Store findDetailInfo4aById(Long id){
		javax.persistence.Query query = em.createNativeQuery(
				"SELECT id,login_name,store_name,type,time_diamond,address,status,add_time "
						+ "FROM t_store "
						+ "WHERE id=?1"
				);
		query.setParameter(1, id);
		Object[] rows = (Object[]) query.getSingleResult();//字符数组的形式：[160105, 40, 35, 5]
			
		Store entity = new Store();		
		entity.setId(Long.valueOf(String.valueOf(rows[0].toString())));
		entity.setLoginName((String)rows[1].toString());
		entity.setStoreName((String)rows[2].toString());
		entity.setType(Byte.valueOf(rows[3].toString()));
		entity.setTimeDiamond(BigDecimal.valueOf(Double.valueOf(rows[4].toString())));
		entity.setAddress((String)rows[5].toString());
		entity.setStatus(Byte.valueOf(rows[6].toString()));
		entity.setAddTime(Integer.valueOf(rows[7].toString()));
		
		em.close();
		return entity;
	}
	
	public Store findDetailInfo5ById(Long id) {
		javax.persistence.Query query = em.createQuery(
				"FROM Store p WHERE p.id=?1"
				);
		query.setParameter(1, id);
		return (Store) query.getSingleResult();
	}
	
	public Store findDetailInfo6ById(Long id) {
		javax.persistence.Query query = em.createQuery(
				"SELECT p FROM Store p WHERE p.id=?1"
				);
		query.setParameter(1, id);
		return (Store) query.getSingleResult();
	}
	
	public Store findDetailInfo7ById(Long id) {
		javax.persistence.Query query = em.createQuery(
				"SELECT s.id,s.loginName,s.storeName,s.type,s.address,s.timeDiamond,s.status,s.addTime FROM Store s WHERE s.id=?1"
				);
		query.setParameter(1, id);
		return (Store) query.getSingleResult();
	}

   /**
	* 
　　　　1、在@Query注解中编写JPQL实现DELETE和UPDATE操作的时候必须加上@modifying注解，以通知Spring Data 这是一个DELETE或UPDATE操作（在model层或Entity）。

　　　　2、UPDATE或者DELETE操作需要使用事务，此时需要 定义Service层，在Service层的方法上添加事务操作。

　　　　3、注意JPQL不支持INSERT操作。　
	*/	
	@Transactional //这个的事务：javax.transaction.Transactional
	public int updateAddress(Long storeId,String address){
		return storeRepository.updateAddress(storeId,address);
	}

	@Transactional
	public int modifyAddress(Long storeId,String address){
		return storeRepository.modifyAddress(storeId,address);
	}
	
	public List<Store> getStoreList(){
		return storeRepository.getStoreList();
	}
	
	
	public List<Store> getAllStore(){
		return storeRepository.getAllStore(0L);
	}
	
    /**
     * 查询的结果是对象数组的集合
     */
    @SuppressWarnings("unchecked")
	public List<Store> getJpaSql2List() {
    	//定义SQL
    	String sql = "SELECT id,login_name,store_name,type,address,time_diamond,add_time FROM t_store";
    	//创建原生SQL查询QUERY实例,指定了返回的实体类型
    	javax.persistence.Query query = em.createNativeQuery(sql, Store.class);
    	//执行查询，返回的是可定制化对象
    	JpaTransformer Testtrans = new JpaTransformer(Store.class);
    	query.unwrap(SQLQuery.class).setResultTransformer(Testtrans);
    	
    
    	em.close();
    	//指定返回对象类型
    	//query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

    	//query.unwrap(SQLQuery.class).setResultTransformer(new AliasToBeanResultTransformer(Store.class));
    	//OK hibernate会自动映射过去,如果不用这个方法它返回的时候是Collection<Object>,所以不能起到转换的作用,在JBoss中运行查询时会报错的.
    	return (List<Store>)query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<Store> getJpaSql3List() {
    	String sql = "SELECT id,login_name,store_name,type,address,time_diamond,add_time FROM t_store";
        javax.persistence.Query query=em.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Store> list = query.getResultList();
		
		List<Store> lastList=new ArrayList<>();
		for (Object jpaObj : list) {
			Map<?, ?> row=(Map<?, ?>)jpaObj;
			Store entity = new Store();
			entity.setId(Long.valueOf(String.valueOf(row.get("id"))));
			entity.setLoginName((String)row.get("login_name"));
			entity.setStoreName((String)row.get("store_name"));
			entity.setType((Byte)row.get("type"));
			entity.setTimeDiamond(BigDecimal.valueOf(Double.valueOf(row.get("time_diamond").toString())));
			entity.setAddress(row.get("address").toString());
			entity.setStatus(Byte.valueOf("1"));
			entity.setAddTime((Integer)row.get("add_time"));
		
			lastList.add(entity);
		}
        em.close();
        
        return lastList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Store> getJpaSql3aList(){
    	String sql = "SELECT id,login_name,store_name,type,address,time_diamond,add_time,status FROM t_store";
    	javax.persistence.Query query=em.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
 		List<Object[]> list = query.getResultList();
    	    	
    	List<Store> lastList = new ArrayList<>();
		for (Object jpaObj : list) {
			Map<?, ?> row=(Map<?, ?>)jpaObj;//java.lang.Object; cannot be cast to java.util.Map
			Store entity = new Store();
			entity.setId(Long.valueOf(String.valueOf(row.get("id"))));
			entity.setLoginName((String)row.get("login_name"));
			entity.setStoreName((String)row.get("store_name"));
			entity.setType((Byte)row.get("type"));
			entity.setTimeDiamond(BigDecimal.valueOf(Double.valueOf(row.get("time_diamond").toString())));
			entity.setAddress(row.get("address").toString());
			entity.setStatus(Byte.valueOf(row.get("status").toString()));
			entity.setAddTime((Integer)row.get("add_time"));
		
			lastList.add(entity);
		}
		em.clear();
		
        return lastList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getJpaSql3bList(){
    	String sql = "SELECT id,login_name,store_name,type,address,time_diamond,add_time,status FROM t_store";
    	javax.persistence.Query query=em.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
 		List<Object[]> lastList = query.getResultList();//这直接返回库字段名(id,login_name)，但List<Store>返回实体类的属性名(Id,LoginName)
    	    	
		em.clear();
		
        return lastList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Store> getJpaSql4List(){
    	//字段的别名要与实体Store一致(POJO)
    	String sql = "SELECT id as Id,"
    			+ "login_name as LoginName,"
    			+ "store_name as StoreName,"
    			+ "type as Type,"
    			+ "address as Address,"
    			+ "time_diamond as TimeDiamond,"
    			+ "add_time as AddTime,"
    			+ "status as Status "
    			+ "FROM t_store ";
 
 	
        List<Store> list = (List<Store>)em.createNativeQuery(sql)
        .unwrap(SQLQuery.class)
        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
        .list();
        
        em.clear();
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public List<Store> getJpaSql4aList(){
    	//字段的别名要与实体Store一致(POJO)
    	String sql = "SELECT id as Id,"
    			+ "login_name as LoginName,"
    			+ "store_name as StoreName,"
    			+ "type as Type,"
    			+ "address as Address,"
    			+ "time_diamond as TimeDiamond,"
    			+ "add_time as AddTime,"
    			+ "status as Status "
    			+ "FROM t_store ";
 
 	
    	javax.persistence.Query query=em.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Store> lastList = query.getResultList();//因为要返回实体，所以sql中的字段别名与实体属性名保持一致
        
        em.clear();
        return lastList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getJpaSql4bList(){
    	//字段的别名要与实体Store一致(POJO)
    	String sql = "SELECT id as Id,"
    			+ "login_name as LoginName,"
    			+ "store_name as StoreName,"
    			+ "type as Type,"
    			+ "address as Address,"
    			+ "time_diamond as TimeDiamond,"
    			+ "add_time as AddTime,"
    			+ "status as Status "
    			+ "FROM t_store ";
 
 	
    	javax.persistence.Query query=em.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Object[]> lastList = query.getResultList();//因为不是返回实体(此时返回对象数组列表)，所以sql中的字段别名与实体属性名是否相同并无关系，也可以不使用别名
        
        em.clear();
        return lastList;
    }
    
    
    /**
     * 查询的结果是以hibernate生成的Bean为对象装入list集合
     */
    @SuppressWarnings("unchecked")
	public List<Store> getJpaHqlList() {
    	List<Store> stores = new ArrayList<>();
    	String hql = "From Store c Where c.id>:c1";
    	javax.persistence.Query query = em.createQuery(hql);
    	query.setParameter("c1", 0L);
    	stores = query.getResultList();
    	
    	em.close();

    	return stores;
    }
    
    
    @SuppressWarnings("unchecked")
    public List<Store> getJpaHql2List(){
    	String hql = "SELECT id,loginName,storeName,type,address,timeDiamond,addTime FROM Store";
    	List<Object[]> list = em.createQuery(hql).getResultList();
    	
    	List<Store> stList = new ArrayList<>();
    	for(Object[] objs:list) {
    		Store stu = new Store();
    		stu.setId((Long)objs[0]);
    		stu.setLoginName((String)objs[1]);
    		stu.setStoreName((String)objs[2]);
    		stu.setType((Byte)objs[3]);
    		stu.setAddress((String)objs[4]);
    		stu.setTimeDiamond((BigDecimal)objs[5]);
    		stu.setAddTime((Integer)objs[6]);
    		stList.add(stu);
    	}
    	em.clear();
    	return stList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Store> getJpaHql2aList(){
    	//这个时getResultList返回列表数组
    	//String hql = "SELECT id,loginName,storeName,type,address,timeDiamond,addTime,status FROM Store";
    	//这个时getResultList返回对象数组
    	String hql = "Select p From Store p";    
    	List<Store> list = em.createQuery(hql).getResultList();
    	em.close();
    	
    	return list;

    }
    
    @SuppressWarnings("unchecked")
    public List<Store> getJpaHql2bList(){
    	String hql = "SELECT id,loginName,storeName,type,address,timeDiamond,addTime,status FROM Store";
    	javax.persistence.Query query=em.createQuery(hql);
        //query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Store> list = query.getResultList();//因为返回实体(此时返回对象数组列表)，所以sql中的字段别名与实体属性名相同
        
		List<Store> lastList = new ArrayList<>();
		for (Object jpaObj : list) {
			Map<?, ?> row=(Map<?, ?>)jpaObj;
			Store entity = new Store();
			entity.setId(Long.valueOf(String.valueOf(row.get("id"))));
			entity.setLoginName((String)row.get("login_name"));
			entity.setStoreName((String)row.get("store_name"));
			entity.setType((Byte)row.get("type"));
			entity.setTimeDiamond(BigDecimal.valueOf(Double.valueOf(row.get("time_diamond").toString())));
			entity.setAddress(row.get("address").toString());
			entity.setStatus(Byte.valueOf(row.get("status").toString()));
			entity.setAddTime((Integer)row.get("add_time"));
		
			lastList.add(entity);
		}
		em.clear();
		
    	return lastList;
    }
     
    @SuppressWarnings("unchecked")
    public List<Object[]> getJpaHql2cList(){
    	String hql = "SELECT id,loginName,storeName,type,address,timeDiamond,addTime FROM Store";
    	javax.persistence.Query query=em.createQuery(hql);
        //query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Object[]> lastList = query.getResultList();//因为返回实体(此时返回对象数组列表)，所以sql中的字段别名与实体属性名相同
        
    	em.clear();
    	return lastList;
    }  
    
    @SuppressWarnings("unchecked")
	public List<Store> getJpaHql3List() {
    	List<Store> list = new ArrayList<>();
    	String hql = "Select p From Store p Where p.id>:pramId";
    	javax.persistence.Query query = em.createQuery(hql);
    	query.setParameter("pramId", 0L);
    	list = query.getResultList();
    	em.close();

    	return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<Store> getJpaHql3aList() {
		List<Store> list = new ArrayList<>();
    	String hql = String.format("Select p From Store p Where p.id>%d",0L);
    	javax.persistence.Query query = em.createQuery(hql);    
    	list = query.getResultList();    	
    	em.close();
    	
    	return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<Store> getJpaHql3bList() {
    	String hql = String.format("Select p From Store p Where p.id>%d",0L);
    	List<Store> list = em.createQuery(hql).getResultList();
    	em.close();
    	return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<Object[]> getJpaHql3cList() {
    	String hql = String.format("Select p From Store p Where p.id>%d",0L);
    	List<Object[]> list = em.createQuery(hql).getResultList();
    
    	em.close();
    	return list;
    }
    
	@Transactional
	public List<Store> getStoreListByTypesWithPages(byte typeCate,int pageNo,int pageSize) {
		String tablename ="Store";
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		//赋值给Object时，要具体标识各值的数据类型
		map.put("type", Byte.valueOf(typeCate));
		//map.put("status", Byte.valueOf("1"));
		
        @SuppressWarnings("unchecked")
		List<Store> lastList= storeDao.queryPagesByMoreFileds(tablename, map, pageNo, pageSize);
        return lastList;
    }
	
	@Transactional
	public Object getStoreCountByTypes(byte typeCate) {
		String tablename ="Store";
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		//赋值给Object时，要具体标识各值的数据类型
		map.put("type", Byte.valueOf(typeCate));
		//map.put("status", Byte.valueOf("1"));
		
        @SuppressWarnings("unchecked")
        Object lastObj= storeDao.findCountByFields(tablename, map);
        return lastObj;
    }
	
	@Transactional
	public Integer updateStoreByFields(Long id,String storeName,String address) {
		String tablename ="Store";
		LinkedHashMap<String,Object> pukMap=new LinkedHashMap<String,Object>();
		//赋值给Object时，要具体标识各值的数据类型
		pukMap.put("id", id);
		
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		map.put("storeName", storeName);
		map.put("address", address);
		
        @SuppressWarnings("unchecked")
        Integer resRows= storeDao.updateByFields(tablename, map, pukMap);
        return resRows;
    }
}
