package cn.com.kugou.job.config;

import cn.com.kugou.job.quartz.TimerOne;
import cn.com.kugou.job.quartz.TimerTwo;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/14
 */
public class TimeInit {

    /**
     * 注入任务调度器
     */
//    @Autowired
    private Scheduler scheduler;

    public void init() throws SchedulerException {
        bulidCreateTimeOne();
        bulidCreateTimeTow();
    }

    private void bulidCreateTimeOne() throws SchedulerException {

        //设置开始时间为1分钟后
        long startAtTime = System.currentTimeMillis() + 1000 * 60;
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = TimerOne.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(TimerOne.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).startAt(new Date(startAtTime)).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private void bulidCreateTimeTow()throws SchedulerException {

        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = TimerTwo.class.getName();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(TimerTwo.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
