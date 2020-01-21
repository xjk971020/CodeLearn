package com.cathetine.designPattern.factory.factory;

import com.cathetine.designPattern.factory.Rectangle;
import com.cathetine.designPattern.factory.Shape;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午10:02
 */
public class RectagnleShapeFactory implements ShapeFactory {
    public Shape getShape() {
        return new Rectangle();
    }
}
