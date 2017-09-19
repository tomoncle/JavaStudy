package com.aric.boot.serever.interceptor;

import com.aric.common.utils.PrinterUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录拦截器
 */
@Component
public class AuthInterceptorAdapter extends HandlerInterceptorAdapter {
    private Map<String, String> passURL = new HashMap<>();

    private List<String> passRegex = new ArrayList<>();

    public AuthInterceptorAdapter() {
        passURL.put("/", null);
        passURL.put("/region/getRegionTreeData", null);
        passRegex.add(".*.css$|.*.js$|.*.gif$|.*.png$|.*.jpg$");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        PrinterUtils.printELog("AuthInterceptorAdapter preHandle");
        String route = request.getServletPath();
        if (passURL.containsKey(route)) {
            return true;
        }
        return true;
    }

    private boolean forwardToLogin(HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException {
        String url = "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath()
                + "/login.jsp";
        response.sendRedirect(url);
        return false;
    }

}
