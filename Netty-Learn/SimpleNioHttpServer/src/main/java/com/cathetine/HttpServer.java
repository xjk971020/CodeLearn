package com.cathetine;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author:cathetine
 * @Date:19-8-31
 * @Time:下午3:07 基于NIO访问静态资源服务器
 */
public class HttpServer {
    protected static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "SimpleNioHttpServer" + File.separator + "webroot";
    protected static final int PORT = 8080;
    protected static final String HOST = "127.0.0.1";
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public static void main(String args[]) {
        HttpServer httpServer = new HttpServer();
        httpServer.run();
    }

    public void run() {
        //开始运行先创建服务器
        if (serverSocketChannel == null) {
            System.out.println("服务器启动......");
            System.out.println("静态资源路径:" + WEB_ROOT);
            createServer();
        }
        //一直等待创建连接
        while (true) {
            try {
                this.selector.select();//这地方会阻塞等待创建连接
                Set<SelectionKey> sets = this.selector.selectedKeys();
                Iterator<SelectionKey> iterator = sets.iterator();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    //处理感兴趣的事件
                    dohandelInteresting(selectionKey);
                    iterator.remove();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    private void dohandelInteresting(SelectionKey selectionKey) throws Exception {
        if (selectionKey.isAcceptable()) {//如果有连接接入那么进行处理

            doHandleLink();//处理连接
        } else if (selectionKey.isReadable()) {//希望请求数据
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            try {
                //处理请求，处理请求的时候可能会发生的异常：请求出错或者
                //远程客户端关闭连接，但服务端还进行操作，这时候统统关闭服务端连接
                doHandleAccess(socketChannel);
            } catch (Exception e) {
                //如果处理请求的过程中发生异常就由服务端取消注册并且关闭它
                socketChannel.close();
                selectionKey.cancel();
                throw e;
            }
        }
    }

    private void doHandleLink() throws IOException {
        SocketChannel socketChannel = this.serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    //处理请求分为两步，（1）解析请求资源，（2）回送给客户端
    private void doHandleAccess(SocketChannel socketChannel) throws Exception {
        //解析请求
        Request request = doHandleRequest(socketChannel);
        //回送客户端
        doHandleReply(request, socketChannel);
    }

    private void doHandleReply(Request request, SocketChannel socketChannel) throws Exception {
        Response response = new Response(socketChannel, request);
        response.sendStaticResource();
    }

    //处理到来的request请求
    private Request doHandleRequest(SocketChannel socketChannel) throws Exception {
        Request request = new Request(socketChannel);
        request.doHandelRequestContext();
        return request;
    }

    //创建服务端
    private void createServer() {
        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.socket().bind(new InetSocketAddress(HOST, PORT));
            this.serverSocketChannel.configureBlocking(false);
            createSelector();
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSelector() throws IOException {
        this.selector = Selector.open();
    }
}