package com.cathetine.designPattern.decorator;

/**
 * @Author:cathetine
 * @Date:19-10-9
 * @Time:下午11:38
 * 红边装饰器
 */
public class RedBorderDecorator extends ShapeDecorator {

    public RedBorderDecorator(Shape shapeDecorator) {
        super(shapeDecorator);
    }

    @Override
    public void draw() {
        shapeDecorator.draw();
        setRedBorderDecorator(shapeDecorator);
    }

    public void setRedBorderDecorator(Shape shapeDecorator) {
        System.out.println("Border color : red ");
    }
}
