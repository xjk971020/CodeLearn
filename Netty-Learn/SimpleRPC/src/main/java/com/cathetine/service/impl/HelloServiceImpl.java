package com.cathetine.service.impl;

import com.cathetine.service.HelloService;

/**
 * @Author:cathetine
 * @Date:19-8-31
 * @Time:上午10:50
 */
public class HelloServiceImpl implements HelloService {
    private static int count = 1;
    @Override
    public String hello(String name) {
        System.out.println("接口被调用" + count++  + "次");
        if (name != null) {
            return "hello," + name;
        }
        return null;
    }
}
