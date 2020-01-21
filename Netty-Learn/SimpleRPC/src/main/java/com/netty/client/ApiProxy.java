package com.netty.client;

import com.netty.RpcProtocol;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:xjk
 * @Date 2019/11/24 22:00
 * 接口代理
 */
public class ApiProxy implements InvocationHandler {

    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static NettyClientHandler handler;
    private String protocol;

    public ApiProxy(String host, int port, String protocol) {
        if (handler == null) {
            handler = new NettyClientHandler();
            NettyClient.init(host, port, handler);
        }
        this.protocol = protocol;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        handler.setParameter(protocol + args[0]);
        return executor.submit(handler).get();
    }
}
