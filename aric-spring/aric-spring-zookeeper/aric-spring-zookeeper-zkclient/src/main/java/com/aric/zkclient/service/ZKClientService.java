package com.aric.zkclient.service;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by aric on 2016/5/9.
 */
@Component
public class ZKClientService<T> {

    @Resource(name="default")
    private  ZkClient zkClient;


    /**
     * 获取节点信息
     * @param path 节点路径
     * @return
     */
    public T getNodeData(String path){
       return  zkClient.readData(path);
    }

    /**
     * 获取子节点列表
     * @param path 节点路径
     * @return
     */
    public List<String> getChildNode(String path){
        return zkClient.getChildren(path);
    }


    /**
     * 删除节点
     * @param path 节点路径
     * @return
     */
    public boolean  deleteNode(String path){
        return zkClient.delete(path);
    }


    /**
     * 递归删除
     * @param path
     * @return
     */
    public boolean deleteNodeChildren(String path){
        return zkClient.deleteRecursive(path);
    }


    /**
     * 创建持久节点
     * @param path
     */
    public void  createPersistentNode(String path){
         zkClient.createPersistent(path,true);
    }

}
