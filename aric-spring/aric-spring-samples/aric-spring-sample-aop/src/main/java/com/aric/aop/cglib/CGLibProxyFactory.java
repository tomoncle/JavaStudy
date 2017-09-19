package com.aric.aop.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/7/24.
 */
public class CGLibProxyFactory implements MethodInterceptor {

    private Object object;

    public Object createStudent(Object obj){
        this.object=obj;
        //创建一个Enhancer对象，来实例化obj
        Enhancer enhancer=new Enhancer();
        //将obj设置为Enhancer的父类，则Enhancer实现了obj的功能
        enhancer.setSuperclass(obj.getClass());
        //调用回调函数intercept
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println( //"o = [" + o + "]," +
                " method = [" + method + "]," +
                " objects = [" + objects + "], " +
                "methodProxy = [" + methodProxy + "]");
        Student student=(Student)this.object;
        Object target=null;
        if(student.getName()!=null){
            target=methodProxy.invoke(this.object,objects);
        }else{
            System.out.println("名称为空,目标被拦截！");
        }
        return target;
    }
}
