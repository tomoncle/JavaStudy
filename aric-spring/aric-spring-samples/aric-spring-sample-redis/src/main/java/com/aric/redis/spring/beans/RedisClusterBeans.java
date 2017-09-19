package com.aric.redis.spring.beans;

import com.aric.common.utils.PrinterUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * redis 集群配置
 * Created by liyuanjun on 2016/11/20.
 */
@Component
public class RedisClusterBeans {
    @Value("#{'${redis.cluster.nodes}'.split(',')}")
    private List<String> clusterNodes;
    @Value("#{'redis.cluster.password'}")
    private String clusterPassword;
    @Value("#{'${redis.cluster.timeout}'}")
    private int clusterTimeout;
    @Value("#{'${redis.cluster.maxAttempts}'}")
    private int clusterMaxAttempts;
    @Value("#{'${redis.config.maxTotal}'}")
    private int configMaxTotal;
    @Value("#{'${redis.config.maxIdle}'}")
    private int configMaxIdle;
    @Value("#{'${redis.config.maxWaitMillis}'}")
    private int configMaxWaitMillis;
    @Value("#{'${redis.config.testOnBorrow}'}")
    private boolean configTestOnBorrow;

    @Override
    public String toString() {
        return "RedisClusterBeans{" +
                "clusterNodes=" + clusterNodes +
                ", clusterPassword='" + clusterPassword + '\'' +
                ", clusterTimeout=" + clusterTimeout +
                ", clusterMaxAttempts=" + clusterMaxAttempts +
                ", configMaxTotal=" + configMaxTotal +
                ", configMaxIdle=" + configMaxIdle +
                ", configMaxWaitMillis=" + configMaxWaitMillis +
                ", configTestOnBorrow=" + configTestOnBorrow +
                '}';
    }

    @PostConstruct
    void init(){
        PrinterUtils.printILog("redis cluster初始化结束");
        PrinterUtils.printILog(this.toString());
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jpc=new JedisPoolConfig();
        jpc.setMaxIdle(this.configMaxIdle);
        jpc.setTestOnBorrow(this.configTestOnBorrow);
        jpc.setMaxWaitMillis(this.configMaxWaitMillis);
        jpc.setMaxTotal(this.configMaxTotal);
        return jpc;
    }

    @Bean
    public JedisCluster jedisCluster(JedisPoolConfig jedisPoolConfig){
        Set<HostAndPort> sets=new HashSet<>();
        for (String node:clusterNodes){
            String host=node.split(":")[0];
            int port=Integer.valueOf(node.split(":")[1]);
            sets.add(new HostAndPort(host,port));
        }
        Assert.notNull(sets, "jedisClusterNode object must not be null");
        return new JedisCluster(sets,this.clusterTimeout,
                this.clusterMaxAttempts,jedisPoolConfig);

    }

}
