package com.cathetine.designPattern.chain;

/**
 * @Author:cathetine
 * @Date:19-10-7
 * @Time:下午10:33
 * 责任链中第一个处理者
 */
public class HandlerFirst extends AbstractHandler{
    protected int getHandleLevel() {
        return 1;
    }

    protected void handle(AbstractRequest request) {
        System.out.println(TAG + "  处理级别1 <-> 处理的请求级别:" + request.getContent());
    }
}
