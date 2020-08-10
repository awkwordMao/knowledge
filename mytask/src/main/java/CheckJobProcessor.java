import common.JobInfo;

import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 * 类说明：任务完成后，在一定的时间供查询结果，之后为释放资源节约内存，需要定期处理过期的任务；
 *
 */
public class CheckJobProcessor {
    //存放任务的队列，延时队列DelayQueue
    private static DelayQueue<ItemVo<String>> queue = new DelayQueue<ItemVo<String>>();
    //单例化，框架中有一个处理过期任务的实例即可
    private static class ProcesserHolder{
        public static CheckJobProcessor processor = new CheckJobProcessor();
    }

    public static CheckJobProcessor getInstance(){
        return ProcesserHolder.processor;
    }

    //处理队列中到期任务的线程
    private static class FetchJob implements Runnable{

        private static DelayQueue<ItemVo<String>> queue = CheckJobProcessor.queue;
        //缓存的工作信息
        private static Map<String, JobInfo> jobInfoMap = PendingJobPool.getMap();

        @Override
        public void run() {
            while(true){
                try {
                    ItemVo<String> Item = queue.take();
                }catch (InterruptedException e){
                    
                }
            }
        }
    }
    //任务完成后，放入队列，经过expireTime时间后，会从整个框架中移除
}
