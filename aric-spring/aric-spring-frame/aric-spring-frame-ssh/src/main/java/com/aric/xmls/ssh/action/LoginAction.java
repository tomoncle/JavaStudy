package com.aric.xmls.ssh.action;

import com.aric.common.utils.PrinterUtils;
import com.aric.xmls.ssh.baseframe.base.BaseAction;
import com.aric.xmls.ssh.entity.Account;
import com.aric.xmls.ssh.service.AccountService;
import org.apache.struts2.ServletActionContext;

/**
 * Created by tom.lee on 2016/3/22.
 */
public class LoginAction extends BaseAction {


    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        PrinterUtils.printELog("调用setAccountService..");
        this.accountService = accountService;
    }

    private String username;
    private String password;

    public String getUsername() {
        PrinterUtils.printELog("调用getUsername..");
        return username;
    }

    public void setUsername(String username) {
        PrinterUtils.printELog("调用setUsername..");
        this.username = username;
    }

    public String getPassword() {
        PrinterUtils.printELog("调用getPassword..");
        return password;
    }

    public void setPassword(String password) {
        PrinterUtils.printELog("调用setPassword..");
        this.password = password;
    }


    /**
     * 用户登录
     * @return
     * @throws Exception
     */
    public String login()throws Exception{
       Account account= accountService.login(username, password);
       if(account!=null){
           ServletActionContext.
                   getRequest().
                   getSession().
                   setAttribute("systemUser", account);
           return "login-success";
       }return "login-error";

    }
}
