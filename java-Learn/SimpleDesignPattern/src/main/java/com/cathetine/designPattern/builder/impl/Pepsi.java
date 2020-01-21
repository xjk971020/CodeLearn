package com.cathetine.designPattern.builder.impl;

import com.cathetine.designPattern.builder.pack.ColdDrink;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午9:14
 */
public class Pepsi extends ColdDrink {
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 1.5f;
    }
}
