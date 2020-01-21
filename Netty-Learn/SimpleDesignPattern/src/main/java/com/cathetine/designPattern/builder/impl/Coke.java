package com.cathetine.designPattern.builder.impl;

import com.cathetine.designPattern.builder.pack.ColdDrink;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午9:14
 * 可乐
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 2.0f;
    }
}
