package cn.com.kugou.job.scheduled;

import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/14
 */
@Component
public class ScheduleJob {

    public final static long SECOND = 1 * 1000;
    FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

//    @Scheduled(fixedDelay = SECOND * 2)
    public void fixedDelayJob() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("[FixedDelayJob Execute]"+fdf.format(new Date()));
    }

//    @Scheduled(fixedRate = SECOND * 4)
    public void fixedRateJob() {
        System.out.println("[FixedRateJob Execute]"+fdf.format(new Date()));
    }

//    @Scheduled(cron = "0/4 * * * * ?")
    public void cronJob() {
        System.out.println("[CronJob Execute]"+fdf.format(new Date()));
    }






}
