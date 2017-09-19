package com.aric.boot.serever.service.impl;

import com.aric.boot.serever.entity.User;
import com.aric.boot.serever.mapper.UserMapper;
import com.aric.boot.serever.service.UserService;
import com.aric.common.utils.BaseEnum;
import com.aric.common.utils.UUIDGenerator;
import com.aricframework.core.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public int addUser(User user) {
        //默认参数
        user.setId(UUIDGenerator.UUID());
        user.setCreateAt(new Date());
        user.setLastLogin(null);
        user.setLoginTimes(0);
        user.setIsDeleted(BaseEnum.DataLogicStatus.UN_DELETED.getCode());
        //int aa=12/0;//测试事物是否有效
       return userMapper.addUser(user);

    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }
}
