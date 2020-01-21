package com.cathetine.designPattern.factory;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午9:29
 * 实体产品圆形
 */
public class Circle implements Shape {
    public void draw() {
        System.out.println("draw : circle");
    }
}
