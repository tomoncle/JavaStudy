package com.aric.zkapi.service;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/5/7.
 * 异步服务
 */
@Component
public class ZKAsyncService {
    @Autowired
    private ZooKeeper zooKeeper;
}
