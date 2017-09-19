package com.aric.xmls.ssh.service;



import com.aric.xmls.ssh.entity.User;

import java.io.Serializable;

/**
 * Created by tom.lee on 2016/3/17.
 */
public interface UserService {

    /**
     * 添加用户
     * @param user
     */
     void  addUser(User user);


    /**
     * 添加用户
     * @param user
     */
     Serializable saveUser(User user);

    /**
     * 删除用户
     * @param id
     */
     void  delUser(Serializable id);

    /**
     * 删除用户
     * @param user
     */
     void  delUser(User user);


    /**
     * 查询用户
     * @param id
     * @return
     */
    User  findUserById(Serializable id);


}
