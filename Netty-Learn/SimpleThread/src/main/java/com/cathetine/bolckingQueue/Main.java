package com.cathetine.bolckingQueue;

import java.util.concurrent.*;

/**
 * @Author:cathetine
 * @Date:19-9-1
 * @Time:下午5:05
 * 阻塞队列模拟生产者消费者模型
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>();

        MyThreadFactory consumerThreadFactory = new MyThreadFactory("消费者");
        MyThreadFactory producerThreadFactory = new MyThreadFactory("生产者");
        ExecutorService producerExecutorService = Executors.newFixedThreadPool(10, producerThreadFactory);
        ExecutorService consumerExecutorService = Executors.newFixedThreadPool(10, consumerThreadFactory);
        Producer producerThread1 = new Producer(blockingQueue);
        Producer producerThread2 = new Producer(blockingQueue);
        Producer producerThread3 = new Producer(blockingQueue);
        Producer producerThread4 = new Producer(blockingQueue);
        Consumer consumerThread1 = new Consumer(blockingQueue);
        Consumer consumerThread2 = new Consumer(blockingQueue);
        Consumer consumerThread3 = new Consumer(blockingQueue);
        producerExecutorService.execute(producerThread1);
        producerExecutorService.execute(producerThread2);
        producerExecutorService.execute(producerThread3);
        producerExecutorService.execute(producerThread4);
        consumerExecutorService.execute(consumerThread1);
        consumerExecutorService.execute(consumerThread2);
        consumerExecutorService.execute(consumerThread3);
        TimeUnit.SECONDS.sleep(3);
        producerThread1.stop();
        producerThread2.stop();
        producerThread3.stop();
        producerThread4.stop();

        TimeUnit.SECONDS.sleep(4);

        //等待消费完就停止生产者
        if (blockingQueue.isEmpty()) {
            consumerThread1.stop();
            consumerThread2.stop();
            consumerThread3.stop();
        }
        producerExecutorService.shutdown();
        consumerExecutorService.shutdown();
    }
}
