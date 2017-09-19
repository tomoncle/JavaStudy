package com.aric.mq.config;

import com.aric.common.utils.PrinterUtils;
import com.aric.mq.factory.ActiveMQFactory;
import com.aric.mq.factory.Consumer;
import com.aric.mq.listener.ActiveMQMessageListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * 消费者加载配置类
 * Created by liyuanjun on 16-11-17.
 */
@Configuration
@Import(value= {SystemConfigProperties.class,ActiveMQFactory.class,Consumer.class,ActiveMQMessageListener.class})
public class ActiveMQConsumerConfig {
    @PostConstruct
    void init(){
        PrinterUtils.printILog("初始化ActiveMQConsumerConfig对象后加载");
    }
}
