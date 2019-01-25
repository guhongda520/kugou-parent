package cn.com.kugou.provider.contoller;

import cn.com.kugou.common.stream.sender.SinkSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
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
    private SinkSender sinkSender;

    @GetMapping("/rabbitTest")
    public void rabbitTest(){
        String context = "hello " + System.currentTimeMillis();
//        System.out.println("Sender : " + context);
        sinkSender.output().send(MessageBuilder.withPayload(context).build());
    }
}
