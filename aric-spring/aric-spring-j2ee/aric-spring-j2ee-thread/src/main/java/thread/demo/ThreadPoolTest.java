package thread.demo;

//import org.junit.Test;

/**
 * Created by tom.lee on 2015/12/16.
 */
public class ThreadPoolTest  {

    class TargetRunnable implements Runnable {

        private String command;

        public TargetRunnable(String s){
            this.command=s;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
            processCommand();
            System.out.println(Thread.currentThread().getName()+" End.");
        }

        private void processCommand() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString(){
            return this.command;
        }
    }

//    @Test
    public void testThreadPool() {
        ThreadPool tp = ThreadPool.newThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new TargetRunnable("test " + i);
            tp.addTarget(worker);
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Runnable worker1 = new TargetRunnable("test final");
        tp.addTarget(worker1);

        tp.shutdown();
    }

}
