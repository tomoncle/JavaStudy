package com.aric.boot.serever.controller;

import com.aric.boot.serever.entity.User;
import com.aric.boot.serever.service.UserService;
import com.aric.common.utils.PrinterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2016/5/15.
 */
@RestController
public class UserController {

    @Autowired
   private UserService userService;

    @RequestMapping("/getUserList")
    public List<User> getUserList(){
      return  userService.getUserList();

    }


    @RequestMapping("/addUser")
    public int addUser(User user){
        return  userService.addUser(user);

    }


    @RequestMapping("/getUserById")
    public User getUserById(String id){
        PrinterUtils.printELog(id);
        return  userService.getUserById(id);

    }

}
