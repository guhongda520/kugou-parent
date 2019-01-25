package cn.com.kugou.test.zookeeper.mytest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyZKTest {
    public static final String PATH = "/zk";
    private static final String ZK_ADDRESS = "127.0.0.1:2181";
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);

        for (int i = 0; i < 10; i++) {
            final int j = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    CuratorFramework client = CuratorFrameworkFactory.newClient(
                            ZK_ADDRESS,
                            new RetryNTimes(10, 5000)
                    );

//                    CuratorFramework client = CuratorFrameworkFactory.builder().connectString(ZK_ADDRESS).sessionTimeoutMs(5000).retryPolicy(new RetryUntilElapsed(100000,10000)).build();
                    client.start();
                    MyZKLock zkLock = new MyZKLock(client, PATH);
                    try {
                        zkLock.getLock(3, TimeUnit.SECONDS);
                        System.out.println("线程：" + j +"获取到锁了");
                        zkLock.getLock(3, TimeUnit.SECONDS);
                        System.out.println("线程：" + j +"获取到重入锁了");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            zkLock.unLock();
                        } catch (Exception e) {
                            System.out.println("You do not own the lock");
                        }
                        try {
                            zkLock.unLock();
                        } catch (Exception e) {
                            System.out.println("You do not own the lock");
                        }
                    }
                }
            });
        }
        service.shutdown();
    }
}
