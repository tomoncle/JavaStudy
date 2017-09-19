package com.aric.annotations.started.config;

import com.aric.annotations.started.beans.Person;
import com.aric.common.utils.PrinterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tom.lee on 2016/3/16.
 */
@Component
public class StringConfig {

    private String string;

    private Person person;

    public StringConfig(){
        PrinterUtils.printILog("调用无参构造方法");
    }


    @Autowired
    public StringConfig(Person p,String s){
        this.string=s;
        this.person=p;
        PrinterUtils.printILog("StringConfig调用带参构造方法:"+string+"<>"+person);
    }

}
