package quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取参数有两种方式：
 *  1、直接从各种对应的map中获得
 *    直接使用getMergedJobDataMap获取融合的一个大map(需要保证jobDetail的键和Trigger的键的值不重复，即键唯一）
 *  2、通过属性赋值:Quartz框架默认的JobFactory实现类在初始化job实例对象时会自动调用这些setters方法
 */
public class HelloJob implements Job {
  /*  private String username;
    private Integer age;
    private Double housePrice;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(Double housePrice) {
        this.housePrice = housePrice;
    }*/

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //打印当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = simpleDateFormat.format(date);
        System.out.println("当前时间为：" + formatDate);

    /*  //从各自map取
        //从上下文jobExecutionContext中获取jobDetail
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        //从map中取得参数
        JobDataMap jobDetailDataMap = jobDetail.getJobDataMap();
        String username = (String) jobDetailDataMap.get("username");
        Integer age = (Integer)jobDetailDataMap.get("age");
        System.out.println("username:" + username + ", age:" + age);

        //从上下文jobExecutionContext中获取trigger
        Trigger trigger = jobExecutionContext.getTrigger();
        //从map中取得参数
        JobDataMap triggerJobDataMap = trigger.getJobDataMap();
        Double housePrice = (Double)triggerJobDataMap.get("housePrice");
        System.out.println("housePrice: " + housePrice);*/

        //从mergeMap中取
        /*JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        String username = (String)mergedJobDataMap.get("username");
        Integer age = (Integer)mergedJobDataMap.get("age");
        Double housePrice = (Double)mergedJobDataMap.get("housePrice");
        System.out.println("username:" + username + ", age:" + age + ", housePrice:" + housePrice);*/

        //通过属性赋值
       /* System.out.println("username:" + username + ", age:" + age + ", housePrice:" + housePrice);*/

        //执行业务逻辑
        System.out.println("Hello World");
    }
}
