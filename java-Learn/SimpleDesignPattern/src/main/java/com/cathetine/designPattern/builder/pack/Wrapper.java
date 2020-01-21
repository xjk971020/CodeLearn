package com.cathetine.designPattern.builder.pack;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午9:03
 * 纸质包装
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "wrapper";
    }
}
