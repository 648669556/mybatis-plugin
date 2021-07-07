package com.chen.mybatisPlugin.config;

import com.chen.mybatisPlugin.interceptor.MyInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈俊宏
 */
@Configuration
public class InterceptorConfig {
    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }
}
