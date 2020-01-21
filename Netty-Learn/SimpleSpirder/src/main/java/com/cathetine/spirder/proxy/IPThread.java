package com.cathetine.spirder.proxy;

import com.cathetine.spirder.HttpGetConnect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Author:xjk
 * @Date 2019/12/5 22:56
 * ip抓取线程池
 */
public class IPThread implements Runnable {
    private int startIndex;
    private int endIndex;
    private List<IPBean> list;

    public IPThread(int startIndex, int endIndex, List<IPBean> list) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.list = list;
    }

    @Override
    public void run() {
        int n = 1;
        List<IPBean> result = new ArrayList<>();
        while (startIndex < endIndex) {
            IPBean ipBean = list.get(startIndex);
            try {
                if (n == 10) {
                    System.out.println(Thread.currentThread().getName() + " 验证第" + n++ + "条---> " + ipBean.toString());
                }
                boolean valid = IPUtil.isValid_methodTwo(ipBean);
                if (valid) {
                    result.add(ipBean);
                }
                n++;
            } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
            }
            startIndex++;
        }
        try {
            IPWriter.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
