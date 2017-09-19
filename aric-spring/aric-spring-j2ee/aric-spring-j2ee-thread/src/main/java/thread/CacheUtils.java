package thread;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by tom.lee on 2016/3/24.
 */
public class CacheUtils {

    /**
     * 缓存
     */
    private static Map<String, Object> cacheMap = new HashMap<String, Object>();

    //创建锁实例
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 获取数据
     * @param key
     * @return
     */
    public static Object getData(String key) {
        readWriteLock.readLock().lock();
        Object value = null;
        try {
            value = cacheMap.get(key);
            if (value == null) {
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    if (value == null) {
                        value = findDB(key);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.err.println("获取者："+Thread.currentThread().getName()+"获取数据："+value);
                    }
                } finally {
                    readWriteLock.writeLock().unlock();
                }
                readWriteLock.readLock().lock();
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }

    /**
     * 模拟查询数据库
     * @param key
     * @return
     */
    private static Object findDB(String key) {

        return System.currentTimeMillis() + key;
    }


    public static void main(String[] args) {
      ExecutorService threadPool= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            final int num=i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {

                    for (int j=0;j<2;j++){
                        Object value= getData(""+num);

                        System.out.println(Thread.currentThread().getName()+"读取数据："+value);
                    }
                }
            });
        }
    }

}
