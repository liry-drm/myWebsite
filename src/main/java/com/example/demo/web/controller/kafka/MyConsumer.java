package com.example.demo.web.controller.kafka;
import org.springframework.stereotype.Component;

/**
 * @review:
 * @date: 2018/9/14
 */
@Component
public class MyConsumer {
/*
    @KafkaListener(topics = "mytopic")
    public void listen(ConsumerRecord<?,String> record) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
    }*/
}
