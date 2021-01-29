package com.lzw.config;

import com.lzw.task.TestJob;
import com.lzw.task.TestJob2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
* @author lijie
 * 基于spring支持的quartz的另一种写法
*  https://www.freesion.com/article/4363452744/
**/
@Configuration
public class ScheduleConfig {

    @Bean
    public StdSchedulerFactory stdSchedulerFactory(){
//        test();
//        System.out.println("-------------------");
        StdSchedulerFactory factory = new StdSchedulerFactory();

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


            TestJob testJob = new TestJob();
            TestJob2 testJob2 = new TestJob2();
            JobDetail jobDetail = JobBuilder.newJob(testJob.getClass()).build();
            System.out.println("是否允许并发：" + jobDetail.isConcurrentExectionDisallowed());
//            MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
            JobDetail jobDetail1 = JobBuilder.newJob(testJob2.getClass()).build();

            SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                    .build();

            SimpleTrigger simpleTrigger1 = TriggerBuilder.newTrigger().startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                    .build();

            scheduler.scheduleJob(jobDetail, simpleTrigger);
            scheduler.scheduleJob(jobDetail1, simpleTrigger1);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public void test1(){
        System.out.println("我被注册成bean了");
    }

    @PostConstruct
    public String postConstructTest(){
        System.out.println("postContructTest is running");
        return "ll";
    }

    @PreDestroy
    public String preConstructTest(){
        System.out.println("PreDestroy is running");
        return "ll";
    }

    @Autowired
    public ScheduleConfig(){}


    @Bean
    public void test(){
        System.out.println("我被注册成bean了");
    }
}
