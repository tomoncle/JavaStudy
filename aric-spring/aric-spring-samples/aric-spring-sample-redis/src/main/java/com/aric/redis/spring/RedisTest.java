package com.aric.redis.spring;

import com.aric.common.utils.PrinterUtils;
import com.aric.redis.spring.config.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.JedisCluster;

/**
 * redis cluster for spring
 * Created by liyuanjun on 2016/11/20.
 */
public class RedisTest {
    public static void main(String[] args) {
        ApplicationContext atc=new AnnotationConfigApplicationContext(RedisConfig.class);
        JedisCluster obj = (JedisCluster) atc.getBean("jedisCluster");
        PrinterUtils.printILog(obj.get("username"));
        PrinterUtils.printILog(obj.getClusterNodes());
    }
}
