package com.cathetine.provider;

import com.cathetine.RPCFramework;
import com.cathetine.service.HelloService;
import com.cathetine.service.impl.HelloServiceImpl;

/**
 * @Author:cathetine
 * @Date:19-8-31
 * @Time:上午10:52
 * 服务提供者
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        RPCFramework.export(helloService, 1234);
    }
}
