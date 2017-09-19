package com.aric.redis.spring.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by liyuanjun on 2016/11/20.
 */
@PropertySource("classpath:redis.properties")
public class RedisProperties extends PropertySourcesPlaceholderConfigurer{

}
