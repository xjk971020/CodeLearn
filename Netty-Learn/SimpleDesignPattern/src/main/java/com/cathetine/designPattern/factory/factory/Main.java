package com.cathetine.designPattern.factory.factory;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午10:12
 * 工厂方法测试类
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("people factory produce:");
        PeopleFactory manFactory = new ManFactory();
        PeopleFactory womanFactory = new WomanFactory();
        manFactory.getPeople().type();
        womanFactory.getPeople().type();

        System.out.println("shape factory produce:");
        ShapeFactory circleFactory = new CircleShapeFactory();
        ShapeFactory rectangleFactory = new RectagnleShapeFactory();
        circleFactory.getShape().draw();
        rectangleFactory.getShape().draw();
    }
}
