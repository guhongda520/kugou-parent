package cn.com.kugou.test.zookeeper;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 这个类负责请求锁，使用资源，释放锁这个完整的访问流程
 * */
public class ExampleClientThatLocks{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InterProcessMutex lock;//全局可重入锁
    private final FakeLimitedResource resource;//共享资源
    private final String clientName;
    public ExampleClientThatLocks(CuratorFramework client,String lockPath, FakeLimitedResource resource, String clientName)
    {
        this.lock = new InterProcessMutex(client, lockPath);
        this.resource = resource;
        this.clientName = clientName;
    }
    public void doWork(long time,TimeUnit unit) throws Exception{
        //尝试获取锁，如果无法获取锁，抛出异常
        if(!lock.acquire(time, unit)){
            System.out.println(clientName + "不能获取锁");
            return;
        }
        System.out.println(clientName +"获得了锁");
        if(!lock.acquire(time, unit)){
            System.out.println(clientName + "不能再次获取锁");
            return;
        }
        System.out.println(clientName +"再次获得了锁(重入功能)");
        try{
            resource.use();
        }finally{
            System.out.println(clientName + "释放了锁");
            lock.release();
            lock.release();
        }

    }
}
