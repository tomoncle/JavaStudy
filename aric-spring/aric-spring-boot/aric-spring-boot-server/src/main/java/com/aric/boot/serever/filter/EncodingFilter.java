package com.aric.boot.serever.filter;

import com.aric.common.utils.PrinterUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义EncodingFilter过滤器
 */
@Component
public class EncodingFilter  implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        PrinterUtils.printELog("I18nFilter init");
    }

    /**
     * 此次应写相应的过滤代码
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");

        PrinterUtils.printELog("I18nFilter doFilter start");
        filterChain.doFilter(servletRequest, servletResponse);  //让目标资源执行，放行
        PrinterUtils.printELog("I18nFilter doFilter end");
    }

    @Override
    public void destroy() {
        PrinterUtils.printELog("I18nFilter destroy");
    }
}
