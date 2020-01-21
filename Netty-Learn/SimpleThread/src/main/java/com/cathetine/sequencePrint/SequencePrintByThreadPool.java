package com.cathetine.sequencePrint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:cathetine
 * @Date:19-9-22
 * @Time:下午3:33
 * 使用线程池顺序打印线程
 */
public class SequencePrintByThreadPool {
    public static void main(String[] args) {
        ThreadOne threadOne = new ThreadOne();
        ThreadTwo threadTwo = new ThreadTwo();
        ThreadThree threadThree = new ThreadThree();
        Thread one = new Thread(threadOne);
        Thread two = new Thread(threadTwo);
        Thread three = new Thread(threadThree);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(one);
        singleThreadExecutor.execute(two);
        singleThreadExecutor.execute(three);
        singleThreadExecutor.shutdown();
    }
}
