package common;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class JobInfo<R> {
    private final String jobName;

    private final int jobLentgh;

    private final ITaskProcessor<?, ?> taskProcessor;//处理工作中任务的处理器

    private final long expireTime;//保留的工作结果信息供查询的时长

    private LinkedBlockingDeque<TaskResult<R>> taskDetailQueues;//每个任务的处理结果（Deque双端队列）

    private AtomicInteger successCount;//任务成功的次数
    private AtomicInteger taskProcessCount;

    public JobInfo(String jobName, int jobLentgh, ITaskProcessor<?, ?> taskProcessor, long expireTime) {
        this.jobName = jobName;
        this.jobLentgh = jobLentgh;
        this.taskProcessor = taskProcessor;
        this.expireTime = expireTime;
        successCount = new AtomicInteger(0);
        taskProcessCount = new AtomicInteger(0);
        taskDetailQueues = new LinkedBlockingDeque<TaskResult<R>>(jobLentgh);
    }

    public int getSuccessCount() {
        return successCount.get();
    }

    public int getTaskProcessCount() {
        return taskProcessCount.get();
    }

    public int getFailCount(){
        return taskProcessCount.get() - successCount.get();
    }

    public ITaskProcessor<?, ?> getTaskProcessor() {
        return taskProcessor;
    }

    public int getJobLentgh() {
        return jobLentgh;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public LinkedBlockingDeque<TaskResult<R>> getTaskDetailQueues() {
        return taskDetailQueues;
    }

    //提供工作的整体进度信息
    public String getTotalProcess(){
        return "Success[" + successCount.get() + "]/Current[" + taskProcessCount.get() + "] Total[" +
                jobLentgh + "]";
    }

    //提供工作中每个任务的处理结果
    private List<TaskResult<R>> getTaskDetail(){
        List<TaskResult<R>> taskResultList = new LinkedList<>();
        TaskResult<R> taskREsult;
        while((taskREsult = taskDetailQueues.pollFirst()) != null){
            taskResultList.add(taskREsult);
        }
        return taskResultList;
    }

    //每个任务处理完成后，记录任务的处理结果，因为从业务应用的角度来说，
    //对查询任务进度数据的一致性不高
    //所以保证最终一致性即可，无需对整个方法加锁
    public void addTaskResult(TaskResult<R> taskResult){
        if(TaskResultType.Success.equals(taskResult.getResultType())){
            successCount.incrementAndGet();
        }
        taskProcessCount.incrementAndGet();
        taskDetailQueues.addLast(taskResult);
        if(taskProcessCount.get() == jobLentgh){
            //把工作的结果放到定时缓存，到期后清除

        }
    }
}
