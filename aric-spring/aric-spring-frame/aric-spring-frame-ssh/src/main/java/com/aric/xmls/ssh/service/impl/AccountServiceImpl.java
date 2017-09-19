package com.aric.xmls.ssh.service.impl;

import com.aric.xmls.ssh.dao.AccountDao;
import com.aric.xmls.ssh.entity.Account;
import com.aric.xmls.ssh.service.AccountService;

/**
 * Created by tom.lee on 2016/3/22.
 */
public class AccountServiceImpl implements AccountService{

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account login(String username, String password) {
        return accountDao.login(username, password);
    }
}
