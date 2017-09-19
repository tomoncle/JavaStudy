package com.aric.zk;

import com.aric.common.utils.PrinterUtils;
import com.aric.zk.BaseTest;
import com.aric.zkapi.config.ZKBase64AuthUtils;
import com.aric.zkapi.service.ZKSyncService;
import com.aric.zkapi.watcher.ZKWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ZKSyncServiceTest extends BaseTest {

    @Autowired
    ZKSyncService zkSyncService;

    @Test
    public void testCreateNode() throws Exception {
        String path= zkSyncService.createNode("/node_4",
                "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        PrinterUtils.printELog(path + "" + ZKWatcher.getWatchedEvent());
    }

    @Test
    public void testCreateNode1() throws Exception {
         ACL aclIp = new ACL(ZooDefs.Perms.READ,new Id("ip","192.168.1.105"));
         ACL aclDigest = new ACL(ZooDefs.Perms.READ| ZooDefs.Perms.WRITE,new Id("digest", ZKBase64AuthUtils.getDigest("aric","123456")));
         ArrayList<ACL> acls = new ArrayList<ACL>();
         acls.add(aclIp);acls.add(aclDigest);

         String path= zkSyncService.createNode("/node_4",
                "123".getBytes(),acls, CreateMode.PERSISTENT);
         PrinterUtils.printELog(path + "" + ZKWatcher.getWatchedEvent());
    }

    @Test
    public void testGetChildrenNode() throws Exception {
            List<String> nodes =zkSyncService.getChildrenNode("/");
            PrinterUtils.printELog(nodes);
            PrinterUtils.printELog( ZKWatcher.getWatchedEvent());
    }

    @Test
    public void testGetChildrenNode1() throws Exception {
        ACL aclDigest = new ACL(ZooDefs.Perms.READ| ZooDefs.Perms.WRITE,new Id("digest", ZKBase64AuthUtils.getDigest("dev","dev")));
        ArrayList<ACL> acls = new ArrayList<ACL>();
        List<String> nodes =zkSyncService.getChildrenNode("/schedule",true);
        PrinterUtils.printELog(nodes);
        PrinterUtils.printELog( ZKWatcher.getWatchedEvent());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testDeleteNode() throws Exception {
       boolean b= zkSyncService.deleteNode("/node_4");
        PrinterUtils.printILog(b);
    }

    @Test
    public void  getNodeData() throws Exception {
      byte [] bytes=  zkSyncService.getNodeData("/schedule/dev", false, new Stat(),"dev","dev1");
        PrinterUtils.printILog(new String(bytes));
    }


    @Test
    public void  existsNode() throws Exception {
       zkSyncService.existsNode("/node_2", false);
    }

}