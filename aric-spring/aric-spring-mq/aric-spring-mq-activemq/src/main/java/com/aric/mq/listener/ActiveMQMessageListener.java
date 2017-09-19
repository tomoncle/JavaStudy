package com.aric.mq.listener;

import com.aric.common.utils.PrinterUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.swing.*;

/**
 * 消费者监听
 * Created by liyuanjun on 16-11-17.
 */
@Component
public class ActiveMQMessageListener implements MessageListener {

    @PostConstruct
    void init(){
        PrinterUtils.printILog("初始化ActiveMQMessageListener对象后加载");
    }

    @Override
    public void onMessage(Message message) {
        PrinterUtils.printELog("接收到消息．．．");
        if(message instanceof TextMessage){
            String msg="";
            try {
                msg=((TextMessage) message).getText();
            }catch (Exception e){
                e.printStackTrace();
            }
            PrinterUtils.printILog("msg:"+msg);
        }else {
            PrinterUtils.printILog("messgae is not TextMessage");
        }
    }
}
