package com.aric.mq.factory;

import com.aric.common.utils.PrinterUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

/**
 * activeMQ工厂配置
 * Created by liyuanjun on 16-11-17.
 */
@Component
public class ActiveMQFactory {
    @Value("#{'${destination.queue}'}")
    private String destinationQueue;
    @Value("#{'${destination.topic}'}")
    private String destinationTopic;
    @Value("#{'${brokerUrl}'}")
    private String brokerUrl;

    @PostConstruct
    void init(){
        PrinterUtils.printILog("jms服务地址:"+this.brokerUrl);
    }

    @Bean
    public ConnectionFactory targetConnectionFactory() {
        return new ActiveMQConnectionFactory(this.brokerUrl);
    }

    @Bean
    public SingleConnectionFactory connectionFactory(ConnectionFactory targetConnectionFactory) {
        return new SingleConnectionFactory(targetConnectionFactory);
    }

    @Bean
    public ActiveMQQueue activeMQQueue() {
        return new ActiveMQQueue(this.destinationQueue);
    }


    @Bean
    public ActiveMQTopic activeMQTopic() {
        return new ActiveMQTopic(this.destinationTopic);
    }


}
