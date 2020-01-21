package com.cathetine.designPattern.chain;

/**
 * @Author:cathetine
 * @Date:19-10-7
 * @Time:下午10:46
 * 主函数,　构建请求过程
 */
public class Main {
    public static void main(String[] args) {
        AbstractRequest requestFirst = new RequestFirst("请求级别1");
        AbstractRequest requestSecond= new RequestSecond("请求级别2");
        AbstractRequest requestThird = new RequestThird("请求级别3");

        Chain.handlerFirst.handleRequest(requestFirst);
        Chain.handlerFirst.handleRequest(requestSecond);
        Chain.handlerFirst.handleRequest(requestThird);
    }
}
