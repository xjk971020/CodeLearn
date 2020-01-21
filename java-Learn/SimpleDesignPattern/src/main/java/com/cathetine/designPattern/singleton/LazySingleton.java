package com.cathetine.designPattern.singleton;

/**
 * @Author:cathetine
 * @Date:19-9-22
 * @Time:下午7:02
 * 懒汉单例模式
 */
public class LazySingleton {
    private static LazySingleton lazySingleton;
    private LazySingleton() {}
    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
