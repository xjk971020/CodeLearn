package com.netty.client;

import com.cathetine.service.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;

/**
 * @Author:xjk
 * @Date 2019/11/24 20:26
 */
public class NettyClient {

    public Object getBean(final Class<?> serviceClass, final String protocol,String host, int port) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{serviceClass}, new ApiProxy(host, port, protocol));
    }

    public static void init(String host, int port, NettyClientHandler handler) {
        System.out.println("客户端连接服务端,地址: " + host + ":" + port );
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new NettyClientInitializer(handler));
            bootstrap.connect(host, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
