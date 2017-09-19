package com.aric.hello;

import com.aric.common.utils.PrinterUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tom.lee on 2016/3/15.
 */

@Configuration
@ComponentScan
public class ApplicationMain {



    @Bean
    MessageService mockMessageService() {
        return new MessageService() {
            public String getMessage() {
                return "Hello World!";
            }
        };
    }

    @Bean
    String string() {
       return "AC Milan";
    }

    public static void main(String[] args) {
        //方法入参，需要启动的扫描类
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationMain.class);
        //通过ApplicationContext获取Bean
        MessagePrinter printer = context.getBean(MessagePrinter.class);
        PrinterUtils.printBefore();
        printer.printMessage();
    }
}