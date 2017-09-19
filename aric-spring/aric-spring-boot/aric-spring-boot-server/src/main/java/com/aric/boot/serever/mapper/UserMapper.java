package com.aric.boot.serever.mapper;

import com.aric.boot.serever.entity.User;
import com.aricframework.core.base.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * user mapper 接口，使用@Mapper 注解标注
 * Created by aric on 2016/5/5.
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查看全部用户信息
     * @return
     */
    @Select("select * from t_user")
    public List<User> getUserList();


    @Select("select * from t_user where id = #{id}")
    public User getUserById(@Param("id")String id);


    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into t_user " +
            "(id,username,password,createAt,isDeleted,loginTimes,lastLogin)" +
            "  values " +
            "(#{user.id}," +
            "#{user.username}," +
            "#{user.password}," +
            "#{user.createAt}," +
            "#{user.isDeleted}," +
            "#{user.loginTimes}," +
            "#{user.lastLogin})")
    public int addUser(@Param("user")User user);




}
