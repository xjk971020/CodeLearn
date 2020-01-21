package com.cathetine.spirder.proxy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author:xjk
 * @Date 2019/12/3 10:39
 */
public class IPPool {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<IPBean> httpIp = Collections.synchronizedList(IPCrawler.getHttpIp());
//        List<IPBean> httpsIp = IPCrawler.getHttpsIp();
        int length = httpIp.size();
        int sLength = length/10;
        System.out.println("总长度:" + length);
        System.out.println("一共有多少节:" + sLength);
        int start = 0;
        for (;start < sLength; start++) {
            int end = (start + 1) * 10;
            if (end > length) {
                end = length - 1;
            }
            int curStart = start * 10;
            executorService.submit(new IPThread(curStart, end, httpIp));
        }
        executorService.shutdown();
    }
}
