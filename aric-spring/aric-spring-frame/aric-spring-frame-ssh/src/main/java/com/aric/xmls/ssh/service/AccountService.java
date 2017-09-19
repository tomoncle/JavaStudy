package com.aric.xmls.ssh.service;

import com.aric.xmls.ssh.entity.Account;

/**
 * Created by tom.lee on 2016/3/22.
 */
public interface AccountService {

    Account login(String username,String password);

}
