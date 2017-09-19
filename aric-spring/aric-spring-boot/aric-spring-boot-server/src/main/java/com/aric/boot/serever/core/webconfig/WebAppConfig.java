package com.aric.boot.serever.core.webconfig;

import com.aric.boot.serever.filter.EncodingFilter;
import com.aric.boot.serever.interceptor.AuthInterceptorAdapter;
import com.aric.common.utils.PrinterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2016/9/25.
 */

@Component
public class WebAppConfig  extends WebMvcConfigurerAdapter {

    /**
     * 注入自定义的拦截器
     */
    @Autowired
    private AuthInterceptorAdapter authInterceptorAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        PrinterUtils.printELog("拦截器执行");
        registry.addInterceptor(authInterceptorAdapter);
    }


}
