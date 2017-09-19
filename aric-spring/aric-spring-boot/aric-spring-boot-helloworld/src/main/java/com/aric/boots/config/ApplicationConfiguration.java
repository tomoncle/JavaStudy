package com.aric.boots.config;

import com.aric.common.utils.PrinterUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数配置 通过@Value注解来标注配置文件的key值
 * Created by Administrator on 2016/4/12.
 */
@Component
public class ApplicationConfiguration {
    @Value("${system.mybatis}")
    private String mybatisXML;
    @Value("${system.name}")
    private String systemName;
    @Value("${system.version}")
    private String systemVersion;
    @Value("${system.description}")
    private String systemDescription;
    @Value("${system.show_powered}")
    private Boolean systemShowPowered;

//    @Autowired
//    public ApplicationConfiguration(SystemDefinitionConfiguration systemDefinitionConfiguration){
//        this.mybatisXML=systemDefinitionConfiguration.getMybatis();
//        PrinterUtils.printILog("ApplicationConfiguration无参构造");
//    }


    @PostConstruct
    public void init(){
        PrinterUtils.printELog(applicationConfiguration());
    }


    public  Map<String ,Object> applicationConfiguration(){
      Map<String,Object> map=new HashMap<>();
        map.put("mybatisXML",this.mybatisXML);
        map.put("systemName",this.systemName);
        map.put("systemVersion",this.systemVersion);
        map.put("systemDescription",this.systemDescription);
        map.put("systemShowPowered",this.systemShowPowered);
        return  map;
    }


}
