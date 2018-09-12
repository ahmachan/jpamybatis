package com.hl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hl.congfig.bean.properties.ConfigProperties;
import com.hl.model.User;
import com.hl.service.UserService;
//import com.hl.utils.ResponseResult;
import com.hl.utils.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/users")
@Api("使用mybatis")
public class UserController {
	
	@Autowired
	private UserService userService;	
	
	@Autowired 
	private ConfigProperties configProperties;

	@RequestMapping(value = "/name/{name}", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "姓名模糊查询用户")
	public List<User> likeName(@PathVariable String name) {
		return userService.likeName(name);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "根据id查询用户")
	public ResponseResult<User> getUserInfoById(@PathVariable Long id) {
		//return userService.getInfoById(id);		
		return new ResponseResult<User>(userService.getInfoById(id));
	}
	
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "分页查询用户")
	public ResponseResult<List<User>> getUsers(
			@RequestParam(value = "num", required = false,defaultValue="1") int pageNum, 
			@RequestParam(value = "size", required = false,defaultValue="2") int pageSize
	   ) {
		PageHelper.startPage(pageNum, pageSize);
		//return userService.getUsers();		
		return new ResponseResult<List<User>>(userService.getUsers());
	}
	
	@RequestMapping(value = "xml/list", method = { RequestMethod.GET })
	@ApiOperation(value = "分页查询用户")
	public ResponseResult<List<User>> queryUserList(
		@RequestParam(value = "num", required = false,defaultValue="1") int pageNum, 
		@RequestParam(value = "size", required = false,defaultValue="2") int pageSize
   ) {
		PageHelper.startPage(pageNum, pageSize);
		//List<User> list =userService.getListByPageWithXmlFetchList();
		//ResponseResult<User> res = new ResponseResult<User>(list);
		//return res;
		//return userService.getListByPageWithXmlFetchList();
		return new ResponseResult<List<User>>(userService.getListByPageWithXmlFetchList());
	}
	
	@RequestMapping(value = "xml/pages", method = { RequestMethod.GET })
	@ApiOperation(value = "分页查询用户")
	public List<User> queryUserListByPage(
		@RequestParam(value = "num", required = false,defaultValue="1") int pageNum, 
		@RequestParam(value = "size", required = false,defaultValue="2") int pageSize
   ) {
		Page<User> users = userService.getListByPageWithXmlFetchPage(pageNum, pageSize);
		return users.getResult();
	}	
	
	@RequestMapping(value = "/xml/pages/m2", method = RequestMethod.GET)
	@ApiOperation(value = "分页查询用户")
	public List<User> queryUserListByPage2(
			@RequestParam(value = "num", required = false,defaultValue="1") int pageNum, 
			@RequestParam(value = "size", required = false,defaultValue="2") int pageSize
	) {
		PageInfo<User> users = userService.queryListByPageWithXmlFetchPageInfo(pageNum, pageSize);
		return users.getList();
	}
	
	@RequestMapping(value = "/config/properties", method = RequestMethod.GET)
	@ApiOperation(value = "实现自定义properties配置文件与JavaBean映射")
	public ResponseResult<List<ConfigProperties>> getConfigPropertyList() {
        List<ConfigProperties> configItem = new ArrayList<>();
        configItem.add(configProperties);

		//return configItem;		
		return new ResponseResult<List<ConfigProperties>>(configItem);
	}
	
	@RequestMapping(value = "/config/property/detail", method = RequestMethod.GET)
	@ApiOperation(value = "实现自定义properties配置文件与JavaBean映射")
	public ResponseResult<ConfigProperties> getConfigProperties() {
        List<ConfigProperties> configItem = new ArrayList<>();
        configItem.add(configProperties);
        /*
        String config = "config.author.name: " + configProperties.getName()
        + ", config.author.age:" + configProperties.getAge() 
        + ", config.author.addr:" + configProperties.getAddress();
        */
        /**
         * asList()返回的列表的大小是固定的，返回的列表不是java.util.ArrayList，
         * 而是定义在java.util.Arrays中一个私有静态类。
         * ArrayList的实现本质上是一个数组，而asList()返回的列表是由原始数组支持的固定大小的列表。
         * 这种情况下，如果添加或删除列表中的元素，程序会抛出异常
         */
        //List<ConfigProperties> list = Arrays.asList(array);
        //java.util.List              //java.util.Arrays 
		//return (ConfigProperties)configItem.get(0);//configProperties;
		
		return new ResponseResult<ConfigProperties>((ConfigProperties)configItem.get(0));
	}
	
}
