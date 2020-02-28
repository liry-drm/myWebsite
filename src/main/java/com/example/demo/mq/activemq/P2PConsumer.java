package com.example.demo.mq.activemq;

//import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class P2PConsumer {
   
/*    @JmsListener(destination= "${queue}")    //用这个注解去监听 监听的队列
    public void receiver(String msg) {
        System.out.println("消费者成功获取到生产者的消息，msg"+msg);
    }*/
}