package com.chen.mybatisPlugin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 陈俊宏
 */
@SpringBootApplication
@MapperScan("com.chen.mybatisPlugin.dao.mapping")
@EnableAspectJAutoProxy
public class MybatisPluginApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPluginApplication.class, args);
    }

}
