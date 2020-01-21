package com.cathetine.designPattern.chain;

/**
 * @Author:cathetine
 * @Date:19-10-7
 * @Time:下午10:37
 * 第二个具体的请求
 */
public class RequestSecond extends AbstractRequest{

    public RequestSecond(Object object) {
        super(object);
    }

    public int getRequestLevel() {
        return 2;
    }
}
