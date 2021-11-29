package org.xm06.pms.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xm06.pms.jobs.RestoreInformJob;
import org.xm06.pms.jobs.SystemRecordSaveJob;
import org.xm06.pms.vo.SystemRecord;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail1(){
        //定期存储发送的消息，和未读消息列表到数据库
        return JobBuilder.newJob(RestoreInformJob.class).storeDurably().build();
    }
    @Bean
    public JobDetail jobDetail2(){
        //记录系统信息
        return JobBuilder.newJob(SystemRecordSaveJob.class).storeDurably().build();
    }
    /*
     * 每3天执行一次
     * */
    @Bean
    public Trigger trigger1(){
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger1","xmo6-heleidage")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 1/3 * ?"))
                .forJob(jobDetail1())
                .build();
    }

    /**
     * 每天23 点执行一次
     * @return
     */
    @Bean
    public Trigger trigger2(){
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger2","xmo6-heleidage")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 23 * * ?"))
                .forJob(jobDetail2())
                .build();
    }

}

