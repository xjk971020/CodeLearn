package com.cathetine.designPattern.builder.impl;

import com.cathetine.designPattern.builder.food.Burger;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午9:04
 * 鸡肉汉堡
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 3.5f;
    }

}
