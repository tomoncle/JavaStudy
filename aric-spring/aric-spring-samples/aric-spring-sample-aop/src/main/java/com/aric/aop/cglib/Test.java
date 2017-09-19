package com.aric.aop.cglib;

/**
 * Created by Administrator on 2016/7/24.
 */
public class Test {
    public static void main(String[] args) {
     Student student=new Student();
     CGLibProxyFactory cgLibProxyFactory=new CGLibProxyFactory();
     Student student1=(Student)cgLibProxyFactory.createStudent(student);
        student1.sayHello();
    }
}
