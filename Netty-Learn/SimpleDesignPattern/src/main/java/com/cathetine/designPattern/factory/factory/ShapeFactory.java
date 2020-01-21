package com.cathetine.designPattern.factory.factory;

import com.cathetine.designPattern.factory.Shape;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午9:37
 * 工厂模式
 * 一个抽象工厂对应一个产品族，一个实现抽象工厂的子类工厂去生产具体的产品
 * 比如说这里抽象了一个ShapeFactory工厂，用CircleShapeFactory实现该工厂生产具体的图形
 */
public interface ShapeFactory {
    Shape getShape();
}
