package com.cathetine.waitNotify;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:cathetine
 * @Date:19-9-1
 * @Time:下午7:31
 * 生产者
 */
public class Producer implements Runnable{

    private boolean isRunning;
    private ArrayList<Integer> shareQueue;
    private AtomicInteger atomicInteger;
    private final int SIZE;

    public Producer(ArrayList<Integer> shareQueue, int size) {
        this.shareQueue = shareQueue;
        this.isRunning = true;
        this.atomicInteger = new AtomicInteger(0);
        this.SIZE = size;
    }

    @Override
    public void run() {
        try {
            int data;
            while (isRunning) {
                //模拟网络延迟
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
                //缓冲区满, 线程进入阻塞状态, 等待唤醒
                while (shareQueue.size() == SIZE) {
                    synchronized (shareQueue) {
                        System.out.println(Thread.currentThread().getName() + "进入等待状态 缓冲区满");
                        shareQueue.wait();
                    }
                }
                synchronized (shareQueue) {
                    data = atomicInteger.incrementAndGet();
                    shareQueue.add(data);
                    System.out.println(Thread.currentThread().getName() + " create data:" + data + ", size：" + shareQueue.size());
                    shareQueue.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
