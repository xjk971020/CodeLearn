package com.cathetine.designPattern.chain;

/**
 * @Author:cathetine
 * @Date:19-10-7
 * @Time:下午10:22
 * 请求的抽象父类
 */
public abstract class AbstractRequest {
    //请求的内容
    private Object object;
    public AbstractRequest(Object object) {
        this.object = object;
    }
    public Object getContent() {
        return object;
    }
    //用请求级别模拟具体的不同的请求
    public abstract int getRequestLevel();
}
