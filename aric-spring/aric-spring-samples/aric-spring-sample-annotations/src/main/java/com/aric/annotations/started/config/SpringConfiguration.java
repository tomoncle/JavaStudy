package com.aric.annotations.started.config;

import com.aric.annotations.started.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置参数类
 * Created by tom.lee on 2016/3/16.
 */
@Configuration
@ComponentScan(basePackages = "com.aric.annotations.started")
public class SpringConfiguration {
    @Bean
    public String  king(){
        return "KIng";
    }

    @Bean
    public Person person(){
        return new Person();
    }
}
