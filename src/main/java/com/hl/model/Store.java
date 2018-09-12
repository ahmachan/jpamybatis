package com.hl.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;//javax.persistence.Id
import javax.persistence.Table;

@Entity
@Table(name="t_store")
public class Store implements Serializable {

	private static final long serialVersionUID = -1809244286782876088L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 20)
	private Long id;
    
	@Column(name = "login_name", unique = true, nullable = false, length = 50)
	private String loginName;

	@Column(name = "store_name", nullable = false,length=30)
	private String storeName;
	
	@Column(name = "type", nullable = false,length=1)
	private Byte type;
	
	@Column(name = "address", nullable = false,length=255)	
	private String address;
	
	@Column(name = "status", nullable = false,length=1)
	private Byte status;//java:hibernate:mysql : java.lang.Byte byte TINYINT ,java.lang.Short short SMALLINT
	
	@Column(name="time_diamond",precision=13, scale=4) 
	private BigDecimal timeDiamond; //double类型
	
	@Column(name = "add_time", nullable = false,length=11)
	private Integer addTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
	
	public BigDecimal getTimeDiamond() {
		return timeDiamond;
	}

	public void setTimeDiamond(BigDecimal timeDiamond) {
		this.timeDiamond = timeDiamond;
	}

	public Integer getAddTime() {
		return addTime;
	}

	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}

	

}