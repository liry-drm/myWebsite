package com.example.demo.config.activemq;

//import javax.jms.Queue;
//import javax.jms.Topic;

//import org.apache.activemq.command.ActiveMQQueue;
//import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigQueueAndTopic {
	@Value("${queue}")
	private String queue;
	@Value("${topic}")
	private String topic;

	// 首先将队列注入到SpringBoot容器中去
/*	@Bean
	public Queue queue() {
		return new ActiveMQQueue(queue);
	}*/

/*	@Bean
	public Topic topicqueue() {
		return new ActiveMQTopic(topic);
	}*/

}