package com.lzw.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestJob implements Job {
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
        System.out.println("TestJob executed onece" + new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(new Date()));

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        Condition condition = lock.newCondition();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        condition.signal();

        Task task = new Task();
        FutureTask futureTask = new FutureTask(task);
        futureTask.run();
        try {
            futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.DAYS, new LinkedBlockingDeque<>());
        threadPoolExecutor.execute(()->{

                }
        );
        Future future = threadPoolExecutor.submit(task);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    private class Task implements Callable{

        @Override
        public Object call() throws Exception {
            return 1;
        }
    }

}
