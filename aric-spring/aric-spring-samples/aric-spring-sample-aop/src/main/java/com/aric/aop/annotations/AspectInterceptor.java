package com.aric.aop.annotations;

import com.aric.common.utils.PrinterUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 定义切面类
 * Created by Administrator on 2016/7/24.
 */

@Component //组件扫描
@Aspect  //标注切面类
@EnableAspectJAutoProxy //启用自动注入切面，不然切面类不起作用
public class AspectInterceptor {

    private static int number = 0;

    /**
     * 定义切入点
     * Pointcut: 标注切面目标对象
     */
    @Pointcut("execution(* com.aric.aop.annotations..*.*(..))")
    void aspectPoint() {
    }

    /**
     * 前置通知，在方法执行前执行
     */
    @Before(value = "aspectPoint()")
    public void beforeInterceptor(JoinPoint joinPoint) {
        print(++number + "前置通知 ", joinPoint);
    }

    /**
     * 后置通知，在方法正常返回结果之后执行
     *
     * @param flag 方法返回值
     */
    @AfterReturning(value = "aspectPoint()", returning = "flag")
    public void afterReturningInterceptor(JoinPoint joinPoint, Object flag) {
        print(++number + "后置通知 ：返回值（" + flag + ")", joinPoint);
    }


    /**
     * 环绕通知，环绕方法执行
     */
    @Around(value = "aspectPoint()")
    public Object aroundInterceptor(ProceedingJoinPoint joinPoint) {
        print(++number + "环绕通知 ", joinPoint);
        try {
            Object result = joinPoint.proceed();
            PrinterUtils.printELog(++number + "环绕通知 （返回值：" + result + ")");
            return result;
        } catch (Throwable throwable) {
            PrinterUtils.printELog("发生异常:" + throwable);
            throwable.printStackTrace();
            return null;
        }
    }

    /**
     * 最终通知，在方法执行后执行,总会执行相当于finally模块
     */
    @After(value = "aspectPoint()")
    public void afterInterceptor(JoinPoint joinPoint) {
        PrinterUtils.printELog(++number + "最终通知:" +
                " " + joinPoint.getSignature().getName() + "结束执行");
    }

    /**
     * 异常通知，在方法抛出异常之后
     */
    @AfterThrowing(value = "aspectPoint()", throwing = "e")
    public void errorInterceptor(JoinPoint joinPoint, RuntimeException e) {
        print(++number + "异常通知 ", joinPoint);
        PrinterUtils.printELog("发生异常：" + e);
    }


    private void print(String use, JoinPoint joinPoint) {
        PrinterUtils.printELog(use + " 调用"
                + joinPoint.getTarget().getClass().getSimpleName()
                + "的" + joinPoint.getSignature().getName()
                + "方法。方法入参：" + Arrays.toString(joinPoint.getArgs()));
    }


}
