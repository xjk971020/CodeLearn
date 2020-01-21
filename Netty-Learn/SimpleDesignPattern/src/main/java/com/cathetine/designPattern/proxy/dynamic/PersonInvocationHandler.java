package com.cathetine.designPattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author:cathetine
 * @Date:19-9-9
 * @Time:下午9:12
 */
public class PersonInvocationHandler<T> implements InvocationHandler {
    private T target;

    public PersonInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MonitorUtil.start();
        Thread.sleep(1);        /** 调用呗代理对象的真实方法，*/
        Object result = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return result;
    }
}
