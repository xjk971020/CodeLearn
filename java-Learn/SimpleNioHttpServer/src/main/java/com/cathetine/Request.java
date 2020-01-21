package com.cathetine;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * @Author:cathetine
 * @Date:19-8-31
 * @Time:下午3:08 接受请求，找出对应的url
 */
public class Request {

    private String requestContext;
    private SocketChannel socketChannel;
    private String url;

    public Request(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public String getRequestContext() {
        return requestContext;
    }

    public String getUrl() {
        return url;
    }

    void doHandelRequestContext() throws Exception {
        paserRequestContext();
        paserRequestUrl();
    }

    private void paserRequestContext() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        byte[] temp = new byte[1024];
        int length = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((length = socketChannel.read(byteBuffer)) > 0) {
            stringBuilder.append(new String(byteBuffer.array(), 0, length));
        }
        this.requestContext = stringBuilder.toString();
        if (this.requestContext.trim().equals("")) {
            throw new Exception("请求不合法");
        }
    }

    private void paserRequestUrl() {
        int index = requestContext.indexOf(" ");
        if (index != -1) {
            url = requestContext.substring(index + 1, requestContext.indexOf(" ", index + 1));
        }
        System.out.println("请求地址: " + getUrl());
        System.out.println("请求时间: " + new Date());
        System.out.println();
        //默认请求/index.html
        if (url.equals("/")) {
            url = "/index.html";
        }
    }

}
