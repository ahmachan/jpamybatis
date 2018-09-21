package com.kl.service;

import com.kl.model.Store;

import java.util.List;

public interface StoreService {

	Store findOneById(Long id);
	
	List<Store> findListByType(Byte type);
	
	Store findDetailInfoById(Long id);
	
	Store findDetailInfo2ById(Long id);
	
	Store findDetailInfo3ById(Long id);
	
	Store findDetailInfo4ById(Long id);
	
	Store findDetailInfo4aById(Long id);

	Store findDetailInfo5ById(Long id);
	
	Store findDetailInfo6ById(Long id);
	
	Store findDetailInfo7ById(Long id);
	
	int updateAddress(Long storeId,String address);

	int modifyAddress(Long storeId,String address);
	
	List<Store> getStoreList();
	
	List<Store> getAllStore();	
	
	List<Store> getJpaSql2List();
	
	List<Store> getJpaSql3List();	
	List<Store> getJpaSql3aList();	
	List<Object[]> getJpaSql3bList();
	
	List<Store> getJpaSql4List();	
	List<Store> getJpaSql4aList();	
	List<Object[]> getJpaSql4bList();

	List<Store> getJpaHqlList();
	
	List<Store> getJpaHql2List();	
	List<Store> getJpaHql2aList();	
	List<Store> getJpaHql2bList();	
	List<Object[]> getJpaHql2cList();
	
	List<Store> getJpaHql3List();	
	List<Store> getJpaHql3aList();	
	List<Store> getJpaHql3bList();	
	List<Object[]> getJpaHql3cList();
	
	List<Store> getStoreListByTypesWithPages(byte typeCate,int pageNo,int pageSize);	
	Object getStoreCountByTypes(byte typeCate);
	
	Integer updateStoreByFields(Long id,String storeName,String address);
}
