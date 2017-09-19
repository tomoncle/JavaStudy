package com.aric.aop.proxy;

/**
 * Created by Administrator on 2016/7/24.
 */
public class Test {
    public static void main(String[] args) {
        SayHiService student1=new Student("tom");
        ProxyFactory proxyFactory=new ProxyFactory();
        SayHiService say=(SayHiService)proxyFactory.createStudent(student1);
        say.sayHello();
    }
}
