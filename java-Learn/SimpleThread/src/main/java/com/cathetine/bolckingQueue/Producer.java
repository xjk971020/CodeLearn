package com.cathetine.bolckingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:cathetine
 * @Date:19-9-1
 * @Time:下午5:08 生产者
 */
public class Producer implements Runnable {

    private boolean isRunning;
    private BlockingQueue<Integer> blockingQueue;
    private AtomicInteger atomicInteger;

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        this.isRunning = true;
        this.atomicInteger = new AtomicInteger(1);
    }

    @Override
    public void run() {
        int data;
        try {
            while (isRunning) {
                //模拟网络延时
                TimeUnit.MILLISECONDS.sleep((long) (Math.random()*1000));
                data = atomicInteger.get();
                blockingQueue.offer(data);
                System.out.println("producer " + Thread.currentThread().getName() + " create data：" + data + ", size：" + blockingQueue.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void  stop() {
        isRunning = false;
    }
}
