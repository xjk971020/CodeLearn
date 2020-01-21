package com.cathetine.designPattern.chain;

/**
 * @Author:cathetine
 * @Date:19-10-7
 * @Time:下午10:36
 * 第一个具体的请求
 */
public class RequestFirst extends AbstractRequest{

    public RequestFirst(Object object) {
        super(object);
    }

    public int getRequestLevel() {
        return 1;
    }

}
