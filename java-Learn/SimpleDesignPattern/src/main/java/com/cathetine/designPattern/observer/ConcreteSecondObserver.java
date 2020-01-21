package com.cathetine.designPattern.observer;

/**
 * @Author:cathetine
 * @Date:19-9-22
 * @Time:下午9:55
 * 第二个具体的观察者
 */
public class ConcreteSecondObserver extends Observer{

    private Subject subject;
    public ConcreteSecondObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
    public void update() {
        System.out.println("第二个具体的观察者被通知了");
    }
}
