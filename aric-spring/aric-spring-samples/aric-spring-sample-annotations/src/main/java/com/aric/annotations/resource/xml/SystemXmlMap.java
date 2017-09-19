package com.aric.annotations.resource.xml;

import com.aric.common.utils.PrinterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 系统参数,这种配置无法使用Environment获取参数
 * Created by liyuanjun on 16-11-18.
 */
@Component
public class SystemXmlMap {

    //system.encoding=utf-8
    @Value("#{applicationProperties['system.encoding']}")
    private String systemEncoding;

    //username=tom
    @Value("#{applicationProperties.username}")
    private String username;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init(){
        PrinterUtils.printILog("SystemXmlMap初始化加载结束");
        PrinterUtils.printELog("使用Environment获取配置参数："+environment.getProperty("system.name"));

    }

    @Override
    public String toString() {
        return "SystemXmlMap{" +
                "systemEncoding='" + systemEncoding + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
