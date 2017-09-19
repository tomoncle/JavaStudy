package thread.demo;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tom.lee on 2015/12/16.
 * 固定大小的线程池，用于应用部署调用。
 */
public class ThreadPool {
    //递归锁
    private ReentrantLock lock;
    //条件变量
    private Condition cvAvailable,cvEmpty;
    //存储队列
    private LinkedList<Runnable> queue;
    //队列的大小
    private int nObjects = 0;
    //固定大小的线程数组
    private ThreadPoolThread[] threads;
    //是否线程池终止
    private boolean terminated = false;

    private ThreadPool(int size) {
        this.lock = new ReentrantLock();
        this.cvAvailable = this.lock.newCondition();
        this.cvEmpty = this.lock.newCondition();
        this.queue = new LinkedList<Runnable>();
        this.threads = new ThreadPoolThread[size];
        for (int i = 0; i < size; i++) {
            this.threads[i] = new ThreadPoolThread(this,i);
            this.threads[i].start();
        }
    }

    /**
     * 创建一个新的线程池
     * @param size 线程池中线程大小
     * @return
     */
    public static ThreadPool newThreadPool(int size) {
        return new ThreadPool(size);
    }

    /**
     * 添加一个要被执行的目标
     * @param target 目标
     */
    public void addTarget(Runnable target) {
        try {
            this.lock.lock();
            if (this.terminated) {
                System.out.println("ThreadPool is terminated.");
                return;
            }
            //向队列添加目标
            this.queue.add(target);
            ++this.nObjects;
            //唤醒等待线程
            this.cvAvailable.signal();
        }
        finally {
            this.lock.unlock();
        }
    }

    /**
     * 停止线程池
     * 停止后线程池不能再被使用必须重新创建新的线程池
     */
    public void shutdown() {
        try {
            this.lock.lock();
            //等待所有线程完成工作
            while (this.nObjects != 0) {
                try {
                    this.cvEmpty.await();
                }catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
            //停止所有的线程
            for (int i = 0; i < this.threads.length; i++) {
                this.threads[i].shouldRun = false;
            }
            this.cvAvailable.signalAll();
            this.terminated = true;
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 获取当前队列大小
     * @return
     */
    public int getQueueSize() {
        return nObjects;
    }

    /**
     * 获取线程池中线程大小
     * @return
     */
    public int getThreadSize() {
        return threads.length;
    }

    /**
     * 线程池中的线程
     */
    class ThreadPoolThread extends Thread {
        ThreadPool parent;
        boolean shouldRun = true;

        ThreadPoolThread(ThreadPool parent, int i) {
            super("ThreadPoolThread" + i);
            this.parent = parent;
        }

        public void run() {
            Runnable obj = null;
            while (shouldRun) {
                try {
                    parent.lock.lock();
                    //1.获取数据
                    while (obj == null && shouldRun) {
                        try {
                            obj = parent.queue.remove();
                        }
                        catch (Exception e) {
                            obj = null;
                        }
                        //如果没有数据就等待，直到被终止或有数据
                        if (obj == null) {
                            try {
                                parent.cvAvailable.await();
                            } catch (InterruptedException e) {
                                return;
                            }
                        }
                    }
                }
                finally {
                    parent.lock.unlock();
                }
                //2.判断是否被终止
                if (!shouldRun) return;
                //3.执行目标
                obj.run();
                //4.当队列为空时，通知等待要结束的线程
                try {
                    parent.lock.lock();
                    --parent.nObjects;
                    if (parent.nObjects == 0) {
                        parent.cvEmpty.signal();
                    }
                }
                finally {
                    parent.lock.unlock();
                }
                //5.清除工作
                obj = null;
            } //end while
        } //end run

    }

}
