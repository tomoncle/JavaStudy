package com.aric.boot.serever.core.dataSource;

import com.aric.boot.serever.core.dataSource.config.MineC3P0DataSource;
import com.aric.boot.serever.core.dataSource.config.MineDBCPDataSource;
import com.aric.common.utils.PrinterUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * 创建数据源工厂
 * Created by aric on 2016/5/6.
 */

@Component
public class DataSourceFactory {

    @PostConstruct
    public void init() {
        PrinterUtils.printILog("加载数据源");
    }


    /**
     *配置数据源
     * @return
     */
    @Bean
    public DataSource dataSource() {
        MineDBCPDataSource basicDataSource=new MineDBCPDataSource();
        PrinterUtils.printELog("数据源加载信息:"+basicDataSource.getUsername());
        PrinterUtils.printELog("数据源加载信息:"+basicDataSource.getPassword());
        PrinterUtils.printELog("数据源加载信息:"+basicDataSource.getUrl());
        PrinterUtils.printELog("数据源加载信息:"+basicDataSource.getDriverClassName());

        return basicDataSource;
    }

}

