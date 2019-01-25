package cn.com.kugou.common.stream.sender;

import cn.com.kugou.common.KugouConstance;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/10/16
 */
@Service
public interface SinkSender {

    @Output(KugouConstance.OUTPUT)
    MessageChannel output();


    @Output(KugouConstance.OUTPUT)
    MessageChannel output1();
}

