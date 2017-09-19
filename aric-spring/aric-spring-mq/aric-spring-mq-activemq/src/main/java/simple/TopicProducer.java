package simple;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

/**
 * Created by liyuanjun on 16-11-17.
 */
public class TopicProducer {
    //mq jms服务地址
    private static final String ACTIVEMQ_SERVER="tcp://0.0.0.0:61616";

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            producer();
        }
    }


    public static void producer(){
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_SERVER);
        try {
            //创建连接
            Connection connection = connectionFactory.createConnection();
            //开启连接
            connection.start();
            //创建一个回话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个Destination，queue或者Topic
            Topic topic = session.createTopic("mytopic");
            //创建一个生成者
            MessageProducer producer = session.createProducer(topic);
            //创建一个消息
            TextMessage textMessage = new ActiveMQTextMessage();
            textMessage.setText("hello my topic");
            //发送消息
            producer.send(textMessage);
            //关闭
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
