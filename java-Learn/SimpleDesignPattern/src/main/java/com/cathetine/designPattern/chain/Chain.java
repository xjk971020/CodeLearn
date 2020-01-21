package com.cathetine.designPattern.chain;

/**
 * @Author:cathetine
 * @Date:19-10-7
 * @Time:下午10:38
 */
public class Chain {

    static AbstractHandler handlerFirst = new HandlerFirst();
    static AbstractHandler handlerSecond = new HandlerSecond();
    static AbstractHandler handlerThird = new HandlerThird();
    static {
        handlerFirst.nextHandler = handlerSecond;
        handlerSecond.nextHandler =handlerThird;
    }
}
