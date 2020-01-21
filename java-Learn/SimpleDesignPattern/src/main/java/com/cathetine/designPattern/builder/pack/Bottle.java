package com.cathetine.designPattern.builder.pack;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午9:03
 * 瓶装包装
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "bottle";
    }
}
