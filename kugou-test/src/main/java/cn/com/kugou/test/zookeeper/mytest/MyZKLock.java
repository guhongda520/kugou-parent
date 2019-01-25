package cn.com.kugou.test.zookeeper.mytest;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

public class MyZKLock {

    private InterProcessMutex lock;

    public MyZKLock (CuratorFramework client, String lockPath){
        this.lock = new InterProcessMutex(client, lockPath);
    }

    public void getLock(Integer time, TimeUnit timeUnit) throws Exception {
        lock.acquire(time, timeUnit);
    }

    public void unLock() throws Exception {
        lock.release();
    }

}
