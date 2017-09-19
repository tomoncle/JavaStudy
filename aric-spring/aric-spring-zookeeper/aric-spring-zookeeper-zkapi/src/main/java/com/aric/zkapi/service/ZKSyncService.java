package com.aric.zkapi.service;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by tom.lee on 2016/5/7.
 * 同步服务
 */
@Component
public class ZKSyncService {

    @Autowired
    private ZooKeeper zooKeeper;

    /**
     * 创建节点
     * eg:zookeeper.create("/node_1", "123".getBytes(),
     * Ids.OPEN_ACL_UNSAFE,
     * CreateMode.PERSISTENT);
     *
     * @param path       节点路径
     * @param data       数据
     * @param acl        权限
     * @param createMode 类型
     * @return
     */
    public String createNode(String path, byte[] data, List<ACL> acl, CreateMode createMode) throws Exception {
        if (!notExistsNode(path)) {
            throw new Exception("Node:" + path + " Already exist!");
        }
        return zooKeeper.create(path, data, acl, createMode);
    }

    /**
     * 创建节点
     *
     * @param path       节点路径
     * @param acl        权限
     * @param createMode 类型
     * @return
     */
    public String createNode(String path, List<ACL> acl, CreateMode createMode) throws Exception {
        return createNode(path, null, acl, createMode);
    }


    /**
     * 创建节点
     *
     * @param path       节点路径
     * @param createMode 类型
     * @return
     */
    public String createNode(String path, CreateMode createMode) throws Exception {
        return createNode(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
    }

    /**
     * 创建节点
     *
     * @param path     节点路径
     * @return
     */
    public String createNode(String path) throws Exception {
        return createNode(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }


    /**
     * 获取节点的子节点信息
     *
     * @param path    节点路径
     * @param watcher 是否监听
     * @return
     */
    public List<String> getChildrenNode(String path, boolean watcher) throws Exception {
        if (notExistsNode(path)) {
            throw new Exception("Node:" + path + " is not exist!");
        }
        return zooKeeper.getChildren(path, watcher);
    }

    /**
     * 获取子节点信息
     *
     * @param path 节点路径
     * @return
     */
    public List<String> getChildrenNode(String path) throws Exception {
        return getChildrenNode(path, false);
    }

    /**
     * 获取节点数据
     *
     * @param path    节点路径
     * @param watcher 监听
     * @param stat    节点状态信息
     * @return
     */
    public byte[] getNodeData(String path, boolean watcher, Stat stat) throws Exception {
        if (notExistsNode(path)) {
            throw new Exception("Node:" + path + " is not exist!");
        }
        return zooKeeper.getData(path, watcher, stat);
    }

    /**
     * 获取节点数据
     *
     * @param path 节点路径
     * @param stat 节点状态信息
     * @return
     */
    public byte[] getNodeData(String path, Stat stat) throws Exception {
        return getNodeData(path, false, stat);
    }


    /**
     *
     * 通过用户名密码获取节点数据
     *
     * @param path    节点路径
     * @param watcher 监听
     * @param stat    节点状态信息
     * @param username  用户
     * @param password  密码
     * @return
     * @throws Exception
     */
    public byte[] getNodeData(String path, boolean watcher, Stat stat,@NotNull String username,@NotNull String password) throws Exception{
        zooKeeper.addAuthInfo("digest",(username+":"+password).getBytes());
        return getNodeData(path, watcher, stat);
    }

    /**
     * 删除节点
     *
     * @param path    节点路径
     * @param version 版本
     * @return
     */
    public boolean deleteNode(String path, int version) {
        if (notExistsNode(path)) {
            return false;
        }
        try {
            zooKeeper.delete(path, version);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 删除节点
     *
     * @param path 节点路径
     * @return
     */
    public boolean deleteNode(String path) throws Exception {
        return deleteNode(path, -1);
    }


    /**
     * 判断节点是否存在
     *
     * @param path    节点路径
     * @param watcher 是否监听
     * @return
     */
    public Stat existsNode(String path, boolean watcher) {
        Stat stat = null;
        try {
            stat = zooKeeper.exists(path, watcher);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return stat;
    }

    /**
     * 判断节点是否存在
     *
     * @param path 节点路径
     * @return
     */
    public Stat existsNode(String path) {
        return existsNode(path, false);
    }

    /**
     * 判断节点是否存在
     *
     * @param path 节点路径
     * @return
     */
    boolean notExistsNode(String path) {
        return existsNode(path, false) == null ? true : false;
    }



}