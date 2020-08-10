import ch.qos.logback.core.util.TimeUtil;
import common.JobInfo;
import org.omg.SendingContext.RunTime;

import java.util.concurrent.*;

/**
 * 类说明：框架的主题类，也是调用者主要使用的类
 */
public class PendingJobPool {
    //框架运行时的线程数， 与机器的CPU数相同
    private static final int THREAD_COUNTS = Runtime.getRuntime().availableProcessors();

    //队列，线程池使用，用以存放待处理的任务
    private static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(5000);

    //线程池，固定大小，有界队列
    private static ExecutorService taskExecutor
            = new ThreadPoolExecutor(THREAD_COUNTS, THREAD_COUNTS, 60, TimeUnit.SECONDS, taskQueue);

    //工作信息的存放容器
    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap = new ConcurrentHashMap<>();

    //检查过期工作的处理器
    private static CheckJobProcessor checkJob = CheckJobProcessor.getInstance();

    //以单例模式启动
    private PendingJobPool(){}

    private static class JobPoolHolder{
        public static PendingJobPool pool = new PendingJobPool();
    }

    public static PendingJobPool getInstance(){
        return JobPoolHolder.pool;
    }

    public static int getThreadCounts() {
        return THREAD_COUNTS;
    }

    public static BlockingQueue<Runnable> getTaskQueue() {
        return taskQueue;
    }

    public static ExecutorService getTaskExecutor() {
        return taskExecutor;
    }

    public static ConcurrentHashMap<String, JobInfo<?>> getJobInfoMap() {
        return jobInfoMap;
    }

    public static CheckJobProcessor getCheckJob() {
        return checkJob;
    }
}
