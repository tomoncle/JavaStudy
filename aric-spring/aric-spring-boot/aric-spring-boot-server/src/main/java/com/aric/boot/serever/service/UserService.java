package com.aric.boot.serever.service;

import com.aric.boot.serever.entity.User;
import com.aricframework.core.base.BaseService;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
public interface UserService extends BaseService<User,String> {

    public List<User> getUserList();

    public int addUser(User user);

    public User getUserById(String id);
}
