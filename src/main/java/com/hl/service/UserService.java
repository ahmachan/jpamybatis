package com.hl.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hl.model.User;

public interface UserService {

	List<User> likeName(String name);

	User getInfoById(Long id);

	List<User> getUsers();
	
	/**
	 * 分页查询
	 * @return
	 */
	List<User> getListByPageWithXmlFetchList();
	
	/**
     * 分页查询
     * @param pageNum 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<User> getListByPageWithXmlFetchPage(int pageNum, int pageSize);
    
    PageInfo<User> queryListByPageWithXmlFetchPageInfo(int pageNum, int pageSize);
	
    List<User> getListByPageWithAnnotationFetchList();
    
	//Store findByName(String name);
	
	//Store findOneById(Long id);
	
}
