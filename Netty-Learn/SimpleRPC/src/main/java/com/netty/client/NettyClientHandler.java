package com.netty.client;

import com.netty.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @Author:xjk
 * @Date 2019/11/24 20:27
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable<String> {
    private ChannelHandlerContext context;
    private String result;
    private String parameter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = (String) msg;
        System.out.println("客户端接收到服务端信息:" + result);
        notifyAll();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public synchronized String call() throws Exception {
        context.writeAndFlush(RpcProtocol.PROVIDER_PROTOCOL + parameter);
        wait();
        return result;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
