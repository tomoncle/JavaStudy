package com.aric.annotations.started;

import com.aric.annotations.started.config.SpringConfiguration;
import com.aric.annotations.started.config.StringConfig;
import com.aric.common.utils.PrinterUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring注解测试入口
 * Created by tom.lee on 2016/3/16.
 */

public class App {

    /**
     * 使用通过构造函数加载配置类
     */
    @Test
    public void app1(){
        ApplicationContext atc=new AnnotationConfigApplicationContext(SpringConfiguration.class);
        String king=(String)atc.getBean("king");
        PrinterUtils.printBefore();
        PrinterUtils.printILog(king);
    }

    /**
     * 通过编码方式注册配置类
     */

     @Test
     public void app2(){
        AnnotationConfigApplicationContext atc=new AnnotationConfigApplicationContext();
        atc.register(SpringConfiguration.class);//注册配置类
        atc.refresh();//记得刷新

        String king=(String)atc.getBean("king");
        PrinterUtils.printBefore();
        PrinterUtils.printILog(king);
    }


    @Autowired
    StringConfig stringConfig;

    @Test
    public void app3(){
        AnnotationConfigApplicationContext atc=new AnnotationConfigApplicationContext();
        atc.register(SpringConfiguration.class);//注册配置类
        atc.refresh();//记得刷新
        PrinterUtils.printBefore();


        PrinterUtils.printILog(stringConfig);
    }




}
