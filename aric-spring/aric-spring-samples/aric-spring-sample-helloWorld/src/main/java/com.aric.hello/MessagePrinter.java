package com.aric.hello;


import com.aric.common.utils.PrinterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by tom.lee on 2016/3/15.
 */
@Component
public class MessagePrinter {

    private MessageService service;

    @Autowired
    private String string;

    @PostConstruct
    void init() {
        PrinterUtils.printILog("初始化对象后加载");
    }

    @PreDestroy
    void destroy() {
        PrinterUtils.printILog("销毁对象前加载");
    }


    public MessagePrinter() {
        PrinterUtils.printILog("无参构造");
    }

    @Autowired
    public MessagePrinter(MessageService service) {
        PrinterUtils.printILog("带参构造1");
        this.service = service;
    }


    public MessagePrinter(MessageService service, String string) {
        PrinterUtils.printILog("带参构造2");
        ;
        this.service = service;
        this.string = string;
    }

    public void printMessage() {
        PrinterUtils.printILog(this.service + "@" + this.string);
        System.out.println(this.service.getMessage() + "@" + this.string);
    }


}
