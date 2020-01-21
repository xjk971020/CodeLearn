# 基于NIO的简单静态服务器

---

## 一、准备过程

​     实现的http服务器只可以访问静态资源(html, css, js)或者jpg格式的图片，需要将文件放在webroot目录下。


## 二、设计流程

​    1、开发Request进行请求资源的解析，找到请求的路径，如果请求不合法抛出异常。
​    2、开发Response将资源返回给客户端
​    3、开发HttpServer，创建ServerSocketChannel,获得客户端的SocketChannel进行处理。并将产生异常的请求关闭。

## 三、项目设计

```
SimpleNioHttpServer
├── readme.md
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── cathetine
│   │   │           ├── HttpServer -- 服务器启动类
│   │   │           ├── Request       -- 解析请求的类
│   │   │           ├── Response   -- 响应请求的类
│   │   ├── resources
├── webroot -- 可访问的静态资源存放目录
```

