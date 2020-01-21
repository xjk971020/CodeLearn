package com.netty.consumer;

import com.cathetine.service.HelloService;
import com.netty.RpcProtocol;
import com.netty.client.NettyClient;

import java.util.concurrent.TimeUnit;

/**
 * @Author:xjk
 * @Date 2019/11/24 20:29
 */
public class ClientBootstrap {
    public static void main(String[] args) throws InterruptedException {
        NettyClient nettyClient = new NettyClient();
        HelloService helloService = (HelloService) nettyClient.getBean(HelloService.class, RpcProtocol.PROVIDER_PROTOCOL,"localhost", 7000);
        for (;;) {
            TimeUnit.SECONDS.sleep(2);
            helloService.hello("dubbo");
        }
    }
}
