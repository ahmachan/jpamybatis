package com.hl.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hl.model.Store;
import com.hl.service.StoreService;
import com.hl.utils.ResponseMap;
import com.hl.utils.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/stores")
@Api("使用JPA的方式")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "根据id查询用户")
	public ResponseResult<Store> getStoreInfoById(@PathVariable Long id) {
		return new ResponseResult<Store>(storeService.findOneById(id));
	}
	
	@RequestMapping(value = "/cates/{type}", method = RequestMethod.GET)
	@ApiOperation(value = "根据type查询用户")
	public ResponseResult<List<Store>> getStoreListByType(@PathVariable Byte type) {
		List<Store> data = storeService.findListByType(type);
		Integer res = (data==null||data.size()==0)?0:1;
		return new ResponseResult<List<Store>>(data,res);
	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id查询用户")
	public ResponseResult<Store> getDetailById(@PathVariable Long id) {
		Store data = storeService.findDetailInfoById(id);
		Integer res = (data==null)?0:1;
		return new ResponseResult<Store>(data,res);
	}
	
	@RequestMapping(value = "/detail2/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id查询用户")
	public ResponseResult<Store> getDetail2ById(@PathVariable Long id) {
		Store data = storeService.findDetailInfo2ById(id);
		Integer res = (data==null)?0:1;
		return new ResponseResult<Store>(data,res);
	}
	
	@RequestMapping(value = "/detail3/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id查询用户")
	public ResponseResult<Store> getDetail3ById(@PathVariable Long id) {
		Store data = storeService.findDetailInfo3ById(id);
		Integer res = (data==null)?0:1;
		return new ResponseResult<Store>(data,res);
	}
	
	@RequestMapping(value = "/detail4/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id查询用户")
	public Map<String, Object> getDetail4ById(@PathVariable Long id) {
		Object data = (Object)storeService.findDetailInfo4ById(id);
		ResponseMap<String, Object> resMap =new ResponseMap<String, Object>();
		if(data == null){
			return resMap.FailedResponse(data);
		}
		return resMap.SuccessResponse(data);
	}
	
	@RequestMapping(value = "/detail4a/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id查询用户")
	public Map<String, Object> getDetail4aById(@PathVariable Long id) {
		Object data = (Object)storeService.findDetailInfo4aById(id);
		//Integer res = (data==null)?0:1;
		//return new ResponseResult<Store>(data,res);
		ResponseMap<String, Object> resMap =new ResponseMap<String, Object>();
		if(data == null){
			return resMap.FailedResponse(data);
		}
		return resMap.SuccessResponse(data);
	}
	
	@RequestMapping(value = "/detail4b/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id查询用户")
	public ResponseResult<Object> getDetail4bById(@PathVariable Long id) {
		Object data = (Object)storeService.findDetailInfo4ById(id);
		Integer res = (data==null)?0:1;
		return new ResponseResult<Object>(data,res);		
	}

	@RequestMapping(value = "/detail5/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id查询用户")
	public ResponseResult<Store> getDetail5ById(@PathVariable Long id) {
		Store data = storeService.findDetailInfo5ById(id);
		Integer res = (data==null)?0:1;
		return new ResponseResult<Store>(data,res);
	}	
	
	@RequestMapping(value = "/detail6/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id查询用户")
	public ResponseResult<Store> getDetail6ById(@PathVariable Long id) {
		Store data = storeService.findDetailInfo6ById(id);
		Integer res = (data==null)?0:1;
		return new ResponseResult<Store>(data,res);
	}
	
	@RequestMapping(value = "/detail7/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据id查询用户")
	public ResponseResult<Store> getDetail7ById(@PathVariable Long id) {
		Store data = storeService.findDetailInfo7ById(id);
		Integer res = (data==null)?0:1;
		return new ResponseResult<Store>(data,res);
	}
	
	@RequestMapping(value = "/{storeId}/address", method = RequestMethod.POST)
	@ApiOperation(value = "编辑用户的地址")
	public ResponseResult<Integer> updateAddress(
		@PathVariable Long storeId,
		@RequestParam(value = "address", required = true,defaultValue="") String address
	) {
		Integer intRes = storeService.modifyAddress(storeId, address);
		return new ResponseResult<Integer>(null,intRes);
	}
	
	@RequestMapping(value = "/{storeId}/address", method = RequestMethod.PUT)
	@ApiOperation(value = "编辑用户的地址")
	public ResponseResult<Integer> modifyAddress(
		@PathVariable Long storeId,
		@RequestParam(value = "address", required = true,defaultValue="") String address
	) {
		Integer intRes = storeService.modifyAddress(storeId, address);
		return new ResponseResult<Integer>(null,intRes);
	}
	
	@RequestMapping(value = "/list/?", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getStoreList() {
		return new ResponseResult<List<Store>>(storeService.getStoreList());
	}
	
	@RequestMapping(value = "/alls/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getAllStore() {
		return new ResponseResult<List<Store>>(storeService.getAllStore());
	}
	
	@RequestMapping(value = "/jpaSql3List/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaSql3List() {
		return new ResponseResult<List<Store>>(storeService.getJpaSql3List());
	}
	
	@RequestMapping(value = "/jpaSql3aList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaSql3aList() {
		return new ResponseResult<List<Store>>(storeService.getJpaSql3aList());
	}

	@RequestMapping(value = "/jpaSql3bList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Object[]>> getJpaSql3bList() {
		return new ResponseResult<List<Object[]>>(storeService.getJpaSql3bList());
	}
	
	@RequestMapping(value = "/jpaSql4List/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaSql4List() {
		return new ResponseResult<List<Store>>(storeService.getJpaSql4List());
	}
	
	@RequestMapping(value = "/jpaSql4aList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaSql4aList() {
		return new ResponseResult<List<Store>>(storeService.getJpaSql4aList());
	}
	
	@RequestMapping(value = "/jpaSql4bList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Object[]>> getJpaSql4bList() {
		return new ResponseResult<List<Object[]>>(storeService.getJpaSql4bList());
	}
	
	//HqlList
	@RequestMapping(value = "/jpaHqlList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaHqlList() {
		return new ResponseResult<List<Store>>(storeService.getJpaHqlList());
	}
	
	//Hql2List
	@RequestMapping(value = "/jpaHql2List/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaHql2List() {
		return new ResponseResult<List<Store>>(storeService.getJpaHql2List());
	}
	
	@RequestMapping(value = "/jpaHql2aList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaHql2aList() {
		return new ResponseResult<List<Store>>(storeService.getJpaHql2aList());
	}
	
	@RequestMapping(value = "/jpaHql2bList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaHql2bList() {
		return new ResponseResult<List<Store>>(storeService.getJpaHql2bList());
	}
	
	@RequestMapping(value = "/jpaHql2cList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Object[]>> getJpaHql2cList() {
		return new ResponseResult<List<Object[]>>(storeService.getJpaHql2cList());
	}
	
	//Hql3List
	@RequestMapping(value = "/jpaHql3List/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaHql3List() {
		return new ResponseResult<List<Store>>(storeService.getJpaHql3List());
	}
	
	@RequestMapping(value = "/jpaHql3aList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaHql3aList() {
		return new ResponseResult<List<Store>>(storeService.getJpaHql3aList());
	}
	
	@RequestMapping(value = "/jpaHql3bList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Store>> getJpaHql3bList() {
		return new ResponseResult<List<Store>>(storeService.getJpaHql3bList());
	}
	
	@RequestMapping(value = "/jpaHql3cList/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表")
	public ResponseResult<List<Object[]>> getJpaHql3cList() {
		return new ResponseResult<List<Object[]>>(storeService.getJpaHql3cList());
	}
	
	@RequestMapping(value = "/listByTypes/{typeCate}/", method = RequestMethod.GET)
	@ApiOperation(value = "商家列表分页获取")
	public ResponseResult<List<Store>> listByTypes(
			@PathVariable Byte typeCate,
			@RequestParam(name="begin",required=false,defaultValue="1") Integer begin,
			@RequestParam(name="size",required=false,defaultValue="3") Integer size
			) {
		int start = begin;
		int pageNumber=size;
		return new ResponseResult<List<Store>>(storeService.getStoreListByTypesWithPages(typeCate, start, pageNumber));
	}
	
	@RequestMapping(value = "/listByTypes/{typeCate}/count/", method = RequestMethod.GET)
	@ApiOperation(value = "商家数据总数")
	public ResponseResult<Object> listCountByTypes(@PathVariable Byte typeCate) {
		return new ResponseResult<Object>(storeService.getStoreCountByTypes(typeCate));
	}
	
	@RequestMapping(value = "/{storeId}/item", method = RequestMethod.POST)
	@ApiOperation(value = "编辑商家信息")
	public ResponseResult<Integer> updateStoreItem(
		@PathVariable Long storeId,
		@RequestParam(value = "storeName", required = true,defaultValue="") String storeName,
		@RequestParam(value = "address", required = false,defaultValue="") String address
	) {
		Integer objRes = storeService.updateStoreByFields(storeId, storeName, address);
		return new ResponseResult<Integer>(objRes);
	}
}
