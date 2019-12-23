package com.example.demo.mq.activemq;

//import javax.jms.Topic;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {
/*    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    //把队列注入进来 
    @Autowired  //此注解默认是以类型找  在配置文件中 已经注入的  @Bean 
    private Topic topic;*/
    
    //每隔5s时间向队列发送消息
/*    @Scheduled(fixedDelay=5000*4)
    public void send() {
        String msgString = System.currentTimeMillis()+" ";
        jmsMessagingTemplate.convertAndSend(topic,msgString);
        System.out.println("发布订阅通讯，msg"+msgString);
    }*/
}