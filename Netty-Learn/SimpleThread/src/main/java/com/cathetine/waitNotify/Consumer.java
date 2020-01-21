package com.cathetine.waitNotify;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Author:cathetine
 * @Date:19-9-1
 * @Time:下午7:55
 */
public class Consumer implements Runnable {

    private boolean isRunning;
    private ArrayList<Integer> shareQueue;

    public Consumer(ArrayList<Integer> shareQueue) {
        this.shareQueue = shareQueue;
        this.isRunning = true;
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                //模拟网络延迟
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
                while (shareQueue.isEmpty()) {
                    synchronized (shareQueue) {
                        System.out.println(Thread.currentThread().getName() + "进入等待状态 缓冲区空");
                        shareQueue.wait();
                    }
                }
                synchronized (shareQueue) {
                    System.out.println(Thread.currentThread().getName() + " consume data：" + shareQueue.remove(0) + ", size：" + shareQueue.size());
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
