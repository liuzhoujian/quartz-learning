package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

//三个重要组件：
// Job JobDetail
// Trigger simpleTrigger cronTrigger
// scheduler
// jobDataMap
// JobExecutionContext
public class Main {
    public static void main(String[] args) throws SchedulerException {
        //打印当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = simpleDateFormat.format(date);
        System.out.println("当前时间为：" + formatDate);

        //创建一个JobDetail实例，将该实例与HelloJob.class绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJobDetail", "gby")
                //使用usingJobData向jobDataMap可以设置值，然后在JobExecutionContext中获取参数
                .usingJobData("username", "张三")
                .usingJobData("age", 18)
                .build();

        //获取设置的name和group
        System.out.println("--------------jobDetail-----------------------");
        System.out.println("jobDetail的name：" + jobDetail.getKey().getName());
        System.out.println("jobDetail的group：" + jobDetail.getKey().getGroup());
        System.out.println("jobDetail绑定的class:" + jobDetail.getJobClass().getName());
        System.out.println("----------------jobDetail---------------------");

        //1、定义一个trigger实例，从现在开始，每隔两秒执行一次任务，直到永远
        /*Trigger trigger = TriggerBuilder.newTrigger() //默认就是SimpleTrigger
                .withIdentity("myTrigger", "lzj")
                .usingJobData("housePrice", 123000.5445)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();*/

        //2、定义一个trigger实例，从现在开始，每个两秒执行一次任务，执行2次
        /*Trigger trigger = TriggerBuilder.newTrigger() //默认就是SimpleTrigger
                .withIdentity("myTrigger", "lzj")
                .usingJobData("housePrice", 123000.5445)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(2))
                .build();*/

        //3、定义一个trigger实例，在当前时间的3秒后开始执行任务，每个2秒执行一次，在当前时间的6秒后结束任务。
        //当前时间的3s后
        date.setTime(date.getTime() + 3000L);
        System.out.println("开始时间：" + simpleDateFormat.format(date));
        Date endTime = new Date();
        endTime.setTime(date.getTime() + 6000L);
        System.out.println("结束时间：" + simpleDateFormat.format(endTime));
        Trigger trigger = TriggerBuilder.newTrigger() //默认就是SimpleTrigger
                .withIdentity("myTrigger", "lzj")
                .usingJobData("housePrice", 123000.5445)
                .startAt(date)
                .endAt(endTime) //endAt的优先级要高于repeatForever()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();

        System.out.println("--------------trigger-----------------------");
        System.out.println("trigger的name:" + trigger.getKey().getName());
        System.out.println("trigger的group:" + trigger.getKey().getGroup());
        System.out.println("---------------trigger----------------------");

        //创建scheduler实例
        SchedulerFactory  schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
