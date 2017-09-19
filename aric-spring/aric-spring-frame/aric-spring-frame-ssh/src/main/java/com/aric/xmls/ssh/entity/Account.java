package com.aric.xmls.ssh.entity;

import java.util.Date;

/**
 * Created by tom.lee on 2016/3/22.
 */
public class Account {

    //PK
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //登录时间
    private Date lastLogOn;
    //登录Ip
    private String lastLogOnIp;
    //登录次数
    private Integer logOnCount;


    public Account(String username, String password, Date lastLogOn, String lastLogOnIp, Integer logOnCount) {
        this.username = username;
        this.password = password;
        this.lastLogOn = lastLogOn;
        this.lastLogOnIp = lastLogOnIp;
        this.logOnCount = logOnCount;
    }

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogOn() {
        return lastLogOn;
    }

    public void setLastLogOn(Date lastLogOn) {
        this.lastLogOn = lastLogOn;
    }

    public String getLastLogOnIp() {
        return lastLogOnIp;
    }

    public void setLastLogOnIp(String lastLogOnIp) {
        this.lastLogOnIp = lastLogOnIp;
    }

    public Integer getLogOnCount() {
        return logOnCount;
    }

    public void setLogOnCount(Integer logOnCount) {
        this.logOnCount = logOnCount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastLogOn=" + lastLogOn +
                ", lastLogOnIp='" + lastLogOnIp + '\'' +
                ", logOnCount=" + logOnCount +
                '}';
    }
}

