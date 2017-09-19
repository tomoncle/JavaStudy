package com.aric.zkclient.api.nameservice;

import com.aric.common.utils.StringUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用zk 命名服务功能实现分布式全局唯一ID
 * Created by Administrator on 2016/6/2.
 */
public class IdGenerator {

    //节点前缀
    private static final String NODE_PREFIX = "000000";
    //zk 根节点
    private static final String HOME = "/";
    //id 生成目录根节点
    private static final String BASE_PATH = "zkNameService";
    //zk client
    private ZkClient zkClient = null;
    //zk 服务器地址
    private final String url;
    //zk 顺序节点的根节点
    private final String root;
    //zk 顺序节点的父节点
    private final String parent;
    //zk 顺序节点-路径前缀
    private final String nodeName;
    //zk连接超时时间
    private final int connectionTimeout = 5000;
    //zk会话超时时间
    private final int sessionTimeout = 5000;
    //线程池
    private ExecutorService cleanExecutors;
    // 连接状态
    private volatile boolean running = false;


    /**
     * @param url        zk 连接地址
     * @param database   生成的数据库名称
     * @param table      生成的表名称
     * @param nodePrefix 前缀
     */
    public IdGenerator(String url, String database, String table, String nodePrefix) {
        this.url = url;
        this.root = StringUtils.isEmpty(database) ? BASE_PATH : database;
        this.parent = table;
        this.nodeName = StringUtils.isEmpty(nodePrefix) ? NODE_PREFIX : nodePrefix;
    }

    /**
     * @param url      zk 连接地址
     * @param database 生成的数据库名称
     * @param table    生成的表名称
     */
    public IdGenerator(String url, String database, String table) {
        this(url, database, table, null);
    }


    /**
     * @param url   zk 连接地址
     * @param table 生成的表名称
     */
    public IdGenerator(String url, String table) {
        this(url, null, table);
    }


    public void start() throws Exception {
        if (running) {
            throw new Exception("zookeeper server is started...");
        }
        running = true;
        init();
    }


    public void stop() throws Exception {
        if (!running) {
            throw new RuntimeException("zookeeper server is stoped..");
        }
        running = false;
        release();
    }


    private void init() {
        zkClient = new ZkClient(url, sessionTimeout, connectionTimeout,
                new BytesPushThroughSerializer());
        try {
            zkClient.createPersistent(HOME.concat(root).concat("/").
                    concat(parent), true);
        } catch (ZkNodeExistsException e) {
            e.printStackTrace();
        }
        cleanExecutors = Executors.newCachedThreadPool();

    }


    private void release() {
        if (zkClient != null) {
            zkClient.close();
            zkClient = null;
        }
        cleanExecutors.shutdown();
    }


    private void checkRunning() throws Exception {
        if (!running) {
            throw new Exception("zookeeper server is no start...");
        }
    }


    public String generator(RELEASE_NODE_SOURCE releaseNodeSource)
            throws Exception {
        return extract(generatorHandle(releaseNodeSource));
    }


    private String generatorHandle(RELEASE_NODE_SOURCE releaseNodeSource)
            throws Exception {
        checkRunning();
        final String fullNodePath = HOME.concat(root).concat("/").
                concat(parent).concat("/").
                concat(nodeName);
        final String nodePath = zkClient.
                createPersistentSequential(fullNodePath, null);
        if (releaseNodeSource.equals(RELEASE_NODE_SOURCE.DELAY)) {
            cleanExecutors.execute(new Runnable() {
                @Override
                public void run() {
                    zkClient.delete(nodePath);
                }
            });
        }
        if (releaseNodeSource.equals(RELEASE_NODE_SOURCE.FAST)) {
            zkClient.delete(nodePath);
        }
        return nodePath;
    }


    public String generatorPrefix(RELEASE_NODE_SOURCE releaseNodeSource)
            throws Exception {
        return extractPrefix(generatorHandle(releaseNodeSource));
    }


    private String extract(String nodePath) {
        int index = nodePath.lastIndexOf(nodeName);
        if (index >= 0) {
            index += nodeName.length();
            return index <= nodePath.length() ? nodePath.substring(index) : "";
        }
        return nodePath;
    }


    private String extractPrefix(String nodePath) {
        int index = nodePath.lastIndexOf("/");
        if (index >= 0) {
            return index <= nodePath.length() ? nodePath.substring(++index) : "";
        }
        return nodePath;
    }


    public enum RELEASE_NODE_SOURCE {
        NONE, DELAY, FAST
    }
}
