package com.aric.mq.config;

import com.aric.common.utils.PrinterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * 加载配置测试类
 * Created by liyuanjun on 16-11-17.
 */
@Configuration
@Import(value ={SystemConfigProperties.class})
public class SpringConfiguration {
    @Autowired
    private SystemConfigProperties systemConfigProperties;
    @Autowired
    private Environment environment;
    @Autowired
    private PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;

    @Value("#{'${brokerUrl}'}")
    private String brokerUrl;

    @PostConstruct
    void init(){
        PrinterUtils.printILog("初始化SpringConfiguration对象后加载");
        PrinterUtils.printILog("environment:"+environment.getProperty("brokerUrl"));
        PrinterUtils.printILog("propertySourcesPlaceholderConfigurer:"+propertySourcesPlaceholderConfigurer);
        PrinterUtils.printILog("brokerUrl:"+this.brokerUrl);
        PrinterUtils.printILog("systemConfigProperties:"+systemConfigProperties);
    }

}
