package com.aric.mq.factory;

import com.aric.common.utils.PrinterUtils;
import com.aric.mq.listener.ActiveMQMessageListener;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 消费者配置
 * Created by liyuanjun on 16-11-17.
 */
@Component
public class Consumer {
    @PostConstruct
    void init() {
        PrinterUtils.printILog("初始化Consumer对象后加载");
    }

    /**
     * 队列消息监听容器
     *
     * @param connectionFactory       JMS Connection的ConnectionFactory
     * @param activeMQQueue           消息类型
     * @param activeMQMessageListener 　消息监听
     * @return
     */
    @Bean(name = "queueListenerContainer")
    public DefaultMessageListenerContainer queueListenerContainer(
            SingleConnectionFactory connectionFactory,
            ActiveMQQueue activeMQQueue,
            ActiveMQMessageListener activeMQMessageListener) {
        return listenerContainer(connectionFactory,activeMQQueue,activeMQMessageListener);
    }

    /**
     * 发布、订阅消息监听容器
     *
     * @param connectionFactory       JMS Connection的ConnectionFactory
     * @param activeMQTopic           消息类型
     * @param activeMQMessageListener 　消息监听
     * @return
     */
    @Bean(name = "topicListenerContainer")
    public DefaultMessageListenerContainer topicListenerContainer(
            SingleConnectionFactory connectionFactory,
            ActiveMQTopic activeMQTopic,
            ActiveMQMessageListener activeMQMessageListener) {
        return listenerContainer(connectionFactory,activeMQTopic,activeMQMessageListener);
    }

    private DefaultMessageListenerContainer listenerContainer(
             SingleConnectionFactory connectionFactory,
             ActiveMQDestination destination,
             ActiveMQMessageListener activeMQMessageListener) {
        DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setDestination(destination);
        listenerContainer.setMessageListener(activeMQMessageListener);
        return listenerContainer;
    }
}
