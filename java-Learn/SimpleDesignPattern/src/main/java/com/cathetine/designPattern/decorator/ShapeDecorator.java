package com.cathetine.designPattern.decorator;

/**
 * @Author:cathetine
 * @Date:19-10-9
 * @Time:下午11:36
 * 装饰器抽象接口
 */
public abstract class ShapeDecorator implements Shape{
    protected Shape shapeDecorator;
    public ShapeDecorator(Shape shapeDecorator) {
        this.shapeDecorator = shapeDecorator;
    }
    public abstract void draw();
}
