package com.aric.boot.serever.core.dataSource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 使用阿里巴巴 DruidDataSource连接池
 * TODO  存在bug 待修复
 */
@ConfigurationProperties(prefix = "system.datasource.druid")
public class MineDruidDataSource extends DruidDataSource {

    private int initValue;

    public int getInitValue() {
        return initValue;
    }

    public void setInitValue(int initValue) {
        this.initValue = initValue;
    }
}
