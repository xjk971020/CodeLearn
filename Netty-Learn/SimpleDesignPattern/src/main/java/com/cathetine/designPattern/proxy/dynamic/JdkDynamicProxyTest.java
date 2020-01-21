package com.cathetine.designPattern.proxy.dynamic;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Author:cathetine
 * @Date:19-9-9
 * @Time:下午9:14
 */
public class JdkDynamicProxyTest {
    public static void main(String[] args) throws Exception {
        saveGeneratedJdkProxyFiles();
        //创建一个实例对象，这个对象是被代理的对象，委托类
        System.out.println("-------------------第一种创建代理类方法--------------");
        //创建一个与代理类相关联的InvocationHandler,每一个代理类都有一个关联的 InvocationHandler，并将代理类引用传递进去
        Person person = new SoftwareEngineer("Vincent");
        //创建一个 代理对象 personProxy 来代理 person，创建的代理对象的每个执行方法都会被替换执行Invocation接口中的invoke方法
        InvocationHandler Handler = new PersonInvocationHandler<>(person);
        /** 代理类信息 */
        Person personProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, Handler);
        System.out.println("package = " + personProxy.getClass().getPackage() + " SimpleName = " + personProxy.getClass().getSimpleName() + " name =" + personProxy.getClass().getName() + " CanonicalName = " + "" + personProxy.getClass().getCanonicalName() + " 实现的接口 Interfaces = " + Arrays.toString(personProxy.getClass().getInterfaces()) + " superClass = " + personProxy.getClass().getSuperclass() + " methods =" + Arrays.toString(personProxy.getClass().getMethods()));        // 通过 代理类 执行 委托类的代码逻辑
        personProxy.goWorking(personProxy.getName(), "深圳");
        Person persontwo = new SoftwareEngineer("Vincent");
        InvocationHandler Handlertwo = new PersonInvocationHandler<>(persontwo);        // 2 创建代理类,是一个字节码文件, 把 proxyClass 保存起来就能看到 他继承Proxy 类，实现Person接口
        Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class<?>[]{Person.class});        /** 代理类信息 */
        System.out.println("package = " + proxyClass.getPackage() + " SimpleName = " + proxyClass.getSimpleName() + " name =" + proxyClass.getName() + " CanonicalName = " +                "" + proxyClass.getCanonicalName() + " 实现的接口 Interfaces = " + Arrays.toString(proxyClass.getInterfaces()) +                " superClass = " + proxyClass.getSuperclass() + " methods =" + Arrays.toString(proxyClass.getMethods()));        // 3、  通过 proxyClass 获得 一个带有InvocationHandler参数的构造器constructor
        Constructor<?> ProxyConstructor = proxyClass.getConstructor(InvocationHandler.class);        // 4、通过构造器创建一个  动态代理类 实例
        Person stuProxy = (Person) ProxyConstructor.newInstance(Handlertwo);        /** 检测生成的类是否是代理类 */
        System.out.println("stuProxy isProxy "+Proxy.isProxyClass(stuProxy.getClass()));        /** 获取 代理类关联的 InvocationHandler 是哪个*/
        InvocationHandler handlerObject = Proxy.getInvocationHandler(stuProxy);
        System.out.println(handlerObject.getClass().getName());
        stuProxy.goWorking(stuProxy.getName(), "广州");        // 保存代理類
        saveClass("$PersonProxy0", proxyClass.getInterfaces(), "/home/cathetine/IdeaProjects/Netty-Learn/SimpleDesignPattern/src/");
    }

    public static void saveClass(String className, Class<?>[] interfaces, String pathdir) {
        /**
         * 第一个参数是 代理类 名 。
         * 第二个参数是 代理类需要实现的接口
         */
        byte[] classFile = ProxyGenerator.generateProxyClass(className, interfaces);
        /**
         * 如果目录不存在就新建所有子目录
         */
        Path path1 = Paths.get(pathdir);
        if (!path1.toFile().exists()) {
            path1.toFile().mkdirs();
        }
        String path = pathdir + className + ".class";
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }

    /**
     * 设置保存Java动态代理生成的类文件。
     *
     * @throws Exception
     */
    public static void saveGeneratedJdkProxyFiles() throws Exception {
        Field field = System.class.getDeclaredField("props");
        field.setAccessible(true);
        Properties props = (Properties) field.get(null);
        props.put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

}
