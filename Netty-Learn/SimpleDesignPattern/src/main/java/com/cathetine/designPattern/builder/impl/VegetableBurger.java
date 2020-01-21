package com.cathetine.designPattern.builder.impl;

import com.cathetine.designPattern.builder.food.Burger;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午9:10
 * 素菜汉堡
 */
public class VegetableBurger extends Burger {
    @Override
    public String name() {
        return "Vegetable Burger";
    }

    @Override
    public float price() {
        return 5.5f;
    }
}
