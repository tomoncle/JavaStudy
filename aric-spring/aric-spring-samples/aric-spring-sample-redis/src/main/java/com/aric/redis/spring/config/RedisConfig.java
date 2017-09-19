package com.aric.redis.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * Created by liyuanjun on 2016/11/20.
 */
@Configuration
@Import({RedisProperties.class})
@ComponentScan({"com.aric.redis.spring"})
public class RedisConfig {




}
