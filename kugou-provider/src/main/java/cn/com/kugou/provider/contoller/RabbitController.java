package cn.com.kugou.provider.contoller;

import cn.com.kugou.comsumer.Receiver.RabbitMqReceiver;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/13
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private RabbitMqReceiver rabbitMqReceiver;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping("/rabbitTest")
    public void rabbitTest(){

        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
