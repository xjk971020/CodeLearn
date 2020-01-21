package com.cathetine.designPattern.observer;

/**
 * @Author:cathetine
 * @Date:19-9-22
 * @Time:下午9:56
 * 主函数
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new ConcreteFirstObserver(subject);
        new ConcreteSecondObserver(subject);
        subject.notifyAllObservers();
    }
}
