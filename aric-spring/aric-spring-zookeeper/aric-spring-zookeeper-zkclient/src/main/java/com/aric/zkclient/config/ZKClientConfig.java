package com.aric.zkclient.config;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tom.lee on 2016/5/7.
 */
@Configuration
@ConfigurationProperties(prefix = "zookeeper")
public class ZKClientConfig {

    //zk地址
    private String url;
    //zk连接超时时间
    private int connectionTimeout;
    //zk会话超时时间
    private int sessionTimeout;


    @Bean(name = "default")
    public ZkClient zkClient0() {
        return new ZkClient(url, connectionTimeout);
    }

    @Bean(name = "zkClient_usc")
    public ZkClient zkClient1() {
        return new ZkClient(url, sessionTimeout, connectionTimeout);

    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }


}
