package com.aric.mq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * 加载系统配置文件
 * Created by Administrator on 2016/11/17.
 */

@PropertySource({"classpath:application.properties"})
public class SystemConfigProperties extends PropertySourcesPlaceholderConfigurer {
}
