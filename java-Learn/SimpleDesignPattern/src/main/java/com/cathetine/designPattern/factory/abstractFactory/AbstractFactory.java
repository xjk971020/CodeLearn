package com.cathetine.designPattern.factory.abstractFactory;

import com.cathetine.designPattern.factory.People;
import com.cathetine.designPattern.factory.Shape;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午10:07
 * 抽象工厂, 与工厂方法的区别在于他能生产多个产品族
 * 在子类中可以多个产品族相互组合
 */
public interface AbstractFactory {
    Shape getShape();
    People getPeople();
}
