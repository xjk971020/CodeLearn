package com.cathetine.designPattern.builder.pack;

import com.cathetine.designPattern.builder.food.Item;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午9:12
 * 冷饮
 */
public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }
}
