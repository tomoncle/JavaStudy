package com.aric.annotations.resource.xml;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 加载系统配置文件
 * Created by liyuanjun on 16-11-18.
 */

@Configuration
@ImportResource("classpath:xml/beans.xml")
@ComponentScan("com.aric.annotations.resource.xml")
public class SystemXml {



}
