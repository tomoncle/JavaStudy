package simple;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by liyuanjun on 16-11-17.
 */
public class QueueConsumer {

    //mq jms服务地址
    private static final String ACTIVEMQ_SERVER = "tcp://0.0.0.0:61616";
    //mq 同步超时时间 ms
    private static final Integer SYNC_TIMEOUT = 100000;


    public static void main(String[] args) {
        asyncConsumer();
//        syncConsumer();
    }

    /**
     * 同步消费
     */
    public static void syncConsumer() {
        //创建一连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_SERVER);
        try {
            //创建一个连接
            Connection connection = connectionFactory.createConnection();
            //打开连接
            connection.start();
            //创建一个回话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个目的地Destination
            Queue queue = session.createQueue("mytestqueue");
            //创建一个消费者
            MessageConsumer consumer = session.createConsumer(queue);
            while (true) {
                //设置接收者接收消息的时间，为了便于测试，这里定为100s
                Message message = consumer.receive(SYNC_TIMEOUT);
                if (message != null) {
                    System.out.println(message);
                } else {
                    //超时结束
                    break;
                }

            }
            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 异步消费
     */
    public static void asyncConsumer() {
        //创建一连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_SERVER);
        try {
            //创建一个连接
            Connection connection = connectionFactory.createConnection();
            //打开连接
            connection.start();
            //创建一个回话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个目的地Destination
            Queue queue = session.createQueue("mytestqueue");
            //创建一个消费者
            MessageConsumer consumer = session.createConsumer(queue);
            //接收消息
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message instanceof TextMessage){
                        String msg="";
                        try {
                           msg=((TextMessage) message).getText();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        System.out.printf("msg:"+msg);
                    }
                }
            });
            System.out.println("敲回车键退出！\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
            //关闭
            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
