package com.cathetine.designPattern.prototype;

/**
 * @Author:cathetine
 * @Date:19-9-23
 * @Time:下午9:56
 */
public class Rectangle extends Shape {
    public Rectangle() {
        type = "rectangle";
    }
    void draw() {
        System.out.println("draw: rectangle");
    }
}
