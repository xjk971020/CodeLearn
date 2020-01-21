package com.cathetine;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author:cathetine
 * @Date:19-9-1
 * @Time:下午12:54
 * SynchronousQueue 数据同步交换的队列
 *
 * 一个虚假的队列，因为它实际上没有真正用于存储元素的空间，每个插入操作都必须有对应的取出操作，没取出时无法继续放入。
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        final SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            for (int i = 1; i <= 10; ++i) {
                try {
                    synchronousQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("加入第" + i + "个");
            }
        }).start();
        new Thread(() -> {
            while(true) {
                try {
                    System.out.println("取出第" + synchronousQueue.take() + "个");
                    long timeSleep = (long) (Math.random()*1000);
                    TimeUnit.MILLISECONDS.sleep(timeSleep);
                    System.out.println("休息" + timeSleep + "毫秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
