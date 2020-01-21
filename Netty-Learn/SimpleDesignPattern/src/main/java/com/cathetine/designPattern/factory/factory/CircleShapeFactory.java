package com.cathetine.designPattern.factory.factory;

import com.cathetine.designPattern.factory.Circle;
import com.cathetine.designPattern.factory.Shape;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午10:02
 * 具体的生产圆形的工厂
 */
public class CircleShapeFactory implements ShapeFactory{
    public Shape getShape() {
        return new Circle();
    }
}
