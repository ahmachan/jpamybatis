package com.kl.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回对象包装类(带泛型) .
 * 
 */
@Data
public class ResponseMap<T1,T> implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6883482050087865474L;

    /**
     * 成功
     */
    public static final int SUCCESS = 1;
    
    static final String SUCCESSMSG = "success";

    /**
     * 失败
     */
    public static final int FAILED = 0;
    
    static final String FAILEDMSG = "fail";


    /**
     * 构造函数
     */
    public ResponseMap() {
        super();
    }

    /**
     * 有参构造函数
     * 
     * @param data
     */
    public ResponseMap(T data) {
    	super();
    	SuccessResponse(data);
    }
    
    /**
     * 有参构造函数
     * 
     * @param data
     */
    public ResponseMap(T data,Integer status) {
    	super();
    	if(status>0){
    		SuccessResponse(data);
    	}else{
    		FailedResponse(data);
    	}
    }

    /**
     * success response
     * @param data
     */
    public Map<String, Object> SuccessResponse(T data) { 
    	
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", SUCCESSMSG);
        map.put("code",  SUCCESS);
        map.put("data", data);
    	
    	return map;
    }
    
    /**
     * failed response
     * @param data
     */
    public Map<String, Object> FailedResponse(T data) { 
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", FAILEDMSG);
        map.put("code",  FAILED);
        map.put("data", data);
    	
    	return map;
    }

}
