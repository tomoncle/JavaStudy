package com.aric.aop.annotations;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/7/24.
 */
@Component
public class Student {

    private String name;

    public Student() {
    }

    public Student(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() throws Exception {
        System.out.println("hello :"+this.getName());
//        throw new Exception("aaa");
    }
}
