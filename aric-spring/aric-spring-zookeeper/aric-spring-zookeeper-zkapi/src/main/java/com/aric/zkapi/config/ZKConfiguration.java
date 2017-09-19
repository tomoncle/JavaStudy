package com.aric.zkapi.config;

import com.aric.common.utils.PrinterUtils;
import com.aric.zkapi.watcher.ZKWatcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by tom.lee on 2016/5/7.
 */
@Configuration
@ConfigurationProperties(prefix = "zookeeper")
public class ZKConfiguration {

    //zk地址
    private String url;
    //zk超时时间
    private int timeout;


    @Bean
    public ZooKeeper zooKeeper() throws IOException {
        PrinterUtils.printILog("获取zookeeper实例");
        return new ZooKeeper(url,timeout,new ZKWatcher());

    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
