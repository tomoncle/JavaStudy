package com.aric.redis.spring.beans;

import com.aric.common.utils.PrinterUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;

/**
 * redis 集群配置
 * Created by liyuanjun on 2016/11/20.
 */
@Component
public class RedisBeans {
    //redis cluster
    @Value("#{'${redis.cluster.nodes}'.split(',')}")
    private List<String> clusterNodes;
    @Value("#{'${redis.cluster.sentinels}'.split(',')}")
    private List<String> clusterSentinels;
    @Value("#{'${redis.cluster.maxAttempts}'}")
    private int clusterMaxAttempts;
    @Value("#{'redis.cluster.master'}")
    private String clusterMaster;

    //redis poolConfig
    @Value("#{'${redis.config.maxTotal}'}")
    private int configMaxTotal;
    @Value("#{'${redis.config.maxIdle}'}")
    private int configMaxIdle;
    @Value("#{'${redis.config.maxWaitMillis}'}")
    private int configMaxWaitMillis;
    @Value("#{'${redis.config.testOnBorrow}'}")
    private boolean configTestOnBorrow;

    //redis
    @Value("#{'redis.password'}")
    private String password;
    @Value("#{'${redis.host}'}")
    private String host;
    @Value("#{'${redis.port}'}")
    private int port;
    @Value("#{'${redis.timeout}'}")
    private int timeout;



    @PostConstruct
    void init(){
        PrinterUtils.printILog("redis beans初始化结束");
    }

    /**
     * redis 连接池配置
     * @return
     */
    @Bean
    public JedisPoolConfig poolConfig(){
        JedisPoolConfig jpc=new JedisPoolConfig();
        jpc.setMaxIdle(this.configMaxIdle);
        jpc.setTestOnBorrow(this.configTestOnBorrow);
        jpc.setMaxWaitMillis(this.configMaxWaitMillis);
        jpc.setMaxTotal(this.configMaxTotal);
        return jpc;
    }

    /**
     * redis哨兵配置
     * @return
     */
    @Bean
    public RedisSentinelConfiguration sentinelConfig(){
        if(this.clusterSentinels.size()<=0 ||
                this.clusterSentinels.get(0).length()==0){
            return null;
        }
        return new RedisSentinelConfiguration(this.clusterMaster,
                new HashSet<>(this.clusterSentinels));
    }

    /**
     * redis 集群配置
     * @return
     */
    @Bean
    public RedisClusterConfiguration clusterConfig(){
        if(this.clusterNodes.size()<=0||
                this.clusterNodes.get(0).length()==0){
            return null;
        }
        return new RedisClusterConfiguration(this.clusterNodes);
    }


    /**
     *redis 单例
     * @param poolConfig
     * @return
     */
    @Bean(name = "connectionFactory")
    public RedisConnectionFactory connectionFactory(
            JedisPoolConfig poolConfig){
        JedisConnectionFactory jcf=new JedisConnectionFactory();
        jcf.setHostName(this.host);
        jcf.setPort(this.port);
        jcf.setTimeout(timeout);
//        jcf.setPassword(this.password);
        jcf.setPoolConfig(poolConfig);
        return jcf;
    }

    /**
     *redis 集群
     * @return
     */
    @Bean(name="clusterConnectionFactory")
    public RedisConnectionFactory clusterConnectionFactory(
            JedisPoolConfig poolConfig,
            RedisClusterConfiguration clusterConfig){
        if(clusterConfig!=null){
            return new JedisConnectionFactory(clusterConfig,poolConfig);
        }
        return connectionFactory(poolConfig);
    }
    /**
     *redis 主从
     * @return
     */
    @Bean(name="sentinelConnectionFactory")
    public RedisConnectionFactory sentinelConnectionFactory(
            JedisPoolConfig poolConfig,
            RedisSentinelConfiguration sentinelConfig){
        if(sentinelConfig!=null){
            JedisConnectionFactory jcf=new JedisConnectionFactory(sentinelConfig,poolConfig);
            jcf.setHostName(this.host);
            jcf.setPort(this.port);
            jcf.setTimeout(timeout);
//            jcf.setPassword(this.password);
            return jcf;
        }
        return connectionFactory(poolConfig);
    }


    @Bean
    public RedisTemplate redisTemplate(@Qualifier("connectionFactory")RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate= new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }



}
