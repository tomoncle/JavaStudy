package com.aric.mq.config;

import com.aric.common.utils.PrinterUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * 加载配置扫描类
 * Created by liyuanjun on 16-11-17.
 */
@Configuration
@Import(value = {SystemConfigProperties.class})
@ComponentScan(basePackages = {"com.aric.mq.factory","com.aric.mq.listener"})
public class ActiveMQConfig {
    @PostConstruct
    void init(){
        PrinterUtils.printILog("初始化ActiveMQConfig对象后加载");
    }
}
