package com.kl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kl.mapper.UserMapper;
import com.kl.model.User;
import com.kl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	//@Autowired
	//private UserRepository userRepository;
	
	
	//使用mybatis
	public List<User> likeName(String name) {
		return userMapper.likeName(name);
	}

	public User getInfoById(Long id) {
		return userMapper.getInfoById(id);
	}

	public List<User> getUsers() {
		return userMapper.getUsers();
	}

	
	//@List
	public List<User> getListByPageWithXmlFetchList(){
		return userMapper.getListByPageWithXmlFetchList();
	}
	
	//@ListV2
	public List<User> getListByPageWithXmlFetchListV2(int pageNum,int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return userMapper.getListByPageWithXmlFetchList();
    }
	
	//@Page
    public Page<User> getListByPageWithXmlFetchPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.getListByPageWithXmlFetchPage();
    }

	//@PageInfo
	public PageInfo<User> queryListByPageWithXmlFetchPageInfo(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<User> list = userMapper.getListByPageWithXmlFetchList();
		return (new PageInfo<User>(list));
	}
	
	
	public List<User> getListByPageWithAnnotationFetchList(){
		return userMapper.getListByPageWithAnnotationFetchList();
	}
	
	
	//使用JPA
	/*public User findByName(String name){
		return userRepository.findByName(name);
	}
	
	public User findOneById(Long id) {
		return userRepository.findOneById(id);
	}*/

}
