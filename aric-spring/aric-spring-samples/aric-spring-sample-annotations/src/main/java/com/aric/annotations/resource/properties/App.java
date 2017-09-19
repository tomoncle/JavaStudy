package com.aric.annotations.resource.properties;

import com.aric.common.utils.PrinterUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * test
 * Created by liyuanjun on 16-11-18.
 */
public class App {

    @Test
    public void test(){
        ApplicationContext atc=new AnnotationConfigApplicationContext(SystemProperties.class);
        SystemPropertiesMap map=(SystemPropertiesMap)atc.getBean("systemPropertiesMap");
        PrinterUtils.printILog("SystemPropertiesMap toString:"+map.toString());

        Environment environment=(Environment)atc.getBean("environment");
        PrinterUtils.printILog("environment getBean password:"+environment.getProperty("password"));


    }

}
