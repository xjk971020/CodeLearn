package com.cathetine.designPattern.chain;

/**
 * @Author:cathetine
 * @Date:19-10-7
 * @Time:下午10:52
 */
public class RequestThird extends AbstractRequest{
    public RequestThird(Object object) {
        super(object);
    }

    public int getRequestLevel() {
        return 3;
    }
}
