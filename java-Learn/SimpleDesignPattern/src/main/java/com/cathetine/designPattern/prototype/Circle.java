package com.cathetine.designPattern.prototype;

/**
 * @Author:cathetine
 * @Date:19-9-23
 * @Time:下午10:01
 * 圆形
 */
public class Circle extends Shape{
    public Circle() {
        type = "circle";
    }
    void draw() {
        System.out.println("draw: circle");
    }
}
