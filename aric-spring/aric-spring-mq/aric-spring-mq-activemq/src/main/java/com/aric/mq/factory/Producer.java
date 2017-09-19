package com.aric.mq.factory;

import com.aric.common.utils.PrinterUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 生产者配置
 * Created by liyuanjun on 16-11-17.
 */
@Component
public class Producer {
    @PostConstruct
    void init(){
        PrinterUtils.printILog("初始化Producer对象后加载");
    }


    @Bean
    public JmsTemplate jmsTemplate(SingleConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

}
