package thread;

/**
 * Created by tom.lee on 2016/3/15.
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        Product product=new Product();
        //生产者
        Producer producer=new Producer(product);
        Thread t1=new Thread(producer);
        t1.setName("生成者1：");
        Thread t2=new Thread(producer);
        t2.setName("生成者2：");

        //消费者
        Consumer consumer=new Consumer(product);
        Thread c=new Thread(consumer);
        c.setName("消费者1：");

        t1.start();
        t2.start();
        c.start();




    }
}

/**
 * 生产者线程
 */
class Producer implements Runnable{

    final  private Product product;

    public Producer(Product product) {
        this.product=product;
    }


    @Override
    public void run() {
        while (true){
            try {
                Thread.currentThread().sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            product.addProduct();
        }
    }
}

/**
 * 消费者线程
 */
class Consumer implements Runnable{

    final  private Product product;

    public Consumer(Product product) {
        this.product=product;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            product.useProduct();
        }
    }
}


/**
 * 产品对象
 */
class Product{

    //共享数据，产品数量
    private volatile int count;

    /**
     * 生成产品
     */
    public synchronized void addProduct()  {
        if(count>=20){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            count++;
            System.out.println(Thread.currentThread().getName()+"生产了第："+count+" 个商品！");
            notifyAll();
        }

    }

    /**
     * 消费产品
     */
    public synchronized void useProduct()  {

        if(count<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(Thread.currentThread().getName()+"消费了第："+count+" 个商品！");
            count--;
            notifyAll();
        }

    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}