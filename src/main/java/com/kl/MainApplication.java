package com.kl;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * 主应用入口
 *
 * @类名:MainApplication
 */
@Controller
@SpringBootApplication
@MapperScan("com.kl.mapper")
@Configuration
public class MainApplication {
    private static Logger logger = Logger.getLogger(MainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        logger.info("SpringBoot Start Success");
    }

}