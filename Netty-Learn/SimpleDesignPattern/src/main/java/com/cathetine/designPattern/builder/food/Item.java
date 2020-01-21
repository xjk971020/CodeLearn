package com.cathetine.designPattern.builder.food;

import com.cathetine.designPattern.builder.pack.Packing;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午8:48
 * 食物条目接口
 */
public interface Item {
    String name();
    Packing packing();
    float price();
}
