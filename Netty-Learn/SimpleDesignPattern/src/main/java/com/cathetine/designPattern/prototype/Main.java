package com.cathetine.designPattern.prototype;

/**
 * @Author:cathetine
 * @Date:19-9-23
 * @Time:下午10:07
 */
public class Main {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape rectangle = ShapeCache.getShape("1");
        Shape circle = ShapeCache.getShape("2");
        rectangle.draw();
        circle.draw();
    }
}
