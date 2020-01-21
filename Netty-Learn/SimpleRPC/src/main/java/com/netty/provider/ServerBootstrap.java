package com.netty.provider;

import com.netty.server.NettyServer;

/**
 * @Author:xjk
 * @Date 2019/11/24 20:29
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        new NettyServer().startServer(7000,"127.0.0.1");
    }
}
