package com.netty.provider;

import com.netty.api.HelloServcice;

/**
 * @Author:xjk
 * @Date 2019/11/24 20:27
 */
public class HelloServiceImpl implements HelloServcice {
    @Override
    public String hello(String message) {
        if (message == null) {
            return "你好客户端，我已经接收到你的消息";
        } else {
            System.out.println("服务端接受消息:" + message);
            return "你好客户端，我已经接受到你的消息，消息为: " + message;
        }
    }
}
