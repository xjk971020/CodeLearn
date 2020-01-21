package com.cathetine.designPattern.proxy.static_;

/**
 * @Author:cathetine
 * @Date:19-10-13
 * @Time:上午10:24
 * 代理对象
 */
public class ProxyImage implements Image{
    private RealImage realImage;
    private String fileName;
    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
