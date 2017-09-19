package com.aric.annotations.resource.xml;

import com.aric.annotations.resource.properties.SystemProperties;
import com.aric.annotations.resource.properties.SystemPropertiesMap;
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
        ApplicationContext atc=new AnnotationConfigApplicationContext(SystemXml.class);
        SystemXmlMap map=(SystemXmlMap)atc.getBean("systemXmlMap");
        PrinterUtils.printILog(map.toString());

        Environment environment=(Environment)atc.getBean("environment");
        PrinterUtils.printILog(environment.getProperty("username"));
    }

}
