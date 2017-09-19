package com.aric.zkapi.watcher;

import com.aric.common.utils.PrinterUtils;
import org.apache.zookeeper.*;

/**
 * zk事件监听
 */
public class ZKWatcher implements Watcher {

    private static WatchedEvent watchedEvent;

    public static WatchedEvent getWatchedEvent() {
        return watchedEvent;
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        PrinterUtils.printILog("ZKWatcher:"+watchedEvent);
        this.watchedEvent=watchedEvent;
        if(watchedEvent.getState()== Event.KeeperState.SyncConnected){
            if (watchedEvent.getType()== Event.EventType.None
                    && null==watchedEvent.getPath()){
                doSomething();
            }

        }
    }

    public void doSomething(){
        PrinterUtils.printILog("do something");
    }



}