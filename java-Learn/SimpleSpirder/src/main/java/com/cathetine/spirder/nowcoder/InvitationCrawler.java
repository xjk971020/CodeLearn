package com.cathetine.spirder.nowcoder;

import com.cathetine.spirder.Constant;
import com.cathetine.spirder.zhihu.HttpGetConnect;
import com.cathetine.spirder.WordCloudGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.cathetine.spirder.WordCloudGenerator.writeTitle;

/**
 * @Author:cathetine
 * @Date:19-10-12
 * @Time:上午9:06 爬取牛客网帖子标题名,　生成词图
 */
public class InvitationCrawler {
    private static String url = "https://www.nowcoder.com/discuss/tag/_TAG_?type=0&order=0&pageSize=30&expTag=0&query=&page=_PAGE_";
    private static String cookie = "NOWCODERUID=14448AFE08E127EAAAE1ECC1110B6736; NOWCODERCLINETID=A9B93C557B61C2D28DB7A4E51B29055A; gr_user_id=f515eafa-8e01-4d0c-961f-9b93c6c6afa5; grwng_uid=df443e7b-e5bc-4b60-8c42-244edfb6a4f5; Hm_lvt_a808a1326b6c06c437de769d1b85b870=1570676115,1570688677,1570689537,1570841458; dc_pid_set_next_pre=236452_103895_103893_239963_139344_235005_243248_211970_224015_90980_228092_231320_213636_229591_221848_227400_225997_217410_224215_1771_222657_218483_204342_216132_220098_216164_218010_217698_216277_124432; Hm_lpvt_a808a1326b6c06c437de769d1b85b870=1570857640; SERVERID=547d00d82311952605c62ceac64f21fd|1570857639|1570841457";
    private static String path = Constant.FILE_PATH + "/nowcoder/";

    static {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 根据话题ID爬去该话题的所有帖子的标题
     *
     * @param tagId
     * @param start
     * @param end
     * @return
     */
    public static List<String> getInvitationHead(List<String> result, String tagId, int start, int end) {
        String targetUrl = url.replace("_TAG_", tagId);
        try {
            int page = start;
            for (; page <= end; ) {
                //替换页码
                targetUrl = targetUrl.replace("_PAGE_", String.valueOf(page));
                System.out.println(targetUrl);
                if (page == end) {
                    System.out.println(Thread.currentThread().getName() + " : " + targetUrl);
                }
                String content = HttpGetConnect.connect(targetUrl, Constant.CHARSET, targetUrl, cookie);
                Document document = Jsoup.parse(content);
                Elements commonList = document.getElementsByClass("common-list");
                Element ul = commonList.get(0);
                Elements titles = ul.getElementsByClass("clearfix");
                for (int i = 0; i < titles.size(); i = i + 2) {
                    Element titleDetail = titles.get(i).getElementsByClass("discuss-main clearfix").get(0);
                    String title = titleDetail.getElementsByTag("a").get(0).text().replace("精", "").replace("烫", "");
                    result.add(title);
                }
                //将页面数值替换成 _PAGE_, 并让页码的数值加１
                String temp = "page=" + String.valueOf(page);
                targetUrl = targetUrl.replace(temp, "page=_PAGE_");
                page++;
                try {
                    TimeUnit.SECONDS.sleep((int) (Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    public static void main(String[] args) throws IOException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        System.out.println("开始时间: " + sdf.format(startTime));

        List<String> result = new ArrayList<>();
        result = Collections.synchronizedList(result);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int start = 1;
        int end = 10;
        String tagId = "861";
        for (; start <= 91; ) {
            executorService.submit(new CrawlerThread(result, tagId, start, end, countDownLatch));
            start = start + 10;
            end = end + 10;
        }
        countDownLatch.await();
        executorService.shutdown();
        writeTitle(result, path, "牛客秋招帖子标题分析.txt");
        WordCloudGenerator.getWordCloud(path, "牛客秋招帖子标题分析.txt", "牛客秋招帖子标题词云.png");

        Date endTime = new Date();
        System.out.println("结束时间: " + sdf.format(endTime));
        long cost = (endTime.getTime() - startTime.getTime())/1000;
        System.out.println("用时:" + cost + "秒");
    }
}

