package com.cathetine.designPattern.chain;

/**
 * @Author:cathetine
 * @Date:19-10-7
 * @Time:下午10:40
 * 处理链中的第三个
 */
public class HandlerThird extends AbstractHandler{
    protected int getHandleLevel() {
        return 3;
    }

    protected void handle(AbstractRequest request) {
        System.out.println(TAG + "  处理级别3 <-> 处理的请求级别:" + request.getContent());
    }
}
