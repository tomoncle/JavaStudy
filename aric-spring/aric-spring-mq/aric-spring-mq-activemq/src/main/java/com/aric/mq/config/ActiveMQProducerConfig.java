package com.aric.mq.config;

import com.aric.common.utils.PrinterUtils;
import com.aric.mq.factory.ActiveMQFactory;
import com.aric.mq.factory.Producer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * 生产者加载配置类
 * Created by liyuanjun on 16-11-17.
 */
@Configuration
@Import(value= {SystemConfigProperties.class,ActiveMQFactory.class,Producer.class})
public class ActiveMQProducerConfig {
    @PostConstruct
    void init(){
        PrinterUtils.printILog("初始化ActiveMQProducerConfig对象后加载");
    }
}
