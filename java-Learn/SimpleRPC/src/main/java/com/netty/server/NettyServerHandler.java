package com.netty.server;

import com.cathetine.service.HelloService;
import com.cathetine.service.impl.HelloServiceImpl;
import com.netty.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author:xjk
 * @Date 2019/11/24 20:26
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String)msg;
        //匹配协议是否对的上，对的上才能调用接口方法
        if (message.startsWith(RpcProtocol.PROVIDER_PROTOCOL)) {
            HelloService helloService = new HelloServiceImpl();
            String result = helloService.hello(message.substring(message.lastIndexOf("#") + 1));
            System.out.println("服务端返回信息:" + result);
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}