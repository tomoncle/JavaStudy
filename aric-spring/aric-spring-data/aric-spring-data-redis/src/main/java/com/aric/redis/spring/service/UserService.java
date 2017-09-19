package com.aric.redis.spring.service;

import com.aric.redis.spring.entity.User;
import com.aric.redis.spring.vrdb.DBUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyuanjun on 2016/11/26.
 */
@Service
@CacheConfig(cacheNames ="UserService")
public class UserService {


    @Cacheable
    public List<User> getUsers(){
       return new DBUtils().getUsers();
    }


    @Cacheable
    public User getUserById(Integer id){
        return new DBUtils().findByUserId(id);
    }


    @CacheEvict(cacheNames={"UserService"},allEntries = true)
    public void deleteUser(Integer id){
        new DBUtils().deleteUser(id);
    }

}
