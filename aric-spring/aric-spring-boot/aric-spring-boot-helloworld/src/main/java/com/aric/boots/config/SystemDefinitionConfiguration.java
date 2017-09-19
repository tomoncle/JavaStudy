package com.aric.boots.config;

import com.aric.common.utils.PrinterUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 支持系统自定义的属性配置
 * Created by Administrator on 2016/4/12.
 */
@Component
@ConfigurationProperties(prefix="system.config")
public class SystemDefinitionConfiguration {


    private String port;
    private String ip;
    private String author;
    private String mybatis;

    public SystemDefinitionConfiguration(){
        PrinterUtils.printILog("SystemDefinitionConfiguration无参构造");
    }

    @PostConstruct
    public void  init(){
        PrinterUtils.printILog("SystemDefinitionConfiguration初始化 "+toString());
    }

    public String getMybatis() {
        return mybatis;
    }

    public void setMybatis(String mybatis) {
        this.mybatis = mybatis;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "SystemDefinitionConfiguration{" +
                "port='" + port + '\'' +
                ", ip='" + ip + '\'' +
                ", author='" + author + '\'' +
                ", mybatis='" + mybatis + '\'' +
                '}';
    }
}
