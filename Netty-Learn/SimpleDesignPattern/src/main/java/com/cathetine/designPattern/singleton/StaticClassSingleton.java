package com.cathetine.designPattern.singleton;

/**
 * @Author:cathetine
 * @Date:19-9-22
 * @Time:下午7:12
 * 静态内部类
 */
public class StaticClassSingleton {
    private StaticClassSingleton() {}
    public static StaticClassSingleton getInstance() {
        return SingletonHandler.staticClassSingleton;
    }
    private static class SingletonHandler{
        private static StaticClassSingleton staticClassSingleton = new StaticClassSingleton();
    }
}
