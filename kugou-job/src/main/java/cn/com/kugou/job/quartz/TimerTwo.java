package cn.com.kugou.job.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/14
 */
public class TimerTwo extends QuartzJobBean{

    private Logger logger = LoggerFactory.getLogger(TimerTwo.class);
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("TimerTwo：：{}",new Date());

    }
}
