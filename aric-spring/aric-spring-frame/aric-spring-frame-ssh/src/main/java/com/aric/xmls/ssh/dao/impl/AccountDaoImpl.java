package com.aric.xmls.ssh.dao.impl;

import com.aric.xmls.ssh.baseframe.base.impl.BaseDaoImpl;
import com.aric.xmls.ssh.dao.AccountDao;
import com.aric.xmls.ssh.entity.Account;
import org.hibernate.Query;

/**
 * Created by tom.lee on 2016/3/22.
 */
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {
    @Override
    public Account login(String username, String password) {
        String Hql="from Account where username=:username" +
                " and password=:password";
        Query query=this.getSessionFactory().getCurrentSession().createQuery(Hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Account account=(Account) query.uniqueResult();
        return account;
    }
}
