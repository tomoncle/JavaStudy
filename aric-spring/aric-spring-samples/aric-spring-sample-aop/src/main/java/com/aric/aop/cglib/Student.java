package com.aric.aop.cglib;

/**
 * Created by Administrator on 2016/7/24.
 */
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

    public void sayHello() {
        System.out.printf("hello :"+this.getName());
    }
}
