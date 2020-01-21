package com.cathetine.designPattern.chain;

/**
 * @Author:cathetine
 * @Date:19-10-7
 * @Time:下午10:19
 * 抽象处理链父类
 */
public abstract class AbstractHandler {
    //当前类的标示
    protected static final String TAG = AbstractHandler.class.getSimpleName();
    //链中的下一个处理类
    protected AbstractHandler nextHandler;

    public final void handleRequest(AbstractRequest request) {
        if (getHandleLevel() == request.getRequestLevel()) {
            handle(request);
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            } else {
                throw new RuntimeException("该责任链无法处理此请求");
            }
        }
    }

    //处理级别
    protected abstract int getHandleLevel();
    //可以具体到每个实现类的处理逻辑
    protected abstract void handle(AbstractRequest request);
}
