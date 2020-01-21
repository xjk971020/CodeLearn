# readme

------

## RPC 框架介绍

　对于多体应用，由于各服务部署在不同机器，服务间的调用免不了网络通信过程，服务消费方每调用一个服务都要写一坨网络通信相关的代码，不仅复杂而且极易出错。如果有一种方式能让我们像调用本地服务一样调用远程服务，而让调用者对网络通信这些细节透明，那么将大大解放程序员的双手，大幅度提高生产力。比如，服务消费方在执行helloService.hi(“Panda”)时，实质上调用的是远端的服务。这种方式其实就是RPC（Remote Procedure Call Protocol），在各大互联网公司中被广泛使用，如阿里巴巴的HSF、Dubbo（开源）、Facebook的Thrift（开源）、Google GRPC（开源）、Twitter的Finagle（开源）等。

　　RPC的主要功能目标是**让构建分布式计算（应用）更容易，在提供强大的远程调用能力时不损失本地调用的语义简洁性**。为实现该目标，RPC框架需提供一种透明调用机制让使用者不必显式的区分本地调用和远程调用。要让网络通信细节对使用者透明，我们需要对通信细节进行封装，下面是一个RPC的经典调用的流程，并且反映了所涉及到的一些通信细节：

![](http://cdn.cathetine.cn/images/github/simpleRPC/1.jpg)

　(1). 服务消费方（client）以本地调用方式调用服务； 
　(2). client stub接收到调用后负责将方法、参数等组装成能够进行网络传输的消息体； 
　(3). client stub找到服务地址，并将消息发送到服务端； 
　(4). server stub收到消息后进行解码； 
　(5). server stub根据解码结果 反射调用 本地的服务； 
　(6). 本地服务执行并将结果返回给server stub； 
　(7). server stub将返回结果打包成消息并发送至消费方； 
　(8). client stub接收到消息，并进行解码； 
　(9). 服务消费方得到最终结果。

　　RPC框架就是要将2~8这些步骤封装起来，让用户对这些细节透明，使得远程方法调用看起来像调用本地方法一样。

## RPC框架简易实现及其实例分析

(1).服务端

　　服务端提供客户端所期待的服务，一般包括三个部分：服务接口，服务实现以及服务的注册暴露三部分，如下：

- 服务接口

```java
public interface HelloService {

    String hello(String name);

    String hi(String msg);
}
```

- 服务实现

```java
public class HelloServiceImpl implements HelloService{
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

    @Override
    public String hi(String msg) {
        return "Hi, " + msg;
    }
}
```

- 服务暴露：只有把服务暴露出来，才能让客户端进行调用，这是RPC框架功能之一。

```java
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        // RPC框架将服务暴露出来，供客户端消费
        RpcFramework.export(service, 1234);
    }
}
```

(2).客户端

　　客户端消费服务端所提供的服务，一般包括两个部分：服务接口和服务引用两个部分，如下：

- 服务接口：与服务端共享同一个服务接口

```java
public interface HelloService {

    String hello(String name);

    String hi(String msg);
}
```

- 服务引用：消费端通过RPC框架进行远程调用，这也是RPC框架功能之一

```java
public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        // 由RpcFramework生成的HelloService的代理
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        String hello = service.hello("World");
        System.out.println("客户端收到远程调用的结果 ： " + hello);
    }
}
```

(3).RPC框架原型实现

　　RPC框架主要包括两大功能：一个用于服务端暴露服务，一个用于客户端引用服务。

- 服务端暴露服务

```java
    /**
     * 暴露服务
     *
     * @param service 服务实现
     * @param port    服务端口
     * @throws Exception
     */
    public static void export(final Object service, int port) throws Exception {
        if (service == null) {
            throw new IllegalArgumentException("service instance == null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println("Export service " + service.getClass().getName() + " on port " + port);
        // 建立Socket服务端
        ServerSocket server = new ServerSocket(port);
        for (; ; ) {
            try {
                // 监听Socket请求
                final Socket socket = server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            try {
                                /* 获取请求流，Server解析并获取请求*/
                                // 构建对象输入流，从源中读取对象到程序中
                                ObjectInputStream input = new ObjectInputStream(
                                    socket.getInputStream());
                                try {

                                    System.out.println("\nServer解析请求 ： ");
                                    String methodName = input.readUTF();
                                    System.out.println("methodName : " + methodName);
                                    // 泛型与数组是不兼容的，除了通配符作泛型参数以外
                                    Class<?>[] parameterTypes = (Class<?>[])input.readObject();
                                    System.out.println(
                                        "parameterTypes : " + Arrays.toString(parameterTypes));
                                    Object[] arguments = (Object[])input.readObject();
                                    System.out.println("arguments : " + Arrays.toString(arguments));


                                    /* Server 处理请求，进行响应*/
                                    ObjectOutputStream output = new ObjectOutputStream(
                                        socket.getOutputStream());
                                    try {
                                        // service类型为Object的(可以发布任何服务)，故只能通过反射调用处理请求
                                        // 反射调用，处理请求
                                        Method method = service.getClass().getMethod(methodName,
                                            parameterTypes);
                                        Object result = method.invoke(service, arguments);
                                        System.out.println("\nServer 处理并生成响应 ：");
                                        System.out.println("result : " + result);
                                        output.writeObject(result);
                                    } catch (Throwable t) {
                                        output.writeObject(t);
                                    } finally {
                                        output.close();
                                    }
                                } finally {
                                    input.close();
                                }
                            } finally {
                                socket.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
```

　从该RPC框架的简易实现来看，RPC服务端逻辑是：首先创建ServerSocket负责监听特定端口并接收客户连接请求，然后使用Java原生的序列化/反序列化机制来解析得到请求，包括所调用方法的名称、参数列表和实参，最后反射调用服务端对服务接口的具体实现并将得到的结果回传至客户端。至此，一次简单PRC调用的服务端流程执行完毕。

- 客户端引用服务

```java
    /**
     * 引用服务
     *
     * @param <T>            接口泛型
     * @param interfaceClass 接口类型
     * @param host           服务器主机名
     * @param port           服务器端口
     * @return 远程服务，返回代理对象
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port)
        throws Exception {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("Interface class == null");
        }
        // JDK 动态代理的约束，只能实现对接口的代理
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException(
                "The " + interfaceClass.getName() + " must be interface class!");
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("Host == null!");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println(
            "Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);

        // JDK 动态代理
        T proxy = (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass}, new InvocationHandler() {
                // invoke方法本意是对目标方法的增强，在这里用于发送RPC请求和接收响应
                @Override
                public Object invoke(Object proxy, Method method, Object[] arguments)
                    throws Throwable {
                    // 创建Socket客户端，并与服务端建立链接
                    Socket socket = new Socket(host, port);
                    try {
                        /* 客户端像服务端进行请求，并将请求参数写入流中*/
                        // 将对象写入到对象输出流，并将其发送到Socket流中去
                        ObjectOutputStream output = new ObjectOutputStream(
                            socket.getOutputStream());
                        try {
                            // 发送请求
                            System.out.println("\nClient发送请求 ： ");
                            output.writeUTF(method.getName());
                            System.out.println("methodName : " + method.getName());
                            output.writeObject(method.getParameterTypes());
                            System.out.println("parameterTypes : " + Arrays.toString(method
                                .getParameterTypes()));
                            output.writeObject(arguments);
                            System.out.println("arguments : " + Arrays.toString(arguments));


                            /* 客户端读取并返回服务端的响应*/
                            ObjectInputStream input = new ObjectInputStream(
                                socket.getInputStream());
                            try {
                                Object result = input.readObject();
                                if (result instanceof Throwable) {
                                    throw (Throwable)result;
                                }
                                System.out.println("\nClient收到响应 ： ");
                                System.out.println("result : " + result);
                                return result;
                            } finally {
                                input.close();
                            }
                        } finally {
                            output.close();
                        }
                    } finally {
                        socket.close();
                    }
                }
            });
        return proxy;
    }
```

从该RPC框架的简易实现来看，RPC客户端逻辑是：首先创建Socket客户端并与服务端建立链接，然后使用Java原生的序列化/反序列化机制将调用请求发送给客户端，包括所调用方法的名称、参数列表,将服务端的响应返回给用户即可。至此，一次简单PRC调用的客户端流程执行完毕。特别地，从代码实现来看，实现透明的PRC调用的关键就是 **动态代理**，这是RPC框架实现的灵魂所在。

[感谢](https://blog.csdn.net/justloveyou_/article/details/79441306)