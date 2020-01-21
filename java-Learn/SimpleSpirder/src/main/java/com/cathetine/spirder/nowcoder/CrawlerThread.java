package com.cathetine.spirder.nowcoder;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author:cathetine
 * @Date:19-10-12
 * @Time:下午3:32
 * 多线程爬虫
 */
public class CrawlerThread implements Runnable{
    private String tagId;
    private int start;
    private int end;
    private List<String> result;
    private CountDownLatch countDownLatch;
    public CrawlerThread(List<String> result, String tagId, int start, int end, CountDownLatch countDownLatch) {
        this.tagId = tagId;
        this.start = start;
        this.end = end;
        this.result = result;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        InvitationCrawler.getInvitationHead(result,tagId,start,end);
        countDownLatch.countDown();
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
