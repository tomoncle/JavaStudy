package simple;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by liyuanjun on 16-11-17.
 */
public class TopicConsumer {

    //mq jms服务地址
    private static final String ACTIVEMQ_SERVER = "tcp://0.0.0.0:61616";

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_SERVER);
        try {
            //创建连接
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //创建一个会话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个目标
            Destination destination = session.createTopic("mytopic");
            //创建一个消费者
            MessageConsumer consumer = session.createConsumer(destination);
            //接收消息
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message instanceof TextMessage){
                        String msg="";
                        try {
                            msg=((TextMessage) message).getText();
                        }catch (JMSException e){
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
