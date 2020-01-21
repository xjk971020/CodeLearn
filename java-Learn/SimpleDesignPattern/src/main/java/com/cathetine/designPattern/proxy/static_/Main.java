package com.cathetine.designPattern.proxy.static_;

/**
 * @Author:cathetine
 * @Date:19-10-13
 * @Time:上午10:27
 */
public class Main {
    public static void main(String[] args) {
        Image proxyImage = new ProxyImage("pic.jpg");
        proxyImage.display();
        System.out.println();
        proxyImage.display();
    }
}
