package com.cathetine.waitNotify;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:cathetine
 * @Date:19-9-1
 * @Time:下午8:01
 * wait() notify()方法模拟生产者消费者模型
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> shareQueue = new ArrayList<>();

        MyThreadFactory consumerThreadFactory = new MyThreadFactory("消费者");
        MyThreadFactory producerThreadFactory = new MyThreadFactory("生产者");
        ExecutorService producerExecutorService = Executors.newFixedThreadPool(10, producerThreadFactory);
        ExecutorService consumerExecutorService = Executors.newFixedThreadPool(10, consumerThreadFactory);
        Producer producerThread1 = new Producer(shareQueue, 100);
        Producer producerThread2 = new Producer(shareQueue, 10);
        Producer producerThread3 = new Producer(shareQueue, 10);
        Producer producerThread4 = new Producer(shareQueue, 10);
        Consumer consumerThread1 = new Consumer(shareQueue);
        Consumer consumerThread2 = new Consumer(shareQueue);
        Consumer consumerThread3 = new Consumer(shareQueue);
        producerExecutorService.execute(producerThread1);
        producerExecutorService.execute(producerThread2);
//        producerExecutorService.execute(producerThread3);
//        producerExecutorService.execute(producerThread4);
        consumerExecutorService.execute(consumerThread1);
        consumerExecutorService.execute(consumerThread2);
//        consumerExecutorService.execute(consumerThread3);
//        TimeUnit.SECONDS.sleep(3);
//        producerThread1.stop();
//        producerThread2.stop();
//        producerThread3.stop();
//        producerThread4.stop();

//        TimeUnit.SECONDS.sleep(4);

        //等待消费完就停止生产者
//        if (blockingQueue.isEmpty()) {
//            consumerThread1.stop();
//            consumerThread2.stop();
//            consumerThread3.stop();
//        }
        producerExecutorService.shutdown();
        consumerExecutorService.shutdown();
    }
}
