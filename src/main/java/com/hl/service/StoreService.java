package com.hl.service;

import java.util.List;

import com.hl.model.Store;
import com.hl.model.StoreSimpleVo;

public interface StoreService {

	Store findOneById(Long id);
	
	List<Store> findListByType(Byte type);
	
	Store findDetailInfoById(Long id);
	
	Store findDetailInfo2ById(Long id);
	
	Store findDetailInfo3ById(Long id);
	
	Store findDetailInfo4ById(Long id);
	
	Object findDetailInfo4aById(Long id);

	Store findDetailInfo5ById(Long id);
	
	Store findDetailInfo6ById(Long id);
	
	Store findDetailInfo7ById(Long id);
	
	int updateAddress(Long storeId,String address);

	int modifyAddress(Long storeId,String address);
	
	List<Store> getStoreList();
	
	List<Store> getAllStore();	
	
	List<Store> getJpaSql2List();
	
	List<Store> getJpaSql3List();
	
	List<Store> getJpaSql4List();

	List<Store> getJpaSql5List();
	
	List<StoreSimpleVo> findJpaHqlList();
}
