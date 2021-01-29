package com.lzw.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.google.common.collect.ComparisonChain.start;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Object tv1 = context.getTrigger().getJobDataMap().get("t1");
        Object tv2 = context.getTrigger().getJobDataMap().get("t2");
        Object jv1 = context.getJobDetail().getJobDataMap().get("j1");
        Object jv2 = context.getJobDetail().getJobDataMap().get("j2");
        Object sv = null;
        try {
            sv = context.getScheduler().getContext().get("skey");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.println(tv1 + ": " + tv2);
        System.out.println(jv1 + ": " + jv2);
        System.out.println(sv);
    }

    @PostConstruct
    @Bean
    public void testBean(){
        System.out.println("testBean is run");
    }

    public static void main(String[] args) {

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.getContext().put("skey", "value");

            Calendar calendar = Calendar.getInstance();
            calendar.set(2020, Calendar.OCTOBER, 22, 10, 0, 0);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            Date date = simpleDateFormat.parse("2020-9-22 10:48:00");

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                    .usingJobData("t1", "tv1")
                    .startAt(date)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInHours(10)
                    .repeatForever()).build();

            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                    .usingJobData("j1", "jv1")
                    .withIdentity("myjob", "mygroup").build();
            jobDetail.getJobDataMap().put("j2", "jv2");
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException | ParseException e) {
            e.printStackTrace();

        }

    }
}
