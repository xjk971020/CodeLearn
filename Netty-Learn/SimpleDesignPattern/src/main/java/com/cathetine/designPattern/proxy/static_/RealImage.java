package com.cathetine.designPattern.proxy.static_;

/**
 * @Author:cathetine
 * @Date:19-10-13
 * @Time:上午10:22
 * 需要将图片从磁盘中加载出来才能展示
 */
public class RealImage implements Image{
    private String fileName;
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadPictureFromDisk();
    }
    public void display() {
        System.out.println("展示图片" + fileName);
    }
    private void loadPictureFromDisk() {
        System.out.println("将图片从" + fileName + "中加载出来");
    }
}
