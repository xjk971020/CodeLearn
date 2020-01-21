package com.cathetine.spirder.proxy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @Author:xjk
 * @Date 2019/12/5 23:13
 */
public class IPWriter {
    public static void write(List<IPBean> ipBeans) throws IOException {
        int seed = (int) (Math.random()*1000);
        File file = new File("D:\\爬虫资料\\ip池-" + seed + ".txt");
        if (file.exists()) {
            file.delete();
        }
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ipBeans.forEach(ipBean -> {
            try {
                System.out.println(Thread.currentThread().getName() + "写入---> " + ipBean.toString());
                bufferedWriter.write(ipBeans.toString() + "\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
