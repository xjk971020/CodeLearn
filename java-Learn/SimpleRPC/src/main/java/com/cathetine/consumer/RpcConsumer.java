package com.cathetine.consumer;

import com.cathetine.RPCFramework;
import com.cathetine.service.HelloService;

/**
 * @Author:cathetine
 * @Date:19-8-31
 * @Time:上午10:55
 * 服务消费者
 */
public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        HelloService helloService = RPCFramework.refer(HelloService.class, "127.0.0.1", 1234);
        String result = helloService.hello("cathetine");
        System.out.println(result);
    }
}
