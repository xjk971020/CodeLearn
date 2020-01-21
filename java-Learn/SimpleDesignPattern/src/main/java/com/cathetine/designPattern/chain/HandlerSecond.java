package com.cathetine.designPattern.chain;

/**
 * @Author:cathetine
 * @Date:19-10-7
 * @Time:下午10:34
 * 处理链中第二个具体的处理类
 */
public class HandlerSecond extends AbstractHandler{
    protected int getHandleLevel() {
        return 2;
    }

    protected void handle(AbstractRequest request) {
        System.out.println(TAG + "  处理级别2 <-> 处理的请求级别:" + request.getContent());
    }
}
