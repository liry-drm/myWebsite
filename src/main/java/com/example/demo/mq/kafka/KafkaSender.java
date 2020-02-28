package com.example.demo.mq.kafka;

//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
    
//    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private Gson gson = new GsonBuilder().create();
//
//    //发送消息方法
//    public void send() {
//        Message message = new Message();
//        message.setId(System.currentTimeMillis());
//        message.setMsg(UUID.randomUUID().toString());
//        message.setSendTime(new Date());
//        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
//        kafkaTemplate.send("zhisheng", gson.toJson(message));
//    }
}