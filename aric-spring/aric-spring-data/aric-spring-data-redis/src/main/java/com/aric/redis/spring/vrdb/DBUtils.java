package com.aric.redis.spring.vrdb;

import com.aric.common.utils.PrinterUtils;
import com.aric.redis.spring.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟数据库
 * Created by liyuanjun on 2016/11/26.
 */
public class DBUtils {
    private static List<User> users=new ArrayList<>();

    static {
        users.add(new User(1,"tom"));
        users.add(new User(2,"jack"));
        users.add(new User(3,"jenny"));
    }

    public List<User> getUsers(){
        PrinterUtils.printELog("调用数据库...");
        return users;
    }

    public User findByUserId(Integer id){
        PrinterUtils.printELog("调用数据库...param:"+id);
        for(User user:users){
            if(user.getId().equals(id))
                return user;
        }
        return null;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void deleteUser(Integer id){
        for(User user:users){
            if(user.getId().equals(id))
               users.remove(user);return;
        }
    }


}
