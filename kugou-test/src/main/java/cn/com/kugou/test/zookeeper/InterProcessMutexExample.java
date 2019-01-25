package cn.com.kugou.test.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InterProcessMutexExample {
    private static final String PATH = "/zk";
    public static void main(String[] args) throws Exception
    {
        //共享资源，一次只能访问一个外部资源
        final FakeLimitedResource resource = new FakeLimitedResource();
        //有10个线程的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);

        //10个线程，模拟10个客户端
        for(int i=0;i<10;++i){
            final int index = i;
            Callable<Void> task = new Callable<Void>()
            {

                public Void call() throws Exception
                {
                    //2.通过工厂建立连接
                    CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                            .sessionTimeoutMs(5000)
                            .retryPolicy(retryPolicy)
                            .build();
                    try
                    {
                        client.start();
                        ExampleClientThatLocks example = new ExampleClientThatLocks(client, PATH, resource,"client" + index);
                        //每个客户端重复5次任务
                        for(int j=0;j<5;++j){
                            example.doWork(10, TimeUnit.SECONDS);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }finally{
                        CloseableUtils.closeQuietly(client);//关闭该客户端
                    }

                    return null;
                }
            };
            service.submit(task);
        }
        service.shutdown();

    }
}
