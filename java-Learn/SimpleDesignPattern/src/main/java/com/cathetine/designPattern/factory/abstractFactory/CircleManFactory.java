package com.cathetine.designPattern.factory.abstractFactory;

import com.cathetine.designPattern.factory.Circle;
import com.cathetine.designPattern.factory.Man;
import com.cathetine.designPattern.factory.People;
import com.cathetine.designPattern.factory.Shape;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午10:10
 * 生产一个圆形的男人的具体工厂
 */
public class CircleManFactory implements AbstractFactory{
    public Shape getShape() {
        return new Circle();
    }

    public People getPeople() {
        return new Man();
    }
}
