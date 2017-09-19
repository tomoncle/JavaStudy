package com.aric.xmls.ssm.interceptor;

import com.aric.xmls.ssm.entity.Account;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/10.
 */
public class AuthInterceptorAdapter extends HandlerInterceptorAdapter {

    private Map<String, String> passURL = new HashMap<>();

    private List<String> passRegex = new ArrayList<>();

    public AuthInterceptorAdapter() {
        passURL.put("/", null);
        passURL.put("/login", null);
        passURL.put("/common/changeLocale", null);
        passRegex.add(".*.css$|.*.js$|.*.gif$|.*.png$|.*.jpg$|.*.svg$" +
                "|.*.eot$|.*.ttf|.*.woff$|.*.woff2$|.*.map$|.*.ico$");
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String route = request.getServletPath();
        if (passURL.containsKey(route)) {
            return true;
        }
        for (String regex : passRegex) {
            if (route.matches(regex)) {
                return true;
            }
        }
        Account account=(Account)request.getSession().getAttribute("currentUser");
        if(account!=null){
            return true;
        }
        return redirect(request,response);
    }

    private boolean redirect(HttpServletRequest request, HttpServletResponse response){
        String url = "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();
        try {
//            request.getSession().setAttribute("redirect",request.getServletPath());
            response.sendRedirect(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
