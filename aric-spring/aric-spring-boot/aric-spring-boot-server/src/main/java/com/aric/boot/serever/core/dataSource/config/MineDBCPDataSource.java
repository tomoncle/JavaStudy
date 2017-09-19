package com.aric.boot.serever.core.dataSource.config;

import com.aric.common.utils.PrinterUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * 使用dbcp连接池可以有两种方式(tomcat pool同理,详情见application配置文件）
 *
 * 1 spring boot 默认支持dbcp连接池,直接在application.properties配置文件里配置参数即可
 * 1.1 需要加入dbcp的jar包
 * 1.2 spring.datasource.url=
 *     spring.datasource.username=
 *     spring.datasource.password=
 *     spring.datasource.driverClassName=
 *     spring.datasource.xxxxx=
 * ##############################################
 * 2自定义配置dbcp连接池
 * 2.1 如下代码
 * 2.2 在application.properties配置文件里配置system.datasource.dbcp.xxx参数即可
 *
 * system.datasource.dbcp 为自定义前缀
 */

@ConfigurationProperties(prefix = "system.datasource.dbcp")
public class MineDBCPDataSource extends BasicDataSource {

    @PostConstruct
    public void init() {
        PrinterUtils.printILog("加载MineDBCPDataSource数据源");
    }


    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
