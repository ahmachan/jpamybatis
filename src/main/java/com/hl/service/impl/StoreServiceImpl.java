package com.hl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hl.model.Store;
import com.hl.model.StoreSimpleVo;
import com.hl.repository.StoreRepository;
import com.hl.service.StoreService;
import com.hl.utils.JpaTransformer;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreRepository storeRepository;
	
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

	public Object findDetailInfo4aById(Long id){

		javax.persistence.Query query = em.createNativeQuery(
				"SELECT id as Id,login_name as LoginName,store_name as StoreName,type as Type,time_diamond as TimeDiamond,address as Address,status as Status,add_time as addTime "
						+ "FROM t_store "
						+ "WHERE id=?1"
				);
		query.setParameter(1, id);
		//Store rows = query.getSingleResult();//字符数组的形式：[160105, 40, 35, 5]
		@SuppressWarnings("unchecked")
		List<Object> rows = query.getResultList();
		Store entity = new Store();
		entity.setId(Long.valueOf(String.valueOf(rows.get(0))));
		entity.setLoginName((String)rows.get(1));
		entity.setStoreName((String)rows.get(2));
		entity.setType((Byte)rows.get(3));
		entity.setTimeDiamond(BigDecimal.valueOf((Double)rows.get(4)));
		entity.setAddress((String)rows.get(5));
		entity.setStatus((Byte)rows.get(6));
		entity.setAddTime((Integer)rows.get(7));
		return entity;
	}
	
	public Store findDetailInfo4ById(Long id) {
		javax.persistence.Query query = em.createNativeQuery(
				"SELECT id as Id,login_name as LoginName,store_name as StoreName,type as Type,time_diamond as TimeDiamond,address as Address,status as Status,add_time as addTime "
				+ "FROM t_store "
				+ "WHERE id=?1"
				);
		query.setParameter(1, id);
		//List<Store> result = new ArrayList<Store>();
		Store jpaObj = (Store)query.getSingleResult();//字符数组的形式：[160105, 40, 35, 5]
		//List<Object[]> rows = query.getResultList();
//	
//		for(Object[] obj : rows){
//			Store entity = new Store();
//			
//			Map<String,Object> row = (Map<String, Object>) obj;  
//			entity.setId(Long.valueOf(String.valueOf(row.get("id"))));
//			entity.setLoginName((String)row.get("login_name"));
//			entity.setStoreName((String)row.get("store_name"));
//			entity.setType((Byte)row.get("type"));
//			entity.setTimeDiamond(BigDecimal.valueOf((Double)row.get("time_diamond")));
//			entity.setAddress((String)row.get("address"));
//			entity.setStatus((Byte)row.get("status"));
//			entity.setAddTime((Integer)row.get("add_time"));
//			result.add(entity);
//		}

	
		//List<Store> item = query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(Store.class)).list();
		return jpaObj;
		
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
				"SELECT s.id,s.loginName,s.storeName,s.timeDiamond FROM Store s WHERE s.id=?1"
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
    
    public List<Store> getJpaSql3List() {
    	String sql = "SELECT id,login_name,store_name,type,address,time_diamond,add_time FROM t_store";
        System.out.println(sql+"--------sql语句-------------");
        javax.persistence.Query query=em.createNativeQuery(sql);
       
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Store> list = query.getResultList();
		List<Store> rsList=new ArrayList<Store>();
		for (Object jpaObj : list) {
			Map<?, ?> row=(Map<?, ?>)jpaObj;
			Store message = new Store();
			message.setId(Long.valueOf(String.valueOf(row.get("id"))));
			message.setLoginName((String)row.get("login_name"));
			message.setStoreName((String)row.get("store_name"));
			message.setType((Byte)row.get("type"));
			message.setTimeDiamond(BigDecimal.valueOf((Double)row.get("time_diamond")));
			message.setAddTime((Integer)row.get("add_time"));
			rsList.add(message);
		}

        em.close();
        return rsList;
    }
    
    public List<Store> getJpaSql4List(){
    	String sql = "SELECT id as Id,"
    			+ "login_name as LoginName,"
    			+ "store_name as StoreName,"
    			+ "type as Type,"
    			+ "address as Address,"
    			+ "time_diamond as TimeDiamond,"
    			+ "add_time as AddTime,"
    			+ "status as Status "
    			+ "FROM t_store ";
 
 	
    	@SuppressWarnings("unchecked")
        List<Store> list = (List<Store>)em.createNativeQuery(sql)
        .unwrap(SQLQuery.class)
        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
        .list();
        
        em.clear();
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public List<Store> getJpaSql5List(){

    	String hql = "SELECT id,loginName,storeName,type,address,timeDiamond,addTime FROM Store";

    	List<Object[]> list = em.createQuery(hql).getResultList();//列表数组:[11,"abc","abmall",1,15856854]
    	List<Store> stList = new ArrayList<Store>();

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
    	return stList;
    }
    
    /**
     * 查询的结果是以hibernate生成的Bean为对象装入list集合
     */
    public List<StoreSimpleVo> findJpaHqlList() {

    	List<StoreSimpleVo> stores = new ArrayList<StoreSimpleVo>();
    	String hql = "From StoreSimpleVo c Where c.id>:c1";
    	javax.persistence.Query query2 = em.createQuery(hql);
    	query2.setParameter("c1", 0);
    	StoreSimpleVo c = (StoreSimpleVo) query2.getResultList().get(0);
    	stores.add(c);
    	
    	em.close();

    	return stores;
    }
    
}
