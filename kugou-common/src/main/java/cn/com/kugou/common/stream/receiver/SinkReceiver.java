package cn.com.kugou.common.stream.receiver;

import cn.com.kugou.common.KugouConstance;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/10/16
 */
public interface SinkReceiver {

    @Input(KugouConstance.OUTPUT)
    MessageChannel intPut();


    @Input(KugouConstance.OUTPUT_1)
    MessageChannel intPut1();

}
