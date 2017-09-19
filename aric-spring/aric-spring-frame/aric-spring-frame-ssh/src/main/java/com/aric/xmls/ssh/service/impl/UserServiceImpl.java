package com.aric.xmls.ssh.service.impl;

import com.aric.common.utils.PrinterUtils;
import com.aric.xmls.ssh.dao.UserDao;
import com.aric.xmls.ssh.entity.User;
import com.aric.xmls.ssh.service.UserService;
import org.hibernate.HibernateException;


import java.io.Serializable;

/**
 * Created by tom.lee on 2016/3/17.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void addUser(User user) {
        userDao.addEntity(user);
    }

    @Override
    public Serializable saveUser(User user) {
        return  userDao.addEntity(user);
    }

    @Override
    public void delUser(Serializable id) {
        userDao.deleteById(id,new User());
    }

    @Override
    public void delUser(User user) {
        try {
            userDao.deleteEntity(user);
        } catch (Exception e) {
            throw new HibernateException("删除失败，未返回指定唯一记录！"+this.getClass().getName().toString());
        }
    }

    @Override
    public User findUserById(Serializable id) {
       return userDao.findById(User.class,id);
    }
}
