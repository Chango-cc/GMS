package edu.gdou.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
当前类的配置文件相当于xml
 */
@Configuration
public class Config {

    /*
    定义一个方法返回java的对象，放入Spring容器
    @Bean的使用修饰
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
