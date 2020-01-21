package com.cathetine.designPattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:cathetine
 * @Date:19-9-22
 * @Time:下午9:46 主体类
 */
public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
