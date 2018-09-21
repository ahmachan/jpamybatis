package com.kl.mapper;

import com.github.pagehelper.Page;
import com.kl.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
	//使用注解的方式
	@Select("select * from t_user where username like #{name}")
	public List<User> likeName(String name);

	@Select("select * from t_user where id = #{id}")
	public User getInfoById(Long id);
	
	@Select("select id,username,nickname,age,status,address,add_time from t_user where id >0")
	public List<User> getUserList();
	
	/**
     * 分页查询数据
     * @return
     */
	@Select("select id,username,nickname,age,status,address,add_time from t_user where id >0")
	public Page<User> getListByPageWithAnnotationFetchPage();
	
	/**
     * 分页查询数据
     * @return
     */
	@Select("select id,username,nickname,age,status,address,add_time from t_user where id >0")
	public List<User> getListByPageWithAnnotationFetchList();
	
	@Delete("DELETE FROM t_user WHERE id = #{id}")
    int delete(Long id);
	
	//使用xml的方式
	public List<User> getUsers();
	
	public List<User> getListByPageWithXmlFetchList();
	
	public Page<User> getListByPageWithXmlFetchPage();
}
