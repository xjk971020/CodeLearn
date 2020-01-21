package com.cathetine.designPattern.decorator;

/**
 * @Author:cathetine
 * @Date:19-10-9
 * @Time:下午11:45
 * 主方法
 */
public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape rectangle = new Rectangle();

        System.out.println("normal border");
        circle.draw();
        rectangle.draw();

        Shape shapeDecorator = new RedBorderDecorator(circle);
        Shape rectangleDecorator = new RedBorderDecorator(rectangle);
        System.out.println("red border");
        shapeDecorator.draw();
        rectangleDecorator.draw();
    }
}
