package com.user.config;

import com.utils.page.PagingInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置类
 */
@Configuration
public class MyBatisConfig {
    /**
     * 添加拦截器
     * @return 拦截器
     */
    @Bean
    public Interceptor getInterceptor(){
        return new PagingInterceptor();
    }


}
