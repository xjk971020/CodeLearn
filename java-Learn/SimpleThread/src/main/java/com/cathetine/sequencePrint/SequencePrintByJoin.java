package com.cathetine.sequencePrint;

import java.util.concurrent.TimeUnit;

/**
 * @Author:cathetine
 * @Date:19-9-22
 * @Time:下午3:17
 * 使用join()方法顺序打印线程
 */
public class SequencePrintByJoin {

    public static void main(String[] args) {
        ThreadOne threadOne = new ThreadOne();
        ThreadTwo threadTwo = new ThreadTwo();
        ThreadThree threadThree = new ThreadThree();
        Thread one = new Thread(threadOne);
        Thread two = new Thread(threadTwo);
        Thread three = new Thread(threadThree);
        one.start();
        try {
            one.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        two.start();
        try {
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        three.start();
    }
}

class ThreadOne implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("A");
    }
}

class ThreadTwo implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("B");
    }
}

class ThreadThree implements Runnable {

    @Override
    public void run() {
        System.out.println("C");
    }
}
