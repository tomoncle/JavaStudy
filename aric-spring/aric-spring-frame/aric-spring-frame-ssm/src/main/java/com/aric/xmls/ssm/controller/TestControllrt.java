package com.aric.xmls.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/7/21.
 */
@Controller("/")
public class TestControllrt {

    @RequestMapping
    public String login(){
        return "login";
    }

}
