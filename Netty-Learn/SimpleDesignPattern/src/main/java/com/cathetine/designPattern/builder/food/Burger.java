package com.cathetine.designPattern.builder.food;

import com.cathetine.designPattern.builder.pack.Packing;
import com.cathetine.designPattern.builder.pack.Wrapper;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午8:59
 * 汉堡类食品抽象类
 */
public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }
}
