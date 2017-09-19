package com.aric.annotations.resource.properties;

import com.aric.common.utils.PrinterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 系统参数
 * Created by liyuanjun on 16-11-18.
 */
@Component()
public class SystemPropertiesMap {

    //system.name=app
    @Value("#{'${system.name}'}")
    private String systemName;

    //system.encoding=utf-8
    @Value("#{'${system.encoding}'}")
    private String systemEncoding;

    //username=tom
    @Value("#{'${username}'}")
    private String username;

    //password=123456
    @Value("#{'${password}'}")
    private int password;

    //urls=127.0.0.1,localhost,172.16.4.211
    @Value("#{'${urls}'.split(',')}")
    private List<String> urls;

    //description 在配置文件中不存在，使用@Value("${key:value}")设置默认值
    @Value("${description:default}")
    private String description;


    public String getSystemName() {
        return systemName;
    }

    public String getSystemEncoding() {
        return systemEncoding;
    }

    public String getUsername() {
        return username;
    }

    public int getPassword() {
        return password;
    }

    public List<String> getUrls() {
        return urls;
    }

    public String getDescription() {
        return description;
    }


    @Autowired
    private Environment environment;

    @PostConstruct
    public void init(){
        PrinterUtils.printILog("SystemPropertiesMap初始化加载结束");
        PrinterUtils.printILog("使用Environment获取配置参数system.name："+environment.getProperty("system.name"));
    }

    @Override
    public String toString() {
        return "SystemPropertiesMap{" +
                "systemName='" + systemName + '\'' +
                ", systemEncoding='" + systemEncoding + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", urls=" + urls +
                ", description='" + description + '\'' +
                '}';
    }

}
