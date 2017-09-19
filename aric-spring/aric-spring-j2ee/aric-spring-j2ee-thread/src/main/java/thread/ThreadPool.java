package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池使用
 */
public class ThreadPool {

    /**
     *  newSingleThreadExecutor  单一线程池，线程死亡重新生成新线程
     *  FixedThreadPool   固定线程池
     *  CacheThreadPool   缓存线程池
     *  ScheduledThreadPool 定时任务线程池
     */

}

class  FixedThreadPool{
    public static void main(String[] args) {
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(15);
        //定义10个任务
        for (int n = 1; n <= 10; n++) {
            final int task = n;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //每个任务执行10次
                    for (int i = 1; i <= 10; i++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.
                                currentThread().getName()
                                + "线程执行第：" + i + "次，处理第：" + task + "个任务");
                    }
                }
            });
        }
        System.err.println("执行完毕！");
        threadPool.shutdown();
    }
}


class  CacheThreadPool{
    public static void main(String[] args) {
        //创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //定义100个任务
        for (int n = 1; n <= 1000; n++) {
            final int task = n;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //每个任务执行10次
                    for (int i = 1; i <= 10; i++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.
                                currentThread().getName()
                                + "线程执行第：" + i + "次，处理第：" + task + "个任务");

                    }
                }
            });
        }
        System.err.println("执行完毕！");
        threadPool.shutdown();
    }
}


class SingleThreadPool{
    public static void main(String[] args) {
        //创建线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //定义10个任务
        for (int n = 1; n <= 10; n++) {
            final int task = n;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //每个任务执行10次
                    for (int i = 1; i <= 10; i++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.
                                currentThread().getName()
                                + "线程执行第：" + i + "次，处理第：" + task + "个任务");

                    }
                }
            });
        }
        System.err.println("执行完毕！");
        threadPool.shutdown();
    }
}

class ScheduledThreadPool{

    static long start,end;

    public static void main(String[] args) {
         start=System.currentTimeMillis();
         ScheduledExecutorService threadPool=Executors.newScheduledThreadPool(2);
         for (int i=1;i<=5;i++){
             int num=i;
             threadPool.scheduleAtFixedRate(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         Thread.sleep(50);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     end=System.currentTimeMillis();
                     System.err.println(Thread.currentThread().getName() + "执行任务:"+num+" 间隔时间："+(end-start)/1000+"秒");
                 }

             }, 2, 5, TimeUnit.SECONDS);
             System.out.println("**");

         }

    }
}