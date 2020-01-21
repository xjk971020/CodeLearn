package com.cathetine.waitNotify;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:cathetine
 * @Date:19-9-1
 * @Time:下午5:36 线程命名工厂
 */
public class MyThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public MyThreadFactory(String type) {
        if (type == null) {
            throw new NullPointerException("type might not be null");
        }
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        if (type.equals("生产者")) {
            namePrefix = "线程池-" +
                    poolNumber.getAndIncrement() +
                    "-生产者-";
        } else if (type.equals("消费者")) {
            namePrefix = "线程池-" +
                    poolNumber.getAndIncrement() +
                    "-消费者-";
        } else {
            namePrefix = "";
        }
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
