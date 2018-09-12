package cn.com.kugou.common.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/12
 */
public class MyRejectedExecutionHandler implements RejectedExecutionHandler{


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

    }

}
