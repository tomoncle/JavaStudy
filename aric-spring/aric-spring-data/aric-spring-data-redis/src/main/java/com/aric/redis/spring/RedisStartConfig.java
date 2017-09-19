package com.aric.redis.spring;

import com.aric.redis.spring.config.RedisProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 *
 * Created by liyuanjun on 2016/11/20.
 */
@Configuration
@Import({RedisProperties.class})
@ComponentScan({"com.aric.redis.spring"})
public class RedisStartConfig {




}
