package com.cathetine.designPattern.factory.simpleFactory;

import com.cathetine.designPattern.factory.Circle;
import com.cathetine.designPattern.factory.Rectangle;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午9:53
 * 简单工厂模式测试方法
 */
public class Main {
    public static void main(String[] args) {
        Circle circle = (Circle) SimpleShapeFactory.getInstance("circle");
        Rectangle rectangle = (Rectangle) SimpleShapeFactory.getInstance("rectangle");
        System.out.println("通过字符串获取: ");
        circle.draw();
        rectangle.draw();

        Circle circle1 = (Circle) SimpleShapeFactory.getInstance(Circle.class);
        Rectangle rectangle1 = (Rectangle) SimpleShapeFactory.getInstance(Rectangle.class);
        System.out.println("通过反射获取: ");
        circle1.draw();
        rectangle1.draw();
    }
}
