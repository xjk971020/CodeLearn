package com.cathetine.designPattern.factory.abstractFactory;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午10:15
 * 抽象工厂模式主测试方法
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory circleManFacroty = new CircleManFactory();
        circleManFacroty.getPeople().type();
        circleManFacroty.getShape().draw();
    }
}
