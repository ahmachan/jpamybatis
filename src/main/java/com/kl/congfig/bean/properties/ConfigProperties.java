package com.kl.congfig.bean.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "config.author")
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8") 
public class ConfigProperties {//JavaBean映射类
	//@Data用来简化JavaBean体态，使用该注解实现JavaBean的get、set等方法。
    private String name;
    private String age;
	private String address;
}