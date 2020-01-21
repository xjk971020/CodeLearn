package com.cathetine.designPattern.observer;

/**
 * @Author:cathetine
 * @Date:19-9-22
 * @Time:下午9:45
 * 观察者主类
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
