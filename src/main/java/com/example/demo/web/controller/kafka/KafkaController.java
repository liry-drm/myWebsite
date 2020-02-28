package com.example.demo.web.controller.kafka;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.web.controller.BaseController;

@RestController
public class KafkaController extends BaseController{

/*    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public String send(String name) {
        kafkaTemplate.send("mytopic", name);
        return name;
    }*/
	/**
	 * kafka发送测试
	 */
//    KafkaSender sender = context.getBean(KafkaSender.class);
//    for (int i = 0; i < 3; i++) {
//        //调用消息发送类中的消息发送方法
//        sender.send();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
