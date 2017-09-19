package com.aric.xmls.ssh.dao;

import com.aric.xmls.ssh.baseframe.base.BaseDao;
import com.aric.xmls.ssh.entity.Account;

/**
 * Created by tom.lee on 2016/3/22.
 */
public interface AccountDao extends BaseDao<Account> {

    Account login(String username,String password);
}
