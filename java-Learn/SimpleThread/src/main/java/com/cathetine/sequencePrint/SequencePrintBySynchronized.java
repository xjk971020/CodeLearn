package com.cathetine.sequencePrint;

/**
 * @Author:cathetine
 * @Date:19-9-22
 * @Time:下午3:51
 * 通过synchronized关键字进行顺序打印
 */
public class SequencePrintBySynchronized {
    private static boolean isA = true;
    private static boolean isB = false;
    private static boolean isC = false;

    public static void main(String[] args) {
        final Object object = new Object();
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10;) {
                synchronized (object) {
                    if (isA) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        isA = false;
                        isB = true;
                        isC = false;
                        ++i;
                        object.notifyAll();
                    } else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, "线程A");
        Thread two = new Thread(() -> {
            for (int i = 0; i < 10;) {
                synchronized (object) {
                    if (isB) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        isA = false;
                        isB = false;
                        isC = true;
                        ++i;
                        object.notifyAll();
                    } else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "线程B");
        Thread three = new Thread(() -> {
            for (int i = 0; i < 10;) {
                synchronized (object) {
                    if (isC) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        isA = true;
                        isB = false;
                        isC = false;
                        ++i;
                        object.notifyAll();
                    } else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "线程C");
        one.start();
        two.start();
        three.start();
    }
}
