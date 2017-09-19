package com.aric.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂
 * Created by Administrator on 2016/7/24.
 */
public class ProxyFactory implements InvocationHandler{

    /**
     * 实现代理aop的三个步骤：
     * 1.目标类实现接口
     * 2.返回代理对象
     * 3.重写invoke 方法
     */

    private Object object;

    /**
     * 该方法返回目标对象所实现的接口对象
     *
     * Proxy.newProxyInstance 方法用于返回目标对象实现的接口对象
     * 其中的this参数，即调用了重写的invoke方法，验证条件是否符合
     * @param obj
     * @return
     */
    public Object createStudent(Object obj){
        this.object=obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       Student student=(Student)this.object;
       Object target=null;
       if(student.getName()!=null){
           target=method.invoke(this.object,args);
       }else{
           System.out.println("名称为空,目标被拦截！");
       }
        return target;
    }

}
