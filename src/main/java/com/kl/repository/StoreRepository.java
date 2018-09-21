package com.kl.repository;

import com.kl.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 不能定义UserRepository extends JpaRepository<User, Long>,就是删除UserRepository.java文件，因为用户的使用mybatis(xml),商家才使用jpa(hb)
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	
    Store findOneById(Long id);
    
    List<Store> findListByType(Byte type);
    
    @Query(value="FROM Store o WHERE o.id =:id")
    Store findStoreDetailById(@Param("id") Long id);
    
    @Query("FROM Store u WHERE u.id=:storeId")
    Store getStoreDetail2ById(@Param("storeId") Long id);
    
    @Query(value = "SELECT id,login_name,store_name,type,time_diamond,address,status,add_time FROM t_store WHERE id=?1", nativeQuery = true)
    Store getStoreDetail3ById(Long id);
    
    @Modifying
    @Query(value="UPDATE Store o SET o.address=:newAddress WHERE o.id =:storeId")
    int updateAddress(@Param("storeId") Long storeId,@Param("newAddress") String newAddress);

    //此时UPDATE的Store是实体类(StoreModel)
    @Modifying
    @Query(value="UPDATE Store xe SET xe.address=:address WHERE xe.id=:storeId ")
    int modifyAddress(@Param("storeId") Long id,@Param("address") String address); 
    
    @Query(value = "SELECT s FROM Store s ")
    List<Store> getStoreList();
    
    @Query(value="SELECT o FROM Store o WHERE o.id > :id ")
    List<Store> getAllStore(@Param("id") Long id);
}