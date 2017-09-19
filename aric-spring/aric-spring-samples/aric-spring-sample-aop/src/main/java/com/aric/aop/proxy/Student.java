package com.aric.aop.proxy;

/**
 * 目标对象类 （实现代理的aop必须要实现代理的接口，即SayHiService）
 * Created by Administrator on 2016/7/24.
 */
public class Student implements SayHiService {


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

    @Override
    public void sayHello() {
        System.out.printf("hello :"+this.getName());
    }
}
