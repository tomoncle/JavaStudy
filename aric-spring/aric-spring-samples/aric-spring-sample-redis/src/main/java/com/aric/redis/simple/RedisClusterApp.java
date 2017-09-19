package com.aric.redis.simple;

import com.aric.common.utils.PrinterUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * redis集群测试
 * Created by liyuanjun on 2016/11/20.
 */
public class RedisClusterApp {
    public static void main(String[] args) {
        //加载服务列表
        Set<HostAndPort> redisClusterNodes =new HashSet<>();
        redisClusterNodes.add(new HostAndPort("192.168.137.147",7001));
        redisClusterNodes.add(new HostAndPort("192.168.137.147",7002));
        redisClusterNodes.add(new HostAndPort("192.168.137.147",7003));
        redisClusterNodes.add(new HostAndPort("192.168.137.147",7004));
        redisClusterNodes.add(new HostAndPort("192.168.137.147",7005));
        redisClusterNodes.add(new HostAndPort("192.168.137.147",7006));

        //redis配置
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxWaitMillis(-1);
        jedisPoolConfig.setTestOnBorrow(true);

        //redis集群
        JedisCluster jedisCluster=new JedisCluster(redisClusterNodes,6000,1000,jedisPoolConfig);

        PrinterUtils.printELog(jedisCluster.set("username","tom"));
        PrinterUtils.printELog(jedisCluster.set("age","23"));
        PrinterUtils.printILog(jedisCluster.get("username"));
        PrinterUtils.printILog(jedisCluster.get("age"));
        PrinterUtils.printILog(jedisCluster.get("age"));
        PrinterUtils.printILog(jedisCluster.get("username"));
        PrinterUtils.printILog(jedisCluster.get("password"));
        PrinterUtils.printILog(jedisCluster.getClusterNodes());

        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
