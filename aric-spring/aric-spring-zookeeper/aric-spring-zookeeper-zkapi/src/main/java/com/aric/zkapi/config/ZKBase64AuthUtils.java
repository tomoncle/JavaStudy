package com.aric.zkapi.config;

import com.aric.common.utils.StringUtils;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/8.
 */
public class ZKBase64AuthUtils {


    /**
     * 获取用户名密码加密字符串
     * @param username 用户名
     * @param password 密码
     * @return
     */
     public static  String  getDigest(String username,String password){
         if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
             return null;
         }
         try {
            return DigestAuthenticationProvider.generateDigest(username+":"+password);
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
         return null;
     }



}
