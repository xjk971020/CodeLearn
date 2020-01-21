package com.cathetine.designPattern.singleton;

/**
 * @Author:cathetine
 * @Date:19-9-22
 * @Time:下午7:09
 * 双重检验锁的线程安全的单例模式
 */
public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton doubleCheckSingleton;
    private DoubleCheckSingleton(){}
    public static DoubleCheckSingleton getInstance(){
        if (doubleCheckSingleton == null) {
            synchronized (doubleCheckSingleton) {
                if (doubleCheckSingleton == null) {
                    doubleCheckSingleton = new DoubleCheckSingleton();
                }
            }
        }
        return doubleCheckSingleton;
    }
}
