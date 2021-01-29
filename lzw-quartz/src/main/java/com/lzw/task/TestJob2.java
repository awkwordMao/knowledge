package com.lzw.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJob2 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        int j = 0;
        for (int i = 0; i < 1000; i++) {
            j++;
        }
        System.out.println("TestJob2 is running" + new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(new Date()));
    }
}
