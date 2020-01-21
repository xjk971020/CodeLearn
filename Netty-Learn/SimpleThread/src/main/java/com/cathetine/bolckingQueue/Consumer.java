package com.cathetine.bolckingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author:cathetine
 * @Date:19-9-1
 * @Time:下午5:17 消费者
 */
public class Consumer implements Runnable {

    private boolean isRunning;
    private BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        isRunning = true;
    }

    @Override
    public void run() {
        int data;
        try {
            while (isRunning) {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
                // 从阻塞队列中获取数据
                if (!blockingQueue.isEmpty()) {
                    data = blockingQueue.take();
                    System.out.println("consumer " + Thread.currentThread().getName() + " consume data：" + data + ", size：" + blockingQueue.size());
                } else {
                    System.out.println("Queue is empty, consumer " + Thread.currentThread().getName() + " is waiting, size：" + blockingQueue.size());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
