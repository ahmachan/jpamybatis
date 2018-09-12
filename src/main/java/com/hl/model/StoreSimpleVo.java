package com.hl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_store_simple")
public class StoreSimpleVo {
   
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 20)
	private Integer id;
	
	@Column(name = "login_name", unique = true, nullable = false, length = 50)
	private String loginName;

	@Column(name = "store_name", nullable = false,length=30)
	private String storeName;
	
	@Column(name = "add_time", nullable = false,length=11)
	private Integer addTime;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getAddTime() {
		return addTime;
	}

	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}

}