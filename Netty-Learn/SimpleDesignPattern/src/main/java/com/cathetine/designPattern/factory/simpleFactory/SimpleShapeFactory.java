package com.cathetine.designPattern.factory.simpleFactory;

import com.cathetine.designPattern.factory.Circle;
import com.cathetine.designPattern.factory.Rectangle;
import com.cathetine.designPattern.factory.Shape;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午9:31
 * 简单工厂模式
 * 适用场景
 * （1）需要创建的对象较少。
 *
 * （2）客户端不关心对象的创建过程。
 *
 */
public class SimpleShapeFactory {
    /**
     * 根据传递进去的字符串返回具体的实体
     * 当产品数增加的时候，需要在这里面修改代码，违反了设计模式的开闭原则，可以用反射解决该问题
     * @param type
     * @return
     */
    public static Shape getInstance(String type) {
        if ("rectangle".equals(type)) {
            return new Rectangle();
        } else if ("circle".equals(type)) {
            return new Circle();
        } else {
            throw new RuntimeException("No product");
        }
    }

    public static Object getInstance(Class<? extends Shape> clazz) {
        Object obj = null;
        try {
            obj = Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
