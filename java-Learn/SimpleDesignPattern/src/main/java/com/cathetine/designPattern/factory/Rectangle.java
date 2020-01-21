package com.cathetine.designPattern.factory;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午9:28
 * 实体产品长方形
 */
public class Rectangle implements Shape {
    public void draw() {
        System.out.println("draw : rectangle");
    }
}
