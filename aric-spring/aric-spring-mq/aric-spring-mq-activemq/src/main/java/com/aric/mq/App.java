package com.aric.mq;

import com.aric.common.utils.PrinterUtils;
import com.aric.mq.config.*;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Description;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 使用通过构造函数加载配置类
 * Created by liyuanjun on 16-11-17.
 */
public class App {


    /**
     * 生产者和消费者
     * @throws Exception
     */
    @Test
    public void MQ()throws Exception{
        ApplicationContext atc=new AnnotationConfigApplicationContext(ActiveMQConfig.class);
        //获得JmsTemplate对象
        JmsTemplate template = (JmsTemplate) atc.getBean("jmsTemplate");
        //获得Destination
        ActiveMQQueue queue = (ActiveMQQueue) atc.getBean("activeMQQueue");
        send(template,queue);
        PrinterUtils.printLove();

    }


    /**
     *生产者
     */
    @Test
    public void producer()throws Exception{
        ApplicationContext atc=new AnnotationConfigApplicationContext(ActiveMQProducerConfig.class);
        //获得JmsTemplate对象
        JmsTemplate template = (JmsTemplate) atc.getBean("jmsTemplate");
        //获得Destination
        ActiveMQTopic topic = (ActiveMQTopic) atc.getBean("activeMQTopic");
        send(template,topic);
        PrinterUtils.printLove();

    }


    /**
     * 消费者
     * @throws Exception
     */
    @Test
    public void consumer()throws Exception{
        ApplicationContext atc=new AnnotationConfigApplicationContext(ActiveMQConsumerConfig.class);
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }


    /**
     * 发送数据
     * @param jmsTemplate
     * @param queue
     */
    public void send(JmsTemplate jmsTemplate,ActiveMQDestination queue){
        for(int i=0;i<10;i++){
            final int k=i;
            //发送消息
            jmsTemplate.send(queue, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage("hello！this is number："+k);
                }
            });
        }

    }

    @Test
    public void loadSystemConfig(){
        new AnnotationConfigApplicationContext(SpringConfiguration.class);
        PrinterUtils.printELog(">>>>>>>>>>>>>>>>>");
    }

}
