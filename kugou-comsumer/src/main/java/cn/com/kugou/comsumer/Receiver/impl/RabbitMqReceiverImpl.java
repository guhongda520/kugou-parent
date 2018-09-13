package cn.com.kugou.comsumer.Receiver.impl;

import cn.com.kugou.comsumer.Receiver.RabbitMqReceiver;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 翟永超
 * @create 2016/9/25.
 * @blog http://blog.didispace.com
 */
@Service
@RabbitListener(queues = "hello")
public class RabbitMqReceiverImpl implements RabbitMqReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }

}
