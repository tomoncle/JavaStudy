package com.aric.zkclient.api.master;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * master 选举
 * Created by Administrator on 2016/9/19.
 */
public class WorkServer {
    //服务器状态
    private volatile boolean running = false;
    //zk 客户端
    private ZkClient zkClient;
    //master 节点信息
    private static final String MASTER_PATH = "/master";
    //master 节点监听
    private IZkDataListener dataListener;
    //服务器信息
    private RunningData serverData;
    //master 信息
    private RunningData masterData;
    //调度器
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    //争抢延时
    private static final int DELAY_TIME = 5;

    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    public WorkServer(RunningData runningData) {
        this.serverData = runningData;
        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
//                takeMaster();
                if (masterData.getName() != null &&
                        masterData.getName().equals(serverData.getName())) {
                    takeMaster();
                } else {
                    executorService.schedule(new Runnable() {
                        @Override
                        public void run() {
                            takeMaster();
                        }
                    }, DELAY_TIME, TimeUnit.SECONDS);
                }
            }
        };
    }

    public void start() throws Exception {
        if (running) {
            throw new Exception("服务已经启动");
        } else {
            running = true;
            //订阅master节点删除事件
            zkClient.subscribeDataChanges(MASTER_PATH, dataListener);
            takeMaster();
        }

    }

    public void stop() throws Exception {
        if (!running) {
            throw new Exception("服务已经关闭");
        } else {
            running = false;
            executorService.shutdown();
            //取消master节点删除事件订阅
            zkClient.unsubscribeDataChanges(MASTER_PATH, dataListener);
            releaseMaster();
        }

    }

    private boolean checkMaster() {
        try {
            RunningData eventData = zkClient.readData(MASTER_PATH);
            System.out.println(eventData);
            masterData = eventData;
            if (masterData.getName().equals(serverData.getName())) {
                return true;
            }
            return false;
        } catch (ZkNoNodeException e) {
            return false;
        } catch (ZkInterruptedException e) {
            return checkMaster();
        } catch (ZkException e) {
            return false;
        }

    }

    private void releaseMaster() {
        if (checkMaster()) {
            zkClient.delete(MASTER_PATH);
        }
    }


    private void takeMaster() {
        if (running) {
            try {
                //创建master 临时节点
                zkClient.create(MASTER_PATH, serverData, CreateMode.EPHEMERAL);
                masterData = serverData;
//                //模拟测试begin
                System.out.println(serverData.getName() + " is master");
//                executorService.schedule(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (checkMaster()) {
//                            releaseMaster();
//                        }
//                    }
//                }, DELAY_TIME, TimeUnit.SECONDS);
//                //end
            } catch (ZkNodeExistsException e) {
                RunningData runningData = zkClient.readData(MASTER_PATH, true);
                if (runningData == null) {
                    takeMaster();
                } else {
                    masterData = runningData;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
