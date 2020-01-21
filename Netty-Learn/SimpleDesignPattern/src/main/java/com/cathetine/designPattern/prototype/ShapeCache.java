package com.cathetine.designPattern.prototype;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:cathetine
 * @Date:19-9-23
 * @Time:下午10:02
 * 缓存类
 */
public class ShapeCache {
    private static ConcurrentHashMap<String, Shape> shapeMap = new ConcurrentHashMap<String, Shape>();
    public static Shape getShape(String shapeId) {
        Shape shapeCache = shapeMap.get(shapeId);
        return (Shape) shapeCache.clone();
    }

    // 对每种形状都运行数据库查询，并创建该形状
    // shapeMap.put(shapeKey, shape);
    // 例如，我们要添加三种形状
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setShapeId("1");
        shapeMap.put(circle.getShapeId(),circle);


        Rectangle rectangle = new Rectangle();
        rectangle.setShapeId("2");
        shapeMap.put(rectangle.getShapeId(),rectangle);
    }
}
