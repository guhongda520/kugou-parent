package cn.com.kugou.test.zookeeper;


import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 模拟共享资源，这个资源期望只能单线程访问，否则会有并发问题（走到抛异常的逻辑）
 * <br>
 */
public class FakeLimitedResource {
    private final AtomicBoolean inUse = new AtomicBoolean(false);

    public void use() throws InterruptedException {
        /**
         * inUse.compareAndSet(false, true)表示：先检测当前值是否为false,如果为false,就更新为true
         * 如果多个线程执行这个方法，在无锁的情况下，由于下面睡了一段时间，所以有些线程会走到抛异常的地方
         */
        if (!inUse.compareAndSet(false, true)) {
            throw new IllegalStateException("Needs to be used by one client at a time");
        }
        try {
            Thread.sleep(5000);
        } finally {
            inUse.set(false);
        }
    }
    public void read(){
        System.out.println(inUse.get());
    }
}
